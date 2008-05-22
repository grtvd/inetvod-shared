/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class Mailer extends Thread
{
	/* Constants */
	public static String SmtpServerProperty = "mailer.smtpserver";
	public static String DefaultFromEmailProperty = "mailer.defaultfromemail";

	/* Fields */
	private static String fSmtpServer;
	private static String fDefaultFromEmail;

	private String fFromEmail;
	private String fToEmail;
	private String fSubject;
	private String fBody;

	/* Getters and Setters */
	public String getFromEmail() { return fFromEmail; }
	public void setFromEmail(String fromEmail) { fFromEmail = fromEmail; }
	public String getToEmail() { return fToEmail; }
	public void setToEmail(String toEmail) { fToEmail = toEmail; }
	public String getSubject() { return fSubject; }
	public void setSubject(String subject) { fSubject = subject; }
	public String getBody() { return fBody; }
	public void setBody(String body) { fBody = body; }

	/* Construction */
	public static void initialize(String smtpServer, String defaultFromEmail)
	{
		fSmtpServer = smtpServer;
		fDefaultFromEmail = defaultFromEmail;
	}

	public static void initialize()
	{
		AppProperties properties = AppProperties.getThe();
		initialize(properties.getProperty(SmtpServerProperty), properties.getProperty(DefaultFromEmailProperty));
	}

	private Mailer(String fromEmail)
	{
		fFromEmail = fromEmail;
	}

	public static Mailer newInstance()
	{
		return new Mailer(fDefaultFromEmail);
	}

	/* Implementation */
	public static void sendMail(String toEmail, String subject, String text)
	{
		Mailer mailer = newInstance();
		mailer.fToEmail = toEmail;
		mailer.fSubject = subject;
		mailer.fBody = text;
		mailer.send();
	}

	public void send()
	{
		setPriority(MIN_PRIORITY);
		start();
	}

	@Override
	public void run()
	{
		if(!StrUtil.hasLen(fSmtpServer))
		{
			Logger.logErr(this, "run", "SmtpServer not specified");
			return;
		}
		if(!StrUtil.hasLen(fFromEmail))
		{
			Logger.logErr(this, "run", "FromEmail not specified");
			return;
		}
		if(!StrUtil.hasLen(fToEmail))
		{
			Logger.logErr(this, "run", "ToEmail not specified");
			return;
		}
		if(!StrUtil.hasLen(fSubject) && !StrUtil.hasLen(fBody))
		{
			Logger.logErr(this, "run", "Neither Subject nor Body specified");
			return;
		}

		Properties props = new Properties();
		props.put("mail.smtp.host", fSmtpServer);
		props.put("mail.from", fFromEmail);
		//props.put("mail.debug", "true");
		Session session = Session.getDefaultInstance(props, null);

		try
		{
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom();
			msg.setRecipients(Message.RecipientType.TO, fToEmail);
			if(StrUtil.hasLen(fSubject))
				msg.setSubject(fSubject);
			msg.setSentDate(new Date());
			msg.setText(StrUtil.hasLen(fBody) ? fBody : "");
			Transport.send(msg);
		}
		catch(MessagingException e)
		{
			Logger.logWarn(this, "run", e);
		}
	}
}

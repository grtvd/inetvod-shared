/**
 * Copyright © 2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import org.apache.log4j.spi.TriggeringEventEvaluator;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.Level;

public class LoggerWarnEvaluator implements TriggeringEventEvaluator
{
	/* Implementation */
	public boolean isTriggeringEvent(LoggingEvent event)
	{
		return (event.getLevel().toInt() >= Level.WARN_INT);
	}
}

/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Hex;

import com.inetvod.common.core.StrUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptoCipher
{
	/* Constants */
	//private static final String ALGORITHM = "PBEWithHmacSHA1AndDESede";
	private static final String ALGORITHM = "PBEWithMD5AndDES";
	private static final String CHAR_ENCODING = "UTF-8";
	@SuppressWarnings({"MagicNumber"})
	private static final String DEFAULT_SALT = "2a9cd9f685e3dd29";
	private static final int DEFAULT_ITERATIONCOUNT = 1000;

	private byte[] fSalt;
	private int fIterationCount;
	private char[] fEncryptionPassword;

	private PBEParameterSpec fPBEParameterSpec;
	private SecretKey fSecretKey;
	private Cipher fEncryptCipher;
	private Cipher fDecryptCipher;

	private CryptoCipher(String encryptionPassword) throws Exception
	{
		this(DEFAULT_SALT, DEFAULT_ITERATIONCOUNT, encryptionPassword);
	}

	private CryptoCipher(String salt, int iterationCount, String encryptionPassword) throws Exception
	{
		fSalt = Hex.decodeHex(salt.toCharArray());
		fIterationCount = iterationCount;
		fEncryptionPassword = encryptionPassword.toCharArray();
	}

	public static CryptoCipher newInstance(String encryptionPassword) throws Exception
	{
		return new CryptoCipher(encryptionPassword);
	}

	private PBEParameterSpec getPBEParameterSpec()
	{
		if(fPBEParameterSpec == null)
			fPBEParameterSpec = new PBEParameterSpec(fSalt, fIterationCount);

		return fPBEParameterSpec;
	}

	private SecretKey getSecretKey() throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		if(fSecretKey == null)
		{
			// Convert the encryption password into a SecretKey object, using a PBE key factory.
			PBEKeySpec pbeKeySpec = new PBEKeySpec(fEncryptionPassword);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			fSecretKey = keyFactory.generateSecret(pbeKeySpec);
		}

		return fSecretKey;
	}

	private Cipher getEncryptCipher() throws NoSuchAlgorithmException, InvalidKeySpecException,
		InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException
	{
		if(fEncryptCipher == null)
		{
			// Create PBE Cipher
			fEncryptCipher = Cipher.getInstance(ALGORITHM);

			// Initialize PBE Cipher with key and parameters
			fEncryptCipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), getPBEParameterSpec());
		}

		return fEncryptCipher;
	}

	private Cipher getDecryptCipher() throws NoSuchAlgorithmException, InvalidKeySpecException,
		InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException
	{
		if(fDecryptCipher == null)
		{
			// Create PBE Cipher
			fDecryptCipher = Cipher.getInstance(ALGORITHM);

			// Initialize PBE Cipher with key and parameters
			fDecryptCipher.init(Cipher.DECRYPT_MODE, getSecretKey(), getPBEParameterSpec());
		}

		return fDecryptCipher;
	}

	public String encrypt(String textToEncrypt) throws Exception
	{
		if(!StrUtil.hasLen(textToEncrypt))
			return null;

		byte[] cleartext = textToEncrypt.getBytes(CHAR_ENCODING);
		byte[] ciphertext = getEncryptCipher().doFinal(cleartext);

		return (new BASE64Encoder()).encode(ciphertext);
		// to return as Hex bytes in String format use:
		//return new String(Hex.encodeHex(ciphertext));
	}

	public String decrypt(String encryptedText) throws Exception
	{
		if(!StrUtil.hasLen(encryptedText))
			return null;

		// if stored Hex bytes in String format use:
		//byte[] ciphertext = Hex.decodeHex(encryptedText.toCharArray());
		byte[] ciphertext = (new BASE64Decoder()).decodeBuffer(encryptedText);
		byte[] cleartext = getDecryptCipher().doFinal(ciphertext);

		return new String(cleartext, CHAR_ENCODING);
	}
}

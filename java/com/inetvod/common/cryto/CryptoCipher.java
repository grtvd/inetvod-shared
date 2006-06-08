/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.cryto;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.io.UnsupportedEncodingException;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.DecoderException;

public class CryptoCipher
{
	/* Constants */
	//private static final String ALGORITHM = "PBEWithHmacSHA1AndDESede";
	private static final String ALGORITHM = "PBEWithMD5AndDES";
	private static final String CHAR_ENCODING = "UTF-8";
	@SuppressWarnings({"MagicNumber"})
	private static final byte[] DEFAULT_SALT = { 0x2a, (byte)0x9c, (byte)0xd9, (byte)0xf6, (byte)0x85,
		(byte)0xe3, (byte)0xdd, (byte)0x29 };
	private static final int DEFAULT_ITERATIONCOUNT = 1000;

	private byte[] fSalt;
	private int fIterationCount;
	private char[] fEncryptionPassword;

	private PBEParameterSpec fPBEParameterSpec;
	private SecretKey fSecretKey;
	private Cipher fEncryptCipher;
	private Cipher fDecryptCipher;

	private CryptoCipher(String encryptionPassword)
	{
		fSalt = DEFAULT_SALT;
		fIterationCount = DEFAULT_ITERATIONCOUNT;
		fEncryptionPassword = encryptionPassword.toCharArray();
	}

	private CryptoCipher(String salt, int iterationCount, String encryptionPassword) throws DecoderException
	{
		fSalt = Hex.decodeHex(salt.toCharArray());
		fIterationCount = iterationCount;
		fEncryptionPassword = encryptionPassword.toCharArray();
	}

	public CryptoCipher newInstance(String encryptionPassword)
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

	public String encrypt(String textToEncrypt) throws NoSuchAlgorithmException, InvalidKeySpecException,
		NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
		IllegalBlockSizeException, UnsupportedEncodingException
	{
		byte[] cleartext = textToEncrypt.getBytes(CHAR_ENCODING);
		byte[] ciphertext = getEncryptCipher().doFinal(cleartext);
		char[] hexText = Hex.encodeHex(ciphertext);

		return new String(hexText);
	}

	public String decrypt(String encryptedText) throws DecoderException, NoSuchAlgorithmException,
		InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, InvalidKeySpecException,
		BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException
	{
		byte[] ciphertext = Hex.decodeHex(encryptedText.toCharArray());
		byte[] cleartext = getDecryptCipher().doFinal(ciphertext);

		return new String(cleartext, CHAR_ENCODING);
	}
}

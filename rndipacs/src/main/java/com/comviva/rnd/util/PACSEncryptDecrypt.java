/**
 * 
 */
package com.comviva.rnd.util;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class PACSEncryptDecrypt - Implements the PACS Encryption & Decryption
 * Methods.
 * 
 * @author Ravi Choudhari
 * @version 0.1
 */
public class PACSEncryptDecrypt {

	/** The Constant privateKey. */
	public static final String privateKey = "dlk#134";

	/**
	 * PACS encrypt. Encrypt the given plain text by using the encryption key
	 * 
	 * @param plainText
	 *            the plain text (data to be encrypted)
	 * @param key
	 *            the key
	 * @return the string - the cipher text (encrypted data)
	 */
	public static String PACSEncrypt(String plainText, String key) {

		String encrypted = "";

		if (plainText == null || plainText.length() == 0)
			return null;

		Random random = new Random();
		char publicKey = (char) random.nextInt(0x64);

		String encryptedPrivateKey = keyEncryption(key, publicKey);
		String strTmp = plainText;

		for (int i = 0; i < strTmp.length(); i++) {
			char oChar = plainText.charAt(i);
			for (int j = 0; j < encryptedPrivateKey.length(); j += 2) {
				oChar = (char) (oChar ^ Integer.parseInt(
						encryptedPrivateKey.substring(j, j + 2), 0x10));
			}
			String hex = Integer.toHexString(oChar);
			encrypted += hex.length() == 2 ? hex : "0" + hex;
		}
		String strKey = Integer.toString(publicKey);
		encrypted += strKey.length() == 2 ? strKey : "0" + strKey;

		return encrypted.toUpperCase();
	}

	public static String PACSDecrypt(String cipherText, String key) {

		String decrypted = "";

		if (cipherText == null || cipherText.length() == 0)
			return null;

		char publicKey = (char) (Integer.parseInt(
				cipherText.substring(cipherText.length() - 2), 0x0A));

		String encryptedPrivateKey = keyEncryption(key, publicKey);
		String strTemp = cipherText.substring(0, cipherText.length() - 2);

		for (int i = 0; i < strTemp.length(); i += 2) {
			char oChar = (char) (Integer.parseInt(strTemp.substring(i, i + 2),
					0x10));
			for (int j = 0; j < encryptedPrivateKey.length(); j += 2) {
				oChar = (char) (oChar ^ Integer.parseInt(
						encryptedPrivateKey.substring(j, j + 2), 0x10));
			}
			decrypted += String.valueOf(oChar);
		}

		return decrypted;
	}

	/**
	 * PACS decrypt. Decrypt the given cipher text by using the encryption key
	 * 
	 * @param cipherText
	 *            the cipher text (data to be decrypted)
	 * @param key
	 *            the key
	 * @return the string - the plain text (decrypted data)
	 */

	private static String keyEncryption(String privateKey, char publicKey) {

		String secretKey = "";

		for (int i = 0; i < privateKey.length(); i++) {
			char oneChar = (char) (privateKey.charAt(i) ^ 0xFF);
			oneChar >>>= 2;
			oneChar ^= publicKey;
			String hex = Integer.toHexString(oneChar);
			secretKey += hex.length() == 2 ? hex : "0" + hex;
		}

		return secretKey.toUpperCase();
	}
	/**
	 * Key encryption. - Encrypt the private key
	 * 
	 * @param privateKey
	 *            the private key
	 * @param publicKey
	 *            the public key
	 * @return the string - Encrypted private key
	 */
}

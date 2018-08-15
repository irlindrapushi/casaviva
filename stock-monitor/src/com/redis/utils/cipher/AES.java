/*
 * Copyright (C) 2018 Redjan Shabani
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.redis.utils.cipher;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Redjan Shabani
 */
public class AES {
	
	private static final byte[] KEY  = "Big Bang 0000000".getBytes();
	
	public static String encrypt(String decrypted) {
		String encrypted = null;
		try {
			Key aesKey = new SecretKeySpec(KEY, "AES");
			
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			
			encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(decrypted.getBytes()));
		} 
		catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
			Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return encrypted;
	}
	
	public static String decrypt(String encryptes) {
		String decrypted = null;
		
		try {			
			byte [] bytes = Base64.getDecoder().decode(encryptes);
			
			Key aesKey = new SecretKeySpec(KEY, "AES");
			
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			
			decrypted = new String(cipher.doFinal(bytes));
		} 
		catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
			Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return decrypted;
	}
	
	
	public static void main(String[] args) {
		String text0 = "Hello World";
			String text1 = encrypt(text0);
			System.out.println(encrypt(text0));
			System.out.println(decrypt(text1));
	}
}

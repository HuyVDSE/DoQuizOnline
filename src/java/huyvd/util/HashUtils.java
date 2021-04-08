/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvd.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author BlankSpace
 */
public class HashUtils {

    public static String createHashString(String base)
	    throws UnsupportedEncodingException, NoSuchAlgorithmException {
	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	byte[] hash = digest.digest(base.getBytes("UTF-8"));
	StringBuilder hexString = new StringBuilder();

	for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if (hex.length() == 1) {
		hexString.append('0');
	    }
	    hexString.append(hex);
	}

	return hexString.toString();
    }
}

package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordSHA1 {

	public static boolean authenticate(String attemptedPassword, String encryptedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword);

		System.out.println("Attendu : " + encryptedAttemptedPassword);
		System.out.println("Recu : " + attemptedPassword + "  :  " + encryptedPassword);
		return encryptedPassword.equals(encryptedAttemptedPassword);
	}

	/*
	 * http://howtodoinjava.com/2013/07/22/how-to-generate-secure-password-hash-
	 * md5-sha-pbkdf2-bcrypt-examples/
	 */
	public static String getEncryptedPassword(String password) {

		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return generatedPassword;
	}
/*
	String algorithm = "PBKDF2WithHmacSHA1";
	int derivedKeyLength = 160;
	int iterations = 20000;

	String sel = "HELL";
	byte[] b = sel.getBytes();
	KeySpec spec = new PBEKeySpec(password.toCharArray(), b, iterations, derivedKeyLength);

	SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

	return String.valueOf(f.generateSecret(spec).getEncoded());
}
$/
/*
 * public static byte[] generateSalt() throws NoSuchAlgorithmException {
 * SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
 * 
 * byte[] salt = new byte[8]; random.nextBytes(salt);
 * 
 * return salt; }
 */
}

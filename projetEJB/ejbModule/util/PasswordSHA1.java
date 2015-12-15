package util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public  class PasswordSHA1 {
	
	public static boolean authenticate(String attemptedPassword,
			String encryptedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String encryptedAttemptedPassword = getEncryptedPassword(
				attemptedPassword);
		
		System.out.println("Attendu : " + encryptedAttemptedPassword);
		System.out.println("Recu : " + attemptedPassword + "  :  " + encryptedPassword);
		return encryptedPassword.equals(encryptedAttemptedPassword);
	}

	public static String getEncryptedPassword(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String algorithm = "PBKDF2WithHmacSHA1";
		int derivedKeyLength = 160;
		int iterations = 20000;

		String sel = "HELL";
		byte[] b = sel.getBytes();
		KeySpec spec = new PBEKeySpec(password.toCharArray(), b, iterations, derivedKeyLength);
		
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

		return String.valueOf(f.generateSecret(spec).getEncoded());
	}
/*
	public static byte[] generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		
		byte[] salt = new byte[8];
		random.nextBytes(salt);

		return salt;
	}
*/
}

package stackjava.com.bcryptdemo.demo;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptHashing {

	public static boolean verifyHash(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}

	public static void main(String[] args) {
		String password = "stackjava.com";
		String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		System.out.println("BCrypt hash: " + hash);
		
		boolean valuate = BCrypt.checkpw(password, "$2a$12$OFOICietLS3.qRtzIe6jE.vF.fmtL22DqIZ18WNMmQ.8nS7Frq5aO");
		System.out.println(valuate);
	}
}

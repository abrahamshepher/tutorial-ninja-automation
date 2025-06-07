package utils;

import java.util.Date;

public class Common {
	public static String generateNewEmail() {
	    return new Date().toString().replaceAll("[\\s:]", "") + "@gmail.com";
		}
}

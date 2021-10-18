package cl.bci.users.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	
	private static final String REGEX_EMAIL = "^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,3}$";
	private static final String REGEX_PASSWORD = "^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,3}$";
	
	public static Boolean validaEmail (String email) {
		Pattern pattern = Pattern.compile(REGEX_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}
	
	public static Boolean validaPassword (String password) {
		Pattern pattern = Pattern.compile(REGEX_PASSWORD);
		Matcher matcher = pattern.matcher(password);
		return matcher.find();
	}

}

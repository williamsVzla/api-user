package cl.bci.users.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	
	private static final String REGEX_EMAIL = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";
	private static final String REGEX_PASSWORD = "^(?=.*?\\d.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
	
	public static Boolean validaEmail (String email) {
		Pattern pattern = Pattern.compile(REGEX_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static Boolean validaPassword (String password) {
		Pattern pattern = Pattern.compile(REGEX_PASSWORD);
		Matcher matcher = pattern.matcher(password);
		return matcher.find();
	}

}

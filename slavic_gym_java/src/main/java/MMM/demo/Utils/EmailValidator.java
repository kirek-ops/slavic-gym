package MMM.demo.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String[] testEmails = {
                "test@example.com",
                "user.name+tag+sorting@example.com",
                "user_name@example.co.uk",
                "user@subdomain.example.com",
                "user@localhost",
                "user@.example.com",
                "user@com",
                "@example.com"
        };

        for (String email : testEmails) {
            System.out.println(email + " is valid: " + isValidEmail(email));
        }
    }
}

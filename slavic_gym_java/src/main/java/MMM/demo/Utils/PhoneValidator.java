package MMM.demo.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
    private static final String PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    public static boolean isValidPhone(String phone) {
        if (phone == null) {
            return false;
        }
        Matcher matcher = PHONE_PATTERN.matcher(phone);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String[] testPhones = {
                "+1234567890",
                "1234567890",
                "(123) 456-7890",
                "123-456-7890",
                "123.456.7890",
                "+31636363634",
                "075-63546725",
                "+1 650-555-1234",
                "123 456 7890"
        };

        for (String phone : testPhones) {
            System.out.println(phone + " is valid: " + isValidPhone(phone));
        }
    }
}

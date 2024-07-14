//6510405334 kittikun buntoyut

public class UserRegistrationService {
    private final UserValidator userValidator;
    private final EmailValidator emailValidator;

    public UserRegistrationService(UserValidator userValidator, EmailValidator emailValidator) {
        this.userValidator = userValidator;
        this.emailValidator = emailValidator;
    }

    public boolean register(User user) {
        userValidator.validate(user);
        emailValidator.validate(user.getEmail());
        return true;
    }
}

public class UserValidator {
    public void validate(User user) {
        validateName(user.getName());
        validateAge(user.getAge());
    }

    private void validateName(String name) {
        if (isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Name should not be empty");
        }
        if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Name is in wrong format");
        }
    }

    private void validateAge(int age) {
        if (age < 20) {
            throw new IllegalArgumentException("Age should be more than 20 years");
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}

public class EmailValidator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final List<String> NOT_ALLOWED_DOMAINS = Arrays.asList("dom1.cc", "dom2.cc", "dom3.cc");

    public void validate(String email) {
        if (isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Email should not be empty");
        }
        if (!isValidFormat(email)) {
            throw new IllegalArgumentException("Email is in wrong format");
        }
        if (isNotAllowedDomain(email)) {
            throw new IllegalArgumentException("Domain Email is not allowed");
        }
    }

    private boolean isValidFormat(String email) {
        Pattern validEmailPattern = Pattern.compile(EMAIL_PATTERN);
        return validEmailPattern.matcher(email).matches();
    }

    private boolean isNotAllowedDomain(String email) {
        String domain = email.split("@")[1];
        return NOT_ALLOWED_DOMAINS.contains(domain);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
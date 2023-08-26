package com.emma_ea.secure_todo.constants;

public class TodoReqStrings {
    public static final String USERNAME_NOT_FOUND = "User with email provided does not exist." ;
    public static final String SIGN_IN_FAILED = "Invalid email or password";
    public static final String SIGN_IN_FAILED_VERIFY_EMAIL = "Please to continue verify your email";
    public static final String SIGN_IN_SUCCESS = "Authentication successful";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";

    public static String EMAIL_NEED_VERIFICATION(String username) {
        return String.format("Registration successful %s, Please verify email in order to login", username);
    }
}

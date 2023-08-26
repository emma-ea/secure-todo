package com.emma_ea.secure_todo.utils;

public class EmailBuilder {
    public static String buildEmail(String recipientName, String actionLink) {
       return String.format("Hello, %s. Copy paste link to verify email.\n\n%s", recipientName, actionLink);
    }
}

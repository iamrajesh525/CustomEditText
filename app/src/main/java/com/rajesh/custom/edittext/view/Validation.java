package com.rajesh.custom.edittext.view;

/**
 * Created by Mizpah_DEV on 24-Oct-16.
 */


import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import java.util.regex.Pattern;

public class Validation {

    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //private static final String PHONE_REGEX = "\\d{3}-\\d{7}";
    private static final String PHONE_REGEX = "[789]{1}[0-9]{9}";
    private static final String PHONE_REGEX_COUNTRY_CODE = "[+]{1}[0-9]{2}[789]{1}[0-9]{9}";

    private static final String NAME_REGEX = "[a-zA-Z0-9 ]{6,30}";
    // private static final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9]{6,12}";
    // Error Messages
    private static final String REQUIRED_MSG = "Required";
    private static final String EMAIL_MSG = "Invalid email";
    private static final String PHONE_MSG = "###-#######";
    // public final static String TOAST_PASSWORD="Password must contain 6 to 20 characters with at least one digit, one upper case letter, one lower case letter and one special symbol (“@#$%”).";
    public final static String TOAST_PASSWORD = "Must contain min 6, max 30 characters.";
    //private static final String VALID_EMAIL_MOBILE =    "Please enter mobile number";
    private static final String VALID_EMAIL_MOBILE = "Required";

    // call this method when you need to check email validation
    public static boolean isEmailAddress(EditText editText, TextInputLayout textInputLayout, boolean required) {
        return isValid(editText, textInputLayout, EMAIL_REGEX, EMAIL_MSG, required);
    }

    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, TextInputLayout textInputLayout, boolean required) {
        String text = editText.getText().toString().trim();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);

        if (required && !hasText(editText, textInputLayout, true)) {
            return false;
        }
        // pattern doesn't match so returning false

        if (required && !Pattern.matches(PHONE_REGEX, text)) {
            textInputLayout.setError("Enter Valid Number");
            return false;
        }
        ;
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    public static boolean isPhoneWithCountryCode(EditText editText, TextInputLayout textInputLayout, boolean required) {
        String text = editText.getText().toString().trim();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(PHONE_REGEX_COUNTRY_CODE, text)) {
            textInputLayout.setError("Enter Valid Number, Eg: +919876543210");
            return false;
        }

        textInputLayout.setErrorEnabled(false);
        return true;
    }

    // call this method when you need to check phone number validation
    public static boolean isParam(EditText editText, TextInputLayout textInputLayout, boolean required, int minvalue, int maxvalue, String parameter) {
        String text = editText.getText().toString().trim();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);

        if (required && !hasText(editText, textInputLayout, true)) {
            return false;
        }
        // pattern doesn't match so returning false

        int val = Integer.parseInt(editText.getText().toString());
        if (required && (minvalue < val) && (val < maxvalue)) {

        } else {
            textInputLayout.setError(parameter + " should be between " + minvalue + " and " + maxvalue + " years");
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    // call this method when you need to check phone number validation
    public static boolean isCharMin(EditText editText, TextInputLayout textInputLayout, boolean required,int min) {
        String text = editText.getText().toString().trim();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);

        if (required && !hasText(editText, textInputLayout, true)) {
            return false;
        }
        // pattern doesn't match so returning false

        if (required && editText.getText().toString().length() < min) {
            textInputLayout.setError("Message must contain min "+min+" characters");
            return false;
        }

        textInputLayout.setErrorEnabled(false);
        return true;
    }


    // call this method when you need to check phone number validation
    public static boolean isUserName(EditText editText, TextInputLayout textInputLayout, boolean required) {
        String str_username = editText.getText().toString().trim();
        int temp_mob_num;

        if (!str_username.equals("") && !str_username.equals(null)) {
            try {
                temp_mob_num = Integer.parseInt(String.valueOf(str_username.toString().toCharArray()[0]));
            } catch (Exception e) {
                temp_mob_num = -1;
            }

        } else {
            temp_mob_num = -1;

        }

        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);
        if (str_username.equals("")) {
            textInputLayout.setError(VALID_EMAIL_MOBILE);
            return false;
        }
        // if entered username is email verifuing by @
        else if (!str_username.equals("") && str_username.contains("@")
                && !Pattern.matches(EMAIL_REGEX, str_username)) {

            textInputLayout.setError(VALID_EMAIL_MOBILE);
            return false;
        }
        // if usser entered is 11 or 22 comparing as mobile no.
        else if (isInteger(str_username) && !(str_username.length() == 10)) {
            textInputLayout.setError(VALID_EMAIL_MOBILE);
            return false;
        } else if (isInteger(str_username) && temp_mob_num < 7) {
            textInputLayout.setError(VALID_EMAIL_MOBILE);
            return false;

        }
        // if user entered is aa or bb comparing as email
        else if (!isInteger(str_username) && !Pattern.matches(EMAIL_REGEX, str_username)) {
            textInputLayout.setError(VALID_EMAIL_MOBILE);
            return false;

        }


        textInputLayout.setErrorEnabled(false);
        return true;
    }

    public static boolean isInteger(String s) {

        // Integer.parseInt(s);
        s = s.trim();
        String regex = "\\d+";
        if (s.matches(regex)) {
            return true;
        } else {
            return false;
        }

    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, TextInputLayout textInputLayout, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);

        // text required and editText is blank, so return false
        if (required && !hasText(editText, textInputLayout, true)) {
            return false;
        }
        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            textInputLayout.setError(errMsg);
            return false;
        }
        ;
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    public static boolean isLoginPassword(EditText editText, TextInputLayout textInputLayout, Boolean required) {

        String text = editText.getText().toString().trim();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);
        if (required && !hasText(editText, textInputLayout, true)) {
            return false;
        }
        if (required && text.length() < 6) {
            textInputLayout.setError("Must contain  min 6 characters.");
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    public static boolean isNameValid(EditText editText, TextInputLayout textInputLayout, Boolean required) {

        String text = editText.getText().toString().trim();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);
        if (required && !hasText(editText, textInputLayout, true)) {
            return false;
        }
        if (required && !Pattern.matches(NAME_REGEX, text)) {
            textInputLayout.setError("Must contain a-z or A-Z or 0-9 and min 6, max 30 characters.");
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        return true;
    }
    public static boolean isContainMinMax(EditText editText, TextInputLayout textInputLayout, Boolean required,int min, int max) {

        String text = editText.getText().toString().trim();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);
        if (required && !hasText(editText, textInputLayout, true)) {
            return false;
        }
        if (required && !Pattern.matches(NAME_REGEX, text)) {
            textInputLayout.setError("Must contain a-z or A-Z or 0-9 and min 6, max 30 characters.");
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    public static boolean isPasswordValid(EditText editText, TextInputLayout textInputLayout, Boolean required) {

        String text = editText.getText().toString().trim();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);
        if (required && !hasText(editText, textInputLayout, true)) {
            return false;
        }
        if (required && !Pattern.matches(PASSWORD_REGEX, text)) {
            textInputLayout.setError(TOAST_PASSWORD);
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    public static boolean isPasswordsMatch(EditText editText, EditText confirm_editText, TextInputLayout textInputLayout, Boolean required) {

        String pass = editText.getText().toString().trim();
        String c_pass = confirm_editText.getText().toString().trim();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);
        if (required && !hasText(editText, textInputLayout, true)) {
            return false;
        }
        if (required && (!pass.equals(c_pass))) {
            textInputLayout.setError("Passwords does not match.");
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText, TextInputLayout textInputLayout, Boolean required) {

        String text = editText.getText().toString().trim();
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(null);

        // length 0 means there is no text
        if (required && text.length() == 0) {
            textInputLayout.setError(REQUIRED_MSG);
            return false;
        }
        textInputLayout.setErrorEnabled(false);
        return true;
    }
}
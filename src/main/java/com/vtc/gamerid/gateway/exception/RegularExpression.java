package com.vtc.gamerid.gateway.exception;

import java.util.regex.Pattern;

/**
 * Created by phucnguyen on 08/03/2017.
 */
public class RegularExpression {

    /**
     * A string length from 6 to 15
     * */
    private static final String REG_USERNAME = "^.*{6,15}$";
    private static final String REG_PASSWORD = "^.*{6,15}$";
    private static final String REG_TEXT = "^.*{6,15}$";

    public static boolean matchUsername(String username){
        return username.matches(REG_USERNAME);
    }

    public static boolean matchPassword(String password){
        return password.matches(REG_PASSWORD);
    }

    public static boolean matchText(String inputText){
        return inputText.matches(REG_TEXT);
    }

    public static String stripXSS(String value) {
        if (value != null) {
//            value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("", "");
            value = value.replaceAll("--", "");
            value = value.replaceAll("#", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("(<|%3C)script(>|%3E)(.*?)(<|%3C)/script(>|%3E)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("(<|%3C)/script(>|%3E)", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("(<|%3C)script(.*?)(>|%3E)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

        }

        return value;
    }

    public static boolean validateStripXss(String input){
        if(input == null || input.trim().length() < 1) return false;
        if(input.equals(stripXSS(input))) return true;
        return false;
    }
}

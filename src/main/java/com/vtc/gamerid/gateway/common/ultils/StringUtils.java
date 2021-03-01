/***************************************************************************
 * Product made by Quang Dat *
 **************************************************************************/
package com.vtc.gamerid.gateway.common.ultils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Author : Dat Le Quang
 * Email: Quangdat0993@gmail.com
 * Nov 14, 2018
 */
public class StringUtils extends org.springframework.util.StringUtils {
  
    public static final String EMAIL_FORMAT_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    
    public static boolean isValidateFormatEmail(String email) {
        if (StringUtils.isEmpty(email)
                || !email.matches(EMAIL_FORMAT_REGEX)) {
            return false;
        }
        
        return true;
    }
    
    public static String toMD5(String string) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());

            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++)
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String encodePhone(String string, int numEncode) {
        int numView = string.length() - numEncode;
        String encode = "";
        for (int i = 0 ; i < numEncode ; i ++ ) {
            encode = encode + "*";
        }
        
        return string.substring(0, numView) + encode;
    }
    
    public static String encodeEmail(String string) {
        StringBuilder mail = new StringBuilder(string);
        int lastChar = mail.indexOf("@") - 1;
        for (int i = 1 ; i < lastChar ; i ++ ) {
            mail.setCharAt(i, '*');
        }
        
        return mail.toString();
    }
    
    public static String generateRamdomCode(int numberChar, String signOfCode) {
        int charMin = 48; // letter '0'
        int charMax = 90; // letter 'Z'
        int targetStringLength = numberChar;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = ThreadLocalRandom.current().nextInt(charMin, charMax);
            // bỏ các ký tự đặc biệt
            while (randomLimitedInt > 57 && randomLimitedInt < 65) {
                randomLimitedInt = ThreadLocalRandom.current().nextInt(charMin, charMax);
            }
            buffer.append((char) randomLimitedInt);
        }
        return signOfCode + buffer.toString();
    }
    
    public static Map<String, Object> convertStringToMap(String input){
        input = org.apache.commons.lang3.StringUtils.substringBetween(input, "{", "}");         //remove curly brackets
        String[] keyValuePairs = input.split(",");              //split the string to creat key-value pairs
        Map<String, Object> map = new HashMap<>();

        for(String pair : keyValuePairs)                        //iterate over the pairs
        {
            String[] entry = pair.split("=");                   //split the pairs to get key and value
            if(entry.length <2){
                map.put(entry[0].trim(), "");
            }else{
                map.put(entry[0].trim(), entry[1].trim());
            }
        }
        return map;
    }
    
}

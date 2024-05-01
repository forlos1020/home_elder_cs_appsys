package com.user.utils;


import com.user.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: LSH
 * @Date: 2023/6/25-06-25-11:22
 * @Description: com.SwordBBS.utils
 * @Version: 1.0
 */
public class VerifyUtils {

    public static boolean verify(String regex,String valueOf){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valueOf);
        if (!matcher.matches()) {
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean verify(VerifyRegexEnum regex, String valueOf) {
        return verify(regex.getRegex(), valueOf);
    }
}

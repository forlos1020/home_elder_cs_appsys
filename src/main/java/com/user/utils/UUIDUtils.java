package com.user.utils;

import java.util.UUID;

/**
 * @author： lsh
 * @create： 2023-12-06 15:23
 */
public class UUIDUtils {

    public static String get8UUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").substring(0, 8);
    }

    public static String get16UUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").substring(0, 16);
    }


    public static String get24UUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").substring(0, 24);
    }

    public static String get32UUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").substring(0, 32);
    }
}

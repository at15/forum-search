package cn.edu.sjtu.at15.forum.common;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by gpl on 15/11/19.
 */
public class StringUtils {
    public static String encodeBase64(String str) {
        return new String(Base64.encodeBase64(str.getBytes()));
    }

    public static String decodeBase64(String encoded) {
        return new String(Base64.decodeBase64(encoded));
    }
}

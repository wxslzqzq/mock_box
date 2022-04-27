package com.zhtty.mock.box.utils;

import com.zhtty.mock.box.exception.BizException;
import com.zhtty.mock.box.exception.ExceptionMessageEnum;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtils {
    public static String getHashedPassword(String password, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] pwdBytes = messageDigest.digest();
            messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] saltBytes = messageDigest.digest();
            byte[] newBytes = new byte[pwdBytes.length + saltBytes.length];
            System.arraycopy(pwdBytes, 0, newBytes, 0, pwdBytes.length);
            System.arraycopy(saltBytes, 0, newBytes, pwdBytes.length, saltBytes.length);
            messageDigest.update(newBytes);
            return Base64.getEncoder().encodeToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new BizException(ExceptionMessageEnum.ENCRYPTION_ERROR);
        }
    }
    public static String getSalt(String username,String password){
        String s = username + password;
        return String.valueOf(s.hashCode());
    }
}

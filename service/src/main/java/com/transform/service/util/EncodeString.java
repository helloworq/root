package com.transform.service.util;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;

@Service
public class EncodeString {
    /**
     * 将输入的字符串使用SHA算法加密，然后返回加密后的字符串
     * @param inputStr
     * @return
     */
    public String encodePasswordWithSHA(String inputStr){
        BigInteger sha =null;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {e.printStackTrace();}
        return sha.toString(32);
    }

}

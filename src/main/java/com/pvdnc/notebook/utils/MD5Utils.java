package com.pvdnc.notebook.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String toHash(String str) {
        byte[] data=str.getBytes(StandardCharsets.UTF_8);
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(data);
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(str.hashCode());
        }
        return cacheKey;
    }

    /**
     * Convert bytes data into hex string
     * @author http://stackoverflow.com/questions/332079
     * @param data data to transfer
     * @return hex string of data
     */
    private static String bytesToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String hex = Integer.toHexString(0xFF & data[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}

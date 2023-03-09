package com.demo.myjavademo.utils.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 */
public class MD5 {

    /**
     * MD5转换
     *
     * @param inStr 字符串
     * @return 转换字符串
     * @throws NoSuchAlgorithmException 转换异常
     */
    public static String encodeMD5Lowercase(String inStr) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] byteArray = inStr.getBytes();
        byte[] md5Bytes = md5.digest(byteArray);
        return byte2hex(md5Bytes);
    }

    /**
     * 对字符串进行MD5编码
     *
     * @param origin 字符串
     * @return 编码字符串
     */
    public static String encodeMD5Uppercase(String origin) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = origin.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(encodeMD5Lowercase("70028061678348015e02703d5c35466784639b5ae65a8da43"));
    }

    /**
     * md5处理
     *
     * @param md5Bytes 字符串信息
     * @return 处理后信息
     */
    private static String byte2hex(byte[] md5Bytes) {
        StringBuilder hexValue = new StringBuilder();
        int val;
        for (byte md5Byte : md5Bytes) {
            val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}

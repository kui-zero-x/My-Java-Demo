package com.demo.myjavademo.utils.encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESEncryptUtil {

    public static void main(String[] args) {
//        System.out.println(aesEncrypt("18700738616", "8008995Aa123456"));
        System.out.println(aesDecrypt("TY2MzMDM3Mjg5ZjdmZTI3NGQ5ZDQ5ZDE1Y2JlMjQxZGI=", "8008995Aa123456"));
    }

    /**
     * 包装AES加密方法
     *
     * @param encryptedText 需加密的文本
     * @param password 密码
     * @return 加密后的文本
     */
    public static String aesEncrypt(String encryptedText, String password) {

        if (encryptedText == null || "".equals(encryptedText)) {
            return encryptedText;
        }
        encryptedText = encrypt(encryptedText, password);
        // 使用Base64进行第一次加密，并增加字符串 T 作为开头
        encryptedText = "T" + Base64.getEncoder().encodeToString(encryptedText.getBytes(StandardCharsets.UTF_8));
        return encryptedText;
    }

    /**
     * AES加密方法
     *
     * @param encryptedText 需加密的文本
     * @param password 密码
     * @return 加密后的文本
     */
    private static String encrypt(String encryptedText, String password) {

        if (password == null) {
            password = "";
        }

        try {
            // 创建对称加密密钥生成器
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 创建随机数生成器
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // 设置AICC系统中的加密种子为随机数生成器的随机种子
            secureRandom.setSeed(password.getBytes());
            // 初始化密钥生成器
            kgen.init(128, secureRandom);
            // 生成密钥
            SecretKey secretKey = kgen.generateKey();
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
            // 创建密码器并初始化
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            // 获取加密后的电话号码(parseByte2HexStr方法为将二进制数组转为16进制字符串)
            byte[] result = cipher.doFinal(encryptedText.getBytes("utf-8"));
            encryptedText = parseByte2HexStr(result);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException
                | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("decrypt error", e);
        }
        return encryptedText;
    }

    /**
     * 二进制字节数组转16进制字符串
     *
     * @param buf 二进制字节数组
     * @return 16进制字符串
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toLowerCase());
        }

        return sb.toString();
    }


    /**
     * 包装AES解密方法
     *
     * @param encryptedText 需解密的文本
     * @param password 密码
     * @return 解密后的文本
     */
    public static String aesDecrypt(String encryptedText, String password) {

        // 判断加密号码串是否以 T 开头，是则去除T然后得到处理后的串encryptedText，否则非本系统加密后得到加密串，无法进行解密。
        if (encryptedText == null || !encryptedText.startsWith("T")) {
            return encryptedText;
        }
        encryptedText = encryptedText.substring(1);

        // 使用Base64进行第一次解密
        encryptedText = new String(Base64.getDecoder().decode(encryptedText.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);

        return decrypt(encryptedText, password);
    }

    /**
     * AES解密方法
     *
     * @param encryptedText 需解密的文本
     * @param password 密码
     * @return 解密后的文本
     */
    private static String decrypt(String encryptedText, String password) {

        if (password == null) {
            password = "";
        }

        String plainText = null;
        try {
            // 创建对称加密密钥生成器
            KeyGenerator kgen = KeyGenerator.getInstance("AES");

            // 创建随机数生成器
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            // 设置AICC系统中的加密种子为随机数生成器的随机种子
            secureRandom.setSeed(password.getBytes());

            // 初始化密钥生成器
            kgen.init(128, secureRandom);

            // 生成密钥
            SecretKey secretKey = kgen.generateKey();
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

            // 创建密码器并初始化
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            // 获取解密后的电话号码(parseHexStr2Byte方法为将16进制字符串转为二进制数组)
            byte[] result = cipher.doFinal(parseHexStr2Byte(encryptedText));
            plainText = new String(result);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException | NumberFormatException e) {
            throw new RuntimeException("decrypt error", e);
        }

        return plainText;
    }

    /**
     * 16进制字符串转二进制字节数组
     *
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }

        byte[] result = new byte[hexStr.length() / 2];

        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}

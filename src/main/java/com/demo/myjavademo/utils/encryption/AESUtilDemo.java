package com.demo.myjavademo.utils.encryption;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESUtilDemo {

    /**
     * AES加密 CBC模式
     * @param data 加密内容
     * @param key 加密令牌
     * @param ivParameter 加密时使用的偏移量
     * @return
     */
    public static String encrypt(String data, String key, String ivParameter) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(data.getBytes("utf-8"));
            return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码。
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密 CBC模式
     * @param data
     * @param key
     * @param ivParameter
     * @return
     */
    public static String decrypt(byte[] data,  String key, String ivParameter) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(data);
            return new String(encrypted);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密 AES 默认加密模式
     *
     * @param content 需要加密的内容
     * @param key  加密密码
     * @param bucketSize  加密密码长度 128 192
     * @return
     */
    public static String encrypt(String content, String key, Integer bucketSize) {
        try {
            int keySize = 128;
            if (bucketSize != null) {
                if (bucketSize == 128 || bucketSize == 192 || bucketSize == 256) {
                    keySize = bucketSize;
                }
            }
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            kgen.init(keySize, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(result); // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**解密 AES 默认解密模式
     * @param content  待解密内容
     * @param key 解密密钥
     * @param bucketSize 解密密钥长度
     * @return
     */
    public static String decrypt(byte[] content, String key, Integer bucketSize) {
        try {
            int keySize = 128;
            if (bucketSize != null) {
                if (bucketSize == 128 || bucketSize == 192 || bucketSize == 256) {
                    keySize = bucketSize;
                }
            }
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            kgen.init(keySize, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            return new String(result, "utf-8"); // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String key = "992259fdcba52ba6eade419ad783c3dd";
//        String number  = "18700738616";
        String number  = "15850205347";
        /**
         * AES 默认加密模式
         */
        String encodedNumber = encrypt(number, key, 128);
        System.out.println("默认模式加密后： number = " + encodedNumber);
        System.out.println("默认模式解密后： number = " + decrypt(Base64.decodeBase64(encodedNumber), key, 128));

//        /**
//         * AES CBC模式
//         */
//        String ivParamter = "0102030405060708";
//
//        try {
//            encodedNumber = encrypt(number, key, ivParamter);
//            System.out.println("CBC模式加密后： number = " + encodedNumber);
//            System.out.println("CBC模式解密后： number = " + decrypt(Base64.decodeBase64(encodedNumber), key, ivParamter));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

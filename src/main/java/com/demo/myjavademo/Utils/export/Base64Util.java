package com.demo.myjavademo.Utils.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Base64Util {

    /**
     * 文件（图片）转base64编码
     */
    public static String fileFormBase64(String localUrl) {
        // 读取文件
        File file = new File(localUrl);
        byte[] fileContent = new byte[(int) file.length()];
        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将文件内容转换为Base64编码
        String base64Encoded = java.util.Base64.getEncoder().encodeToString(fileContent);
        return base64Encoded;
    }
}

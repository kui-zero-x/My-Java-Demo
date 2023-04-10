package com.demo.myjavademo.utils.export.word;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;


/**
 * word导出工具
 */
public class WordExportUtil {
    private static final Logger logger = LoggerFactory.getLogger(WordExportUtil.class);

    public static boolean createWord(Map<String, Object> dataMap, String ftlFolderPath, String tempName, String outputFilePath) {
        logger.info("开始创建word到本地");
        boolean result = false;
        Configuration configuration = initConfiguration(ftlFolderPath);
        Template t = null;
        try {
            t = configuration.getTemplate(tempName, "UTF-8");
            t.setEncoding("utf-8");
//            t = configuration.getTemplate(tempName, "GB2312");
//            t.setEncoding("GB2312");

        } catch (IOException e) {
            logger.error("在路径：{}里找不到：{}", ftlFolderPath, tempName);
            e.printStackTrace();
        }
        File outFile = new File(outputFilePath);
        Writer out = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(outFile);
            OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
//            OutputStreamWriter oWriter = new OutputStreamWriter(fos, "GB2312");
//            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"GB2312"));

            t.process(dataMap, out);
            out.close();
            fos.close();
            result = true;
            logger.info("word创建成功");
        } catch (FileNotFoundException e) {
            logger.error("文件不存在：{}", outFile);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            logger.error("未知的编码格式");
            e.printStackTrace();
        } catch (TemplateException e) {
            logger.error("模板异常");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IO异常");
            e.printStackTrace();
        }

        return result;
    }

    private static Configuration initConfiguration(String folderPath) {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
//        configuration.setDefaultEncoding("GB2312");
        try {
            configuration.setDirectoryForTemplateLoading(new File(folderPath));
        } catch (IOException e) {
            logger.error("初始化configuration失败，路径不存在：{}", folderPath);
            e.printStackTrace();
        }
        return configuration;
    }
}

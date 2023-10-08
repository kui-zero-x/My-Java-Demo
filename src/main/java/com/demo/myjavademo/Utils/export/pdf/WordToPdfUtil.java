package com.demo.myjavademo.Utils.export.pdf;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class WordToPdfUtil {

    private static final Logger logger = LoggerFactory.getLogger(WordToPdfUtil.class);

    /**
     * 加载license 用于破解 不生成水印
     */
    private static void getLicense() {
        try (InputStream is = WordToPdfUtil.class.getClassLoader().getResourceAsStream("License.xml")) {
            License license = new License();
            license.setLicense(is);
        } catch (Exception e) {
            logger.error("WordToPdfUtil==getLicense===:" + e);
//            e.printStackTrace();
        }
    }

    /**
     * word转pdf
     *
     * @param wordPath word文件保存的路径
     * @param pdfPath  转换后pdf文件保存的路径
     */
    public static void wordToPdf(String wordPath, String pdfPath) {
        getLicense();
        File file = new File(pdfPath);
        try (FileOutputStream os = new FileOutputStream(file)) {
            Document doc = new Document(wordPath);
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            logger.error("WordToPdfUtil==word2pdf===:" + e);
//            e.printStackTrace();
        }
    }

}

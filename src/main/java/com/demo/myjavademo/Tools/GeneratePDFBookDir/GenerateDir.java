package com.demo.myjavademo.Tools.GeneratePDFBookDir;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

public class GenerateDir {

    public static void main(String[] args) throws IOException {
        // Load PDF document
        PDDocument document = PDDocument.load(new File("your-pdf-file.pdf"));
        // Get table of contents
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);

        List<String> titles = new ArrayList<>();
        // Parse text to find titles
        // ...

        // Create table of contents page
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("Table of Contents");
        contentStream.endText();

        int y = 650;
        for (String title : titles) {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(100, y);
            contentStream.showText(title);
            contentStream.endText();
            y -= 20;
        }

        contentStream.close();

        // Save PDF document
        document.save(new File("your-output-pdf-file.pdf"));

        // Close PDF document
        document.close();
    }
}

package ru.hr;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class PdfReader {
    public String getDescription() {
        try {
            File file = new File("C:\\Users\\Владислав\\Desktop\\Резюме Ерохин Дмитрий Анатольевич.pdf");
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getArticleStart();
            String[] strings = text.split("\n");
            document.close();
            return strings[0];
        } catch (Exception e){
          return "error";
        }
        }
}

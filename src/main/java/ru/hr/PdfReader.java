package ru.hr;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PdfReader {
    public Set<File> listFilesUsingFilesList(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toFile)
                    .collect(Collectors.toSet());
        }
    }
    public String getDescription() {
        try {
            String directory ="C:\\Users\\Владислав\\Desktop\\cv";
            Set<File> files = listFilesUsingFilesList(directory);
            for (File file:files ) {
                PDDocument document = PDDocument.load(file);
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                String[] strings = text.split("\r\n");
                document.close();
                System.out.println(strings[1]);
            }
            return "resume";
        } catch (Exception e){
          return "error";
        }
        }
}

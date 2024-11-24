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
    public Set<String> listFilesUsingFilesList(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    public void parsePDF() {
        try {
            String directory = "C:\\Users\\Владислав\\Desktop\\cv";
            Set<String> fileNames = listFilesUsingFilesList(directory);
            for (String fileName : fileNames) {
                File file = new File(directory + "\\" + fileName);
                PDDocument document = PDDocument.load(file);
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                String[] strings = text.split("\r\n");
                document.close();
                String name = findName(strings);
                System.out.println(name);
                String number = findNumber(strings);
                System.out.println(number);
                String mail = findMail(strings);
                System.out.println(mail);
            }
        } catch (Exception e) {
        }
    }

    private String findName(String[] strings) {
        String name;
        if (strings[0].isBlank()) {
            name = strings[1];
        } else {
            name = strings[0];
        }
        return name;
    }

    private String findNumber(String[] lines) {
        for (String line : lines) {
            if (line.startsWith("+")) {
                return line;
            }
        }
        return "no number";

    }

    private String findMail(String[] lines) {
        for (String line : lines) {
            String[] words = line.replace("\u00a0"," ").split("\\s+");
            for (String word : words) {
                if (word.contains("@")) {
                    return word;
                }
            }
        }
        return "no mail";

    }
}

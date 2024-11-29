package ru.hr;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        PdfReader pdfReader = new PdfReader();
        ArrayList<Person> list=pdfReader.parsePDF();
        for (Person person:list) {
            System.out.println(person.getName());
            System.out.println(person.getNumber());
            System.out.println(person.getMail());
        }
        ExcelWriter writer = new ExcelWriter();
        writer.write(list);
    }
}

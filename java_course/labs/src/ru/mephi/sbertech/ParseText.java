package ru.mephi.sbertech;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.LinkedList;

public class ParseText {

     static String[] sentencesGet(String filename){
        try (FileInputStream inp = new FileInputStream(filename);
             Scanner scan = new Scanner(inp, "UTF-8") ) {
            scan.useDelimiter("[.?!]");

            LinkedList<String> sentences = new LinkedList<>();
            while (scan.hasNext()) {
                sentences.add(scan.next().replaceAll("\\r?\\n", " ").trim());
            }
            return sentences.toArray(new String[sentences.size()]);

        } catch (Exception e) {
            System.out.println("Something is wrong.");
            e.printStackTrace();
            return null;
        }
    }

    public static void main (String[] args) {
        String filename = "text.txt";
        for (String s : sentencesGet(filename)) {
            System.out.println(s);
        }
    }
}

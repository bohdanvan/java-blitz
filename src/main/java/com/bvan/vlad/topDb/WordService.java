package com.bvan.vlad.topDb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WordService {

    public List<String> getWords() {
        List<String> words = new ArrayList<>();
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("/home/vpalamarchuk/java-blitz/src/main/java/com/bvan/vlad/words"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc2.hasNextLine()) {
            Scanner s2 = new Scanner(sc2.nextLine());
            while (s2.hasNext()) {
                String s = s2.next();
                words.add(s);
            }
        }
        return words;
    }
}

package com.bvan.vlad.topDb;

public class Main {

    public static void main(String[] args) {

        WordService wordService = new WordService();

        TopDb topDb = new TopDb();
        System.out.println(topDb.findFrequencyOfWords(wordService.getWords()));
    }
}

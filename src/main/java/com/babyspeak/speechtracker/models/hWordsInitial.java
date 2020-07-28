package com.babyspeak.speechtracker.models;

public class hWordsInitial extends AbstractEntity{

    private String[] wordList = {"ham", "hand", "hat", "heart", "hill", "hook", "horse", "house", "hair", "head"};

    public hWordsInitial(String[] wordList) {
        this.wordList = wordList;
    }


    public String[] getWordList() {
        return wordList;
    }
    public hWordsInitial(){};
}

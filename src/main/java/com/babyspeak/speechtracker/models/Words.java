package com.babyspeak.speechtracker.models;

public class Words extends AbstractEntity{

    private String[] wordList = {"ham", "hand", "hat", "heart", "hill", "hook", "horse", "house", "hair", "head"};



    public Words() {

    }

    public Words(String[] wordList) {
        this.wordList = wordList;
    }

    public String[] getWordList() {
        return wordList;
    }

    public void setWordList(String[] wordList) {
        this.wordList = wordList;
    }
}

package com.babyspeak.speechtracker.models;
import javax.persistence.Entity;

@Entity
public class mWordsInitial extends AbstractEntity{


    private String word;

    private String image;

    public mWordsInitial(){};

    public mWordsInitial(String word, String image) {
        this.word = word;
        this.image = image;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

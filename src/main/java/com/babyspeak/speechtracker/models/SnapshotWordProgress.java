package com.babyspeak.speechtracker.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SnapshotWordProgress extends AbstractEntity{
    private String word;
    private String image;
    private int year; //= date.getYear();
    private int month; // = date.getMonthValue();
    private int day; //= date.getDayOfMonth();
    private String correct;
    private Integer userid;


    public SnapshotWordProgress(){};

    public SnapshotWordProgress(int id, @NotBlank @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String name, int year, int month, int day, String correct, String word, String image, int year1, int month1, int day1, Integer userid, String correct1) {
        super(id, name, year, month, day, correct);
        this.word = word;
        this.image = image;
        this.year = year1;
        this.month = month1;
        this.day = day1;
        this.correct = correct1;
        this.userid = userid;

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

    //@Override
    public int getYear() {
        return year;
    }

    //@Override
    public void setYear(int year) {
        this.year = year;
    }

    //@Override
    public int getMonth() {
        return month;
    }

    //@Override
    public void setMonth(int month) {
        this.month = month;
    }

    //@Override
    public int getDay() {
        return day;
    }

    //@Override
    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String getCorrect() {
        return correct;
    }

    @Override
    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}

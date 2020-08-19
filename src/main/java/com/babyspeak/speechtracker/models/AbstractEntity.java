package com.babyspeak.speechtracker.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.Month;
import java.util.Objects;
import java.time.LocalDate;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    private int id;




    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    public AbstractEntity(){};

    public AbstractEntity(int id, @NotBlank @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String name, int year, int month, int day, String correct) {
        this.id = id;
        this.name = name;
//        this.year = year;
//        this.month = month;
//        this.day = day;
        this.correct = correct;
    }

//    private int year; //= date.getYear();
//    private int month; // = date.getMonthValue();
//    private int day; //= date.getDayOfMonth();


    private String correct;

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }
//
//    public int getMonth() {
//        return month;
//    }
//
//    public void setMonth(int month) {
//        this.month = month;
//    }
//
//    public int getDay() {
//        return day;
//    }
//
//    public void setDay(int day) {
//        this.day = day;
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }






    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

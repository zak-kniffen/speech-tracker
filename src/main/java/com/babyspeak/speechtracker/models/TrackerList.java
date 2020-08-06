package com.babyspeak.speechtracker.models;

import java.util.ArrayList;
import java.util.List;

public class TrackerList {



    private List<AbstractEntity>  tracker = new ArrayList<>();

    public TrackerList(){};

    public TrackerList(List<AbstractEntity> tracker) {
        this.tracker = tracker;
    }

    public List<AbstractEntity> getTracker() {
        return tracker;
    }

    public void setTracker(List<AbstractEntity> tracker) {
        this.tracker = tracker;
    }
}

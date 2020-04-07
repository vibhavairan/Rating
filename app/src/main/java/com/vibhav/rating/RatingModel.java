package com.vibhav.rating;

import java.util.Map;

public class RatingModel {
    private long rating;
    private String date;
    private String time;

    public RatingModel()
    {}

    public RatingModel(long rating, String date, String time) {
        this.rating = rating;
        this.date = date;
        this.time = time;
    }



    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
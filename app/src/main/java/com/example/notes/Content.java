package com.example.notes;

public class Content {
    private int _id;
    private String title;
    private String deskripsi;
    private String date;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return deskripsi;
    }

    public void setDesc(String desc) {
        this.deskripsi = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

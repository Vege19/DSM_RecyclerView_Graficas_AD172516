package com.example.recyclerviewgraphview.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.io.Serializable;
import java.util.List;

@Entity
public class Purpose implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "purpose_id")
    public int purpose_id;

    @ColumnInfo(name = "purpose_title")
    public String purpose_title;

    @ColumnInfo(name = "purpose_description")
    public String purpose_description;

    @ColumnInfo(name = "purpose_date")
    public String purpose_date;


    public Purpose(String purpose_title, String purpose_description, String purpose_date) {
        this.purpose_title = purpose_title;
        this.purpose_description = purpose_description;
        this.purpose_date = purpose_date;
    }

    public int getPurpose_id() {
        return purpose_id;
    }

    public void setPurpose_id(int purpose_id) {
        this.purpose_id = purpose_id;
    }

    public String getPurpose_title() {
        return purpose_title;
    }

    public void setPurpose_title(String purpose_title) {
        this.purpose_title = purpose_title;
    }

    public String getPurpose_description() {
        return purpose_description;
    }

    public void setPurpose_description(String purpose_description) {
        this.purpose_description = purpose_description;
    }

    public String getPurpose_date() {
        return purpose_date;
    }

    public void setPurpose_date(String purpose_date) {
        this.purpose_date = purpose_date;
    }

}


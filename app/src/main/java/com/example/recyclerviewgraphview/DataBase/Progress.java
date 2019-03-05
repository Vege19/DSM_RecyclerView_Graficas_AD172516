package com.example.recyclerviewgraphview.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Purpose.class,
parentColumns = "purpose_id",
childColumns = "purpose_id"))
public class Progress {

    @PrimaryKey(autoGenerate = true)
    public int progress_id;

    @ColumnInfo(name = "progress_event")
    public String progress_event;

    @ColumnInfo(name = "progress_percentage")
    public double progress_percentage;

    @ColumnInfo(name = "purpose_id")
    public int purpose_id;

    public Progress(String progress_event, double progress_percentage, int purpose_id) {
        this.progress_event = progress_event;
        this.progress_percentage = progress_percentage;
        this.purpose_id = purpose_id;
    }

    public int getProgress_id() {
        return progress_id;
    }

    public void setProgress_id(int progress_id) {
        this.progress_id = progress_id;
    }

    public String getProgress_event() {
        return progress_event;
    }

    public void setProgress_event(String progress_event) {
        this.progress_event = progress_event;
    }

    public double getProgress_percentage() {
        return progress_percentage;
    }

    public void setProgress_percentage(double progress_percentage) {
        this.progress_percentage = progress_percentage;
    }

    public int getPurpose_id() {
        return purpose_id;
    }

    public void setPurpose_id(int purpose_id) {
        this.purpose_id = purpose_id;
    }
}

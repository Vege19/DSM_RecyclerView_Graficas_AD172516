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

}

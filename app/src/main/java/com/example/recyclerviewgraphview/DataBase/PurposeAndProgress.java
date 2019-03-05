package com.example.recyclerviewgraphview.DataBase;

import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

public class PurposeAndProgress {

    @Relation(parentColumn = "progress_id", entityColumn = "purpose_id")
    public List<Progress> progresses;

}

package com.example.recyclerviewgraphview.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Purpose.class, Progress.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PurposeDao purposeDao();
}

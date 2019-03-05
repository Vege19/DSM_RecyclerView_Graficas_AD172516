package com.example.recyclerviewgraphview.DataBase;

import android.arch.persistence.room.Database;

@Database(entities = {Purpose.class}, version = 1)
public abstract class AppDataBase {

    public abstract PurposeDao purposeDao();
}

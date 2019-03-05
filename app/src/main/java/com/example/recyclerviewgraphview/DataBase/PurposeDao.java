package com.example.recyclerviewgraphview.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PurposeDao {

    //Get a list of all purposes
    @Query("SELECT * FROM purpose")
    List<Purpose> getAll();

    //Insert a purpose
    @Insert
    void insertPurpose(Purpose... purposes);


}

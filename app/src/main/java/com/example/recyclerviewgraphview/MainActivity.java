package com.example.recyclerviewgraphview;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.recyclerviewgraphview.DataBase.AppDataBase;

public class MainActivity extends AppCompatActivity {

    public static AppDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize database
        dataBaseBuild();
    }

    void dataBaseBuild() {
        dataBase = Room.databaseBuilder(this,
                AppDataBase.class,
                "purpose_database")
                .allowMainThreadQueries()
                .build();

    }

}

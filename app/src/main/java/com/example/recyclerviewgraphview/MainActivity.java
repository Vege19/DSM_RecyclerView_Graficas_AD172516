package com.example.recyclerviewgraphview;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.recyclerviewgraphview.Adapters.PurposeAdapter;
import com.example.recyclerviewgraphview.DataBase.AppDataBase;
import com.example.recyclerviewgraphview.DataBase.Purpose;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static AppDataBase dataBase;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize database
        dataBaseBuild();

        //Setup recyclerview
        recyclerViewSetup();

    }

    void dataBaseBuild() {
        dataBase = Room.databaseBuilder(this,
                AppDataBase.class,
                "purpose_database")
                .allowMainThreadQueries()
                .build();

    }

    void recyclerViewSetup() {

        List<Purpose> purposes = dataBase.purposeDao().getAll();

        mRecyclerView = findViewById(R.id.purposeRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        mRecyclerView.setAdapter(new PurposeAdapter(purposes, MainActivity.this));

    }

}

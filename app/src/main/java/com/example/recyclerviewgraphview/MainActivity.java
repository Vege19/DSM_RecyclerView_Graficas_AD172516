package com.example.recyclerviewgraphview;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toolbar;

import com.example.recyclerviewgraphview.Adapters.PurposeAdapter;
import com.example.recyclerviewgraphview.DataBase.AppDataBase;
import com.example.recyclerviewgraphview.DataBase.Purpose;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static AppDataBase dataBase;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Custom Toolbar setup
        mToolBar = findViewById(R.id.mainToolBar);
        setActionBar(mToolBar);

        //Initialize database
        dataBaseBuild();

        //Setup recyclerview
        recyclerViewSetup();

        //Start activity with fab
        startAddPurposeActivity();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //RecyclerView refresh when the activity resumes
        recyclerViewSetup();

    }

    void dataBaseBuild() {

        dataBase = Room.databaseBuilder(this,
                AppDataBase.class,
                "purpose_database")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();

    }

    void recyclerViewSetup() {

        List<Purpose> purposes = dataBase.purposeDao().getAll();

        mRecyclerView = findViewById(R.id.purposeRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        mRecyclerView.setAdapter(new PurposeAdapter(purposes, MainActivity.this));

    }

    void startAddPurposeActivity() {

        mFab = findViewById(R.id.floatingActionButton);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPurposeActivity.class);
                startActivity(intent);

            }
        });

    }

}

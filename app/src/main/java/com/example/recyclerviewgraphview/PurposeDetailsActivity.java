package com.example.recyclerviewgraphview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recyclerviewgraphview.Adapters.ProgressAdapter;
import com.example.recyclerviewgraphview.DataBase.Progress;
import com.example.recyclerviewgraphview.DataBase.Purpose;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

public class PurposeDetailsActivity extends AppCompatActivity {

    private EditText insertEvent, insertPercentage;
    private TextView description;
    private RecyclerView progressRecyclerView;
    private Button addProgressButton;
    private List<Progress> progresses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purpose_details);

        getSupportActionBar().setTitle(purpose().getPurpose_title());

        recyclerViewSetUp();

        initContent();

    }

    private Purpose purpose() {
        //get Parcelable extra
        final Purpose purpose = getIntent().getParcelableExtra("details");

        return purpose;

    }

    private void initContent() {

        description = findViewById(R.id.purposeDetailDescription);
        description.setText(purpose().getPurpose_description());

        //get views and add a new progress
        insertEvent = findViewById(R.id.insertEvent);
        insertPercentage = findViewById(R.id.insertPercentage);

        //add progress
        addProgressButton = findViewById(R.id.addProgressButton);
        addProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert progress
                MainActivity.dataBase.purposeDao().insertProgress(new Progress(insertEvent.getText().toString(),
                        Double.parseDouble(insertPercentage.getText().toString()),
                        purpose().getPurpose_id()));

                //refresh recyclerview
                recyclerViewSetUp();

            }
        });
    }

    private void recyclerViewSetUp() {

        //set db list in this list
        progresses = MainActivity.dataBase.purposeDao().findProgressForPurpose(purpose().getPurpose_id());
        progressRecyclerView = findViewById(R.id.progressRecyclerView);
        progressRecyclerView.setLayoutManager(new GridLayoutManager(PurposeDetailsActivity.this, 1));
        progressRecyclerView.setAdapter(new ProgressAdapter(progresses, PurposeDetailsActivity.this));

    }
}

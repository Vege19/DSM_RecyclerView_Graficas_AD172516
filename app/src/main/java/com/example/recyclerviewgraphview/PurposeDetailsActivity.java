package com.example.recyclerviewgraphview;

import android.graphics.Color;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

public class PurposeDetailsActivity extends AppCompatActivity {

    private EditText insertEvent, insertPercentage;
    private TextView description;
    private RecyclerView progressRecyclerView;
    private Button addProgressButton;
    private List<Progress> progresses = new ArrayList<>();
    private LineChart lineChart;

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

        //graph build
        lineChart = findViewById(R.id.graph);

        List<Entry> entries = new ArrayList<>();

        for (Progress progress : progresses) {
            entries.add(new Entry((float)progress.getProgress_id(), (float)progress.getProgress_percentage()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

    }

    private void recyclerViewSetUp() {

        //set db list in this list
        progresses = MainActivity.dataBase.purposeDao().findProgressForPurpose(purpose().getPurpose_id());
        progressRecyclerView = findViewById(R.id.progressRecyclerView);
        progressRecyclerView.setLayoutManager(new GridLayoutManager(PurposeDetailsActivity.this, 1));
        progressRecyclerView.setAdapter(new ProgressAdapter(progresses, PurposeDetailsActivity.this));

    }
}

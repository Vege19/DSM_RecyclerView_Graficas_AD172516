package com.example.recyclerviewgraphview;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.recyclerviewgraphview.Adapters.ProgressAdapter;
import com.example.recyclerviewgraphview.DataBase.Progress;
import com.example.recyclerviewgraphview.DataBase.Purpose;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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
    private Toolbar mToolBar;
    private LinearLayout bottomSheet;
    private ImageView addEvent, closeBottomSheet;
    private static View darkBackground;
    private static BottomSheetBehavior bsb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purpose_details);

        //Toolbar setup
        mToolBar = findViewById(R.id.purposeDetailsToolBar);
        setActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.ic_round_arrow_back_24px);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurposeDetailsActivity.this.finish();

            }
        });

        getActionBar().setTitle(purpose().getPurpose_title());

        recyclerViewSetUp();

        initContent();

    }

    private Purpose purpose() {
        //get Parcelable extra
        final Purpose purpose = getIntent().getParcelableExtra("details");

        return purpose;

    }

    private void initContent() {

        bottomSheetSetUp();

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

                bsb.setState(BottomSheetBehavior.STATE_HIDDEN);
                darkBackground.setVisibility(View.INVISIBLE);

                //refresh recyclerview and graph
                recyclerViewSetUp();
                graphBuild();

            }
        });

        graphBuild();

    }

    private void graphBuild() {

        //graph build
        lineChart = findViewById(R.id.graph);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.animateX(3000);
        lineChart.animateY(3000);
        lineChart.invalidate();

        List<Entry> entries = new ArrayList<>();

        for (Progress progress : progresses) {
            entries.add(new Entry((float) progress.getProgress_id(), (float) progress.getProgress_percentage()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Porcentaje");
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setDrawCircles(false);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(getResources().getColor(R.color.colorAccent));
        dataSet.setColor(getResources().getColor(R.color.colorAccent));

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();

    }

    private void recyclerViewSetUp() {

        //set db list in this list
        progresses = MainActivity.dataBase.purposeDao().findProgressForPurpose(purpose().getPurpose_id());
        progressRecyclerView = findViewById(R.id.progressRecyclerView);
        progressRecyclerView.setLayoutManager(new GridLayoutManager(PurposeDetailsActivity.this, 1));
        progressRecyclerView.setAdapter(new ProgressAdapter(progresses, PurposeDetailsActivity.this));

    }

    private void bottomSheetSetUp() {

        //Bottom sheet
        bottomSheet = findViewById(R.id.bottomSheet);
        addEvent = findViewById(R.id.addNewEvent);
        closeBottomSheet = findViewById(R.id.closeBottomSheet);
        darkBackground = findViewById(R.id.darkBackground);

        bsb = BottomSheetBehavior.from(bottomSheet);
        //bottom sheet is hidden
        bsb.setState(BottomSheetBehavior.STATE_HIDDEN);

        //when user touches the add event button
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bottom sheet will expand
                bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
                darkBackground.setVisibility(View.VISIBLE);

            }
        });

        closeBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bsb.setState(BottomSheetBehavior.STATE_HIDDEN);
                darkBackground.setVisibility(View.INVISIBLE);

            }
        });

    }

}

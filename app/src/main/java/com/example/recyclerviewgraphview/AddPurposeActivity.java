package com.example.recyclerviewgraphview;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.recyclerviewgraphview.DataBase.Purpose;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddPurposeActivity extends AppCompatActivity {

    private Button addPurpose;
    private Toolbar mToolBar;
    private TextView dateText;
    private EditText getTitle, getDescription;
    private DatePickerDialog.OnDateSetListener mOnDataSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purpose);

        //Get views
        getTitle = findViewById(R.id.insertTitle);
        getDescription = findViewById(R.id.insertDescription);
        addPurpose = findViewById(R.id.addPurposeButton);

        //toolbar setup
        mToolBar = findViewById(R.id.addPurposeToolBar);
        setActionBar(mToolBar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationIcon(R.drawable.ic_round_arrow_back_24px);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPurposeActivity.this.finish();
            }
        });

        //get date
        dateText = findViewById(R.id.insertDate);
        //set current date
        String currentDate = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault()).format(new Date());
        dateText.setText(currentDate);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate();
            }
        });

        addPurpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get final data
                String title = getTitle.getText().toString().trim();
                String description = getDescription.getText().toString().trim();
                String date = dateText.getText().toString().trim();

                //validations
                if (title.isEmpty() || getTitle.equals("")) {
                    getTitle.setError("Por favor ingrese un título para el propósito");
                    getTitle.requestFocus();
                    return;
                }

                if (description.isEmpty() || description.equals("")) {
                    getDescription.setError("Por favor ingrese una breve descripción");
                    getDescription.requestFocus();
                    return;
                }

                //insert if all data are valid
                insertPurpose(title, description, date);
            }
        });


    }

    private void pickDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(AddPurposeActivity.this,
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                mOnDataSetListener,
                year, month, day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.show();

        mOnDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                dateText.setText(date);
            }
        };

    }



    void insertPurpose(String title, String description, String date) {
        //Add new purpose to database list
        MainActivity.dataBase.purposeDao().insertPurpose(new Purpose(title, description, date));
        //This activity will close
        AddPurposeActivity.this.finish();

    }

}

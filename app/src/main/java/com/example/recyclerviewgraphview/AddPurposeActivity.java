package com.example.recyclerviewgraphview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.recyclerviewgraphview.DataBase.Purpose;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddPurposeActivity extends AppCompatActivity {

    private Button addPurpose;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purpose);

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

        initContent();

    }

    void initContent() {

        //Get views
        final EditText mGetTitle = findViewById(R.id.insertTitle);
        final EditText mGetDescription = findViewById(R.id.insertDescription);
        final EditText mGetDate = findViewById(R.id.insertDate);

        //Get user's current date and set it on date EditText
        String date = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault()).format(new Date());
        mGetDate.setText(date);

        //Add button event
        addPurpose = findViewById(R.id.addPurposeButton);
        addPurpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add new purpose to database list
                MainActivity.dataBase.purposeDao().insertPurpose(new Purpose(mGetTitle.getText().toString(),
                        mGetDescription.getText().toString(),
                        mGetDate.getText().toString()));
                //This activity will close
                AddPurposeActivity.this.finish();

            }
        });

    }

}

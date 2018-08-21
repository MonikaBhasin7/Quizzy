package com.techsdm.quizzy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class Result extends AppCompatActivity {

    PieChart pieChart;
    TextView textView;
    int data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle=getIntent().getExtras();
        data=bundle.getInt("name");


        textView=(TextView)findViewById(R.id.textView);
        textView.setText(data);
    }
}

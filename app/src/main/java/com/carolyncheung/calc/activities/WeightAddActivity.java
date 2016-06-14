package com.carolyncheung.calc.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.data.PlateData;

import java.util.ArrayList;

/**
 * Created by Carolyn Cheung on 6/13/2016.
 */
public class WeightAddActivity extends AppCompatActivity {
    private TextView weight_input;
    private RelativeLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightadd);

        frame = (RelativeLayout) findViewById(R.id.frame);
        weight_input = (TextView) findViewById(R.id.weight);

        DBHandler db = new DBHandler(this);
        ArrayList<PlateData> p = new ArrayList<PlateData>();
    }

    public void calculateAddedWeight(double weight, ArrayList<PlateData> plates) {

    }
}
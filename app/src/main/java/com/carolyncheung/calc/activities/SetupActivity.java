package com.carolyncheung.calc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.helpers.SharedPreferencesHelper;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Carolyn Cheung on 2/28/2016.
 */
public class SetupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        if (SharedPreferencesHelper.getBool(this, SharedPreferencesHelper.USER_SETUP, true)) {
            userSetup();
        } else {
            createDB();
        }


        // user is all setup and g2g
        SharedPreferencesHelper.putBool(SetupActivity.this,
                SharedPreferencesHelper.USER_SETUP, true);

        Intent intent = new Intent(SetupActivity.this, WeightCalculateActivity.class);
        startActivity(intent);
    }

    public void createDB() {
        DBHandler db = new DBHandler(this);

        try {
            db.createDataBase();;
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            db.openDatabase();
        } catch (SQLException sqle) {
            throw new Error ("Couldn't open database?");
        }

        db.close();
    }

    public void userSetup() {
        Intent intent = new Intent(this, WeightCalculateActivity.class);
        finish();
        startActivity(intent);
  //      finish();
    }

}

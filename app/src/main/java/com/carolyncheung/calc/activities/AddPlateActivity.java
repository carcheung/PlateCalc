package com.carolyncheung.calc.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.data.Constant;
import com.carolyncheung.calc.data.PlateData;
import com.carolyncheung.calc.fragments.ActionBarFragment;

/**
 * Created by Carolyn Cheung on 6/17/2016.
 * Activity to create a new plate
 */
public class AddPlateActivity extends AppCompatActivity implements View.OnClickListener{
    private PlateData plate = new PlateData();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplate);

        // create bundle and store string key values
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FROM_ACTIVITY, Constant.ACTIVITY_ADDPLATE);

        // dynamically create toolbar
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // add arguments and tag/commit
        ActionBarFragment save_return = new ActionBarFragment();
        save_return.setArguments(bundle);
        fragmentTransaction.add(R.id.toolbar_container, save_return, "save_return");
        fragmentTransaction.commit();

        final DBHandler db = new DBHandler(this);

        for (int i = 0; i < 9; i++) {
            ImageView color_choice_IMG = (ImageView)findViewById(Constant.color_choice_rID[i]);
            color_choice_IMG.setColorFilter(getResources().getColor(Constant.colors[i]), PorterDuff.Mode.ADD);
        }

        SeekBar plate_size = (SeekBar)findViewById(R.id.plate_size);
        plate_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            ImageView plate_IMG = (ImageView) findViewById(R.id.plate);
            int index = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                index = progress;
                plate_IMG.setImageResource(Constant.plate_sizes[progress]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something here, if you want to do anything at the start of
                // touching the seekbar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                plate_IMG.setImageResource(Constant.plate_sizes[index]);
                // database plates start from 1 why did I do that
                plate.setSize(index + 1);
            }
        });

        final EditText weight_edit = (EditText)findViewById(R.id.weight);
        weight_edit.setOnClickListener(new EditText.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight_edit.selectAll();
            }
        });

/*      test code to test DBHandler addPlate & removePlate
        final PlateData pd = new PlateData();
        pd.setAmount(1);
        pd.setSize(1);
        pd.setColor("black");
        pd.setUnit(2);
        pd.setWeight(100);
        pd.setId(20);

        ImageView test = (ImageView) findViewById(R.id.color_3_3);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addPlate(pd);
            }
        });

        ImageView test2 = (ImageView) findViewById(R.id.color_1_1);
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HELLO", "removing");
                db.removePlate(pd);
            }
        }); */
    }

    @Override
    public void onClick(View v) {
        // TODO: FILL IN CASE / COLOR and UNITS etc
        ImageView plate_color = (ImageView)findViewById(R.id.plate);
        TextView kg = (TextView)findViewById(R.id.kg);
        TextView lbs = (TextView)findViewById(R.id.lbs);
        switch(v.getId()) {
            case R.id.color_1_1:
                plate_color.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
                plate.setColor(Constant.RED);
                break;
            case R.id.color_2_1:
                plate_color.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
                plate.setColor(Constant.ORANGE);
                break;
            case R.id.color_3_1:
                plate_color.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
                plate.setColor(Constant.YELLOW);
                break;
            case R.id.color_1_2:
                plate_color.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
                plate.setColor(Constant.GREEN);
                break;
            case R.id.color_2_2:
                plate_color.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
                plate.setColor(Constant.BLUE);
                break;
            case R.id.color_3_2:
                plate_color.setBackgroundColor(ContextCompat.getColor(this, R.color.indigo));
                plate.setColor(Constant.INDIGO);
                break;
            case R.id.color_1_3:
                plate_color.setBackgroundColor(ContextCompat.getColor(this, R.color.violet));
                plate.setColor(Constant.VIOLET);
                break;
            case R.id.color_2_3:
                plate_color.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
                plate.setColor(Constant.BLACK);
                break;
            case R.id.color_3_3:
                plate_color.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                plate.setColor(Constant.WHITE);
                break;
            case R.id.kg:
                kg.setTextColor(ContextCompat.getColor(this, R.color.white));
                lbs.setTextColor(ContextCompat.getColor(this, R.color.lightGrey));
                plate.setUnit(1);
                break;
            case R.id.lbs:
                lbs.setTextColor(ContextCompat.getColor(this, R.color.white));
                kg.setTextColor(ContextCompat.getColor(this, R.color.lightGrey));
                plate.setUnit(2);
                break;
        }
    }

}

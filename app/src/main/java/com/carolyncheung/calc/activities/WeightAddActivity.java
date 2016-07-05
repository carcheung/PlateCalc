package com.carolyncheung.calc.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.data.Constant;
import com.carolyncheung.calc.data.PlateData;
import com.carolyncheung.calc.fragments.DisplayPlateSetAddFragment;
import com.carolyncheung.calc.helpers.CalculationsHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Carolyn Cheung on 6/13/2016.
 * Allows users to add up to 10 plates of any kind to the bar, will calculate the weight
 * based on the plates that the user has added
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

        LinearLayout show_plate_set = new LinearLayout(this);
        // get instance of FragmentTransaction from my activity
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // add the fragment to R.id.show_plate_set
        show_plate_set.setId(R.id.show_plate_set);
        DisplayPlateSetAddFragment displayPlateSetAddFragment =
                new DisplayPlateSetAddFragment();
        fragmentTransaction.add(show_plate_set.getId(), displayPlateSetAddFragment);
        fragmentTransaction.commit();

        DBHandler db = new DBHandler(this);
        ArrayList<PlateData> p = new ArrayList<PlateData>();

        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                return;
            }
        });

    }

    public void calculateAddedWeight(ArrayList<PlateData> plates) {
        //double weight = Double.parseDouble(weight_input.getText().toString());
        clearPlates();

        double weight = 0;
        double convert_weight = 0;
        int size = 1;
        String color;
        // TODO: CHANGE BAR WEIGHT LATER WHEN IMPLEMENTING BARBELLS
        weight += 45;

        int amt;
        int plate_counter = 0;
        int plateSize;
        int plateColor;

        int imgBarEnd = R.id.barEnd;
        ImageView barEnd = (ImageView)findViewById(imgBarEnd);
        ImageView pImg;
        TextView total_weight = (TextView)findViewById(R.id.weight);
        TextView converted_weight = (TextView)findViewById(R.id.convert_weight);

        // MARGIN VALUES CONVERTED TO DP
        int id;
        int dpValueLeft = 1;
        int dpValueRight = 2;
        int dpValueBottom = 10;
        float d = getApplicationContext().getResources().getDisplayMetrics().density;

        int marginLeft = (int) (dpValueLeft * d);
        int marginRight = (int) (dpValueRight * d);


        for(int i = 0; i < plates.size(); i++) {
            amt = plates.get(i).getAmount();
            size = plates.get(i).getSize();
            color = plates.get(i).getColor();
            if (amt > 0) {
                for (int a = 0; a < amt; a++) {
                    switch (size) {
                        case 1:
                            plateSize = R.drawable.plate_1_hdpi;
                            break;
                        case 2:
                            plateSize = R.drawable.plate_2_hdpi;
                            break;
                        case 3:
                            plateSize = R.drawable.plate_3_hdpi;
                            break;
                        case 4:
                            plateSize = R.drawable.plate_4_hdpi;
                            break;
                        case 5:
                            plateSize = R.drawable.plate_5_hdpi;
                            break;
                        case 6:
                            plateSize = R.drawable.plate_6_hdpi;
                            break;
                        case 7:
                            plateSize = R.drawable.plate_7_hdpi;
                            break;
                        default:
                            plateSize = R.drawable.plate_1_hdpi;
                            break;
                    }

                    switch (color) {
                        case "red":
                            plateColor = R.color.red;
                            break;
                        case "orange":
                            plateColor = R.color.orange;
                            break;
                        case "yellow":
                            plateColor = R.color.yellow;
                            break;
                        case "green":
                            plateColor = R.color.green;
                            break;
                        case "blue":
                            plateColor = R.color.blue;
                            break;
                        case "indigo":
                            plateColor = R.color.indigo;
                            break;
                        case "violet":
                            plateColor = R.color.violet;
                            break;
                        case "black":
                            plateColor = R.color.black;
                            break;
                        case "white":
                            plateColor = R.color.white;
                            break;
                        default:
                            plateColor = R.color.red;
                            break;
                    }

                    switch (plate_counter) {
                        case 0:
                            pImg = (ImageView) findViewById(R.id.plate1);
                            imgBarEnd = R.id.plate1;
                            break;
                        case 1:
                            pImg = (ImageView) findViewById(R.id.plate2);
                            imgBarEnd = R.id.plate2;
                            break;
                        case 2:
                            pImg = (ImageView) findViewById(R.id.plate3);
                            imgBarEnd = R.id.plate3;
                            break;
                        case 3:
                            pImg = (ImageView) findViewById(R.id.plate4);
                            imgBarEnd = R.id.plate4;
                            break;
                        case 4:
                            pImg = (ImageView) findViewById(R.id.plate5);
                            imgBarEnd = R.id.plate5;
                            break;
                        case 5:
                            pImg = (ImageView) findViewById(R.id.plate6);
                            imgBarEnd = R.id.plate6;
                            break;
                        case 6:
                            pImg = (ImageView) findViewById(R.id.plate7);
                            imgBarEnd = R.id.plate7;
                            break;
                        case 7:
                            pImg = (ImageView) findViewById(R.id.plate8);
                            imgBarEnd = R.id.plate8;
                            break;
                        case 8:
                            pImg = (ImageView) findViewById(R.id.plate9);
                            imgBarEnd = R.id.plate9;
                            break;
                        case 9:
                            pImg = (ImageView) findViewById(R.id.plate10);
                            imgBarEnd = R.id.plate10;
                            break;
                        default:
                            pImg = (ImageView) findViewById(R.id.plate1);
                            imgBarEnd = R.id.plate1;
                            break;
                    }

                    pImg.setImageResource(plateSize);
                    pImg.setBackgroundColor(getResources().getColor(plateColor));
                    weight += plates.get(i).getWeight();
                    plate_counter++;
                }
            }
        }

        if(plate_counter <= 0) {
            imgBarEnd = R.id.barMid;
        }

        RelativeLayout.LayoutParams barEndLp = new RelativeLayout.
                LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        // set the parameters for the barEnd
        barEndLp.setMargins(marginLeft, 0, marginRight, 0);
        barEndLp.addRule(17, imgBarEnd);
        barEndLp.addRule(15);

        barEnd.setLayoutParams(barEndLp);
        DecimalFormat format = new DecimalFormat("0.#");
        convert_weight = CalculationsHelper.convertWeight(weight);

        total_weight.setText(format.format(weight));
        converted_weight.setText(format.format(convert_weight));
    }

    public void clearPlates() {
        ImageView plate;
        for (int i = 0; i < 10; i++) {
            plate = (ImageView) findViewById(Constant.plate_rID[i]);
            plate.setImageDrawable(null);
            plate.setBackgroundColor(0x00000000);
            plate.destroyDrawingCache();
        }
    }
}
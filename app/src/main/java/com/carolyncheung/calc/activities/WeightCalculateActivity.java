package com.carolyncheung.calc.activities;

import android.content.Context;
import android.graphics.PorterDuff;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.data.PlateData;
import com.carolyncheung.calc.helpers.CalculationsHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Carolyn Cheung on 6/12/2016.
 * Calculates the amount of plates to put on a bar, given the weight
 */

public class WeightCalculateActivity extends AppCompatActivity {
    private EditText weight_input;
    private RelativeLayout frame;
    private ImageView inc_weight;
    private ImageView dec_weight;
    public ArrayList<Integer> plateID = new ArrayList<Integer>();
    public CalculationsHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightcalculate);

        frame = (RelativeLayout) findViewById(R.id.frame);
        weight_input = (EditText) findViewById(R.id.weight);
        inc_weight = (ImageView) findViewById(R.id.inc_weight);
        dec_weight = (ImageView) findViewById(R.id.dec_weight);

        DBHandler db = new DBHandler(this);
        ArrayList<PlateData> p = new ArrayList<PlateData>();
    //    p = db.getPlateSet(1);
    //    int a = p.get(0).getAmount();

        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = weight_input.getText().toString();
                if (TextUtils.isEmpty(temp)) {
                    Toast.makeText(WeightCalculateActivity.this,
                            "Enter weight", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    double weight = Double.parseDouble(temp);
                    DBHandler db = new DBHandler(getBaseContext());
                    ArrayList<PlateData> p = new ArrayList<PlateData>();

                    // unit id: 1 = kg, 2 = lbs
                    p = db.getPlateSet(2);
            //        int a = p.get(0).getAmount();

/*
                    PlateData p1 = new PlateData(1, "red", 1, 10, 90, 1);
                    PlateData p2 = new PlateData(1, "blue", 2, 10, 70, 1);
                    PlateData p3 = new PlateData(1, "green", 3, 10, 50, 1);
                    PlateData p4 = new PlateData(1, "yellow", 4, 10, 20, 1);
                    PlateData p5 = new PlateData(1, "violet", 5, 10, 10, 1);
                    PlateData p6 = new PlateData(1, "orange", 6, 10, 5, 1);
                    p.add(p1);
                    p.add(p2);
                    p.add(p3);
                    p.add(p4);
                    p.add(p5);
                    p.add(p6); */
                    // TODO: Change roundWeights, maybe make it so you can be exact
                    weight = helper.roundWeights(weight);
                    clearPlates();
                    calcuateLimited(weight, p);

                    return;
                }
            }
        });

        weight_input.setOnClickListener(new EditText.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight_input.selectAll();
            }
        });

        inc_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = weight_input.getText().toString();
                Double weight;
                if (temp.equals("")) {
                    weight = 0.0;
                } else {
                    weight = Double.parseDouble(temp);
                }
                // TODO: MODIFY TO BASE INCREASES OFF OF SAVED PREFERENCES
                weight += 5;
                weight_input.setText(Double.toString(weight));
                setConvertedWeight(weight);
            }
        });

        /* TODO: implement later
        inc_weight.setOnTouchListener(new View.OnTouchListener() {
            private Boolean pressed_down = false;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        pressed_down = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        pressed_down = false;
                        break;
                }
                if (pressed_down) {
                    String temp = weight_input.getText().toString();
                    Double weight;
                    if (temp.equals("")) {
                        weight = 0.0;
                    } else {
                        weight = Double.parseDouble(temp);
                    }
                    // TODO: MODIFY TO BASE INCREASES OFF OF SAVED PREFERENCES
                    weight += 5;
                    weight_input.setText(Double.toString(weight));
                }
                return false;
            }
        }); */

        dec_weight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String temp = weight_input.getText().toString();
                double weight;
                if (temp.equals("")) {
                    weight = 0.0;
                } else {
                    weight = Double.parseDouble(temp);
                }
                // TODO: MODIFY TO BASE INCREASES OFF OF SAVED PREFERENCES
                if (weight >= 5) {
                    weight -= 5;
                } else {
                    weight = 0;
                }
                weight_input.setText(Double.toString(weight));
                setConvertedWeight(weight);
            }
        });
    }

    public void clearPlates() {
        ImageView plate;
        for (int i = 0; i < this.plateID.size(); i++) {
            plate = (ImageView) findViewById(plateID.get(i));
            plate.setImageDrawable(null);
            plate.setBackgroundColor(0x00000000);
            plate.destroyDrawingCache();
        }
        this.plateID.clear();
    }

    public void setConvertedWeight(Double weight) {
        DecimalFormat format = new DecimalFormat("0.#");
        TextView converted_weight = (TextView)findViewById(R.id.convert_weight);
        Double convert_weight = CalculationsHelper.convertWeight(weight);
        converted_weight.setText(format.format(convert_weight));
    }

    public void calcuateLimited(double weight, ArrayList<PlateData> plates) {
        // CHANGE BAR WEIGHT LATER (?)
        double w = weight - 45;
        double temp;
        int i = 0;
        int k = 0;
        int numOfPlate, size;
        String color;

        int imgStart = R.id.barMid;
        int imgBarEnd = R.id.barEnd;
        ImageView barEnd = (ImageView)findViewById(imgBarEnd);

        setConvertedWeight(weight);

        // MARGIN VALUES CONVERTED TO DP
        int id;
        int dpValueLeft = 1;
        int dpValueRight = 2;
        int dpValueBottom = 10;
        float d = getApplicationContext().getResources().getDisplayMetrics().density;

        int marginLeft = (int) (dpValueLeft * d);
        int marginRight = (int) (dpValueRight * d);
        int marginBottom = (int) (dpValueBottom * d);

        int plateSize;
        int plateColor;

        do {
            if(plates.get(i).getAmount() > 0) {
                numOfPlate = (int) (w / plates.get(i).getWeight());
                if (numOfPlate > plates.get(i).getAmount()) {
                    numOfPlate = plates.get(i).getAmount();
                }
            } else {
                numOfPlate = 0;
            }
            temp = numOfPlate * plates.get(i).getWeight();
            w -= temp;

            if (numOfPlate > 0) {
                color = plates.get(i).getColor();
                size = plates.get(i).getSize();

                switch (size) {
                    case 1: plateSize = R.drawable.plate_1_hdpi;
                        break;
                    case 2: plateSize = R.drawable.plate_2_hdpi;
                        break;
                    case 3: plateSize = R.drawable.plate_3_hdpi;
                        break;
                    case 4: plateSize = R.drawable.plate_4_hdpi;
                        break;
                    case 5: plateSize = R.drawable.plate_5_hdpi;
                        break;
                    case 6: plateSize = R.drawable.plate_6_hdpi;
                        break;
                    case 7: plateSize = R.drawable.plate_7_hdpi;
                        break;
                    default: plateSize = R.drawable.plate_1_hdpi;
                        break;
                }

                switch (color) {
                    case "red" : plateColor = R.color.red;
                        break;
                    case "orange" : plateColor = R.color.orange;
                        break;
                    case "yellow" : plateColor = R.color.yellow;
                        break;
                    case "green" : plateColor = R.color.green;
                        break;
                    case "blue" : plateColor = R.color.blue;
                        break;
                    case "indigo" : plateColor = R.color.indigo;
                        break;
                    case "violet" : plateColor = R.color.violet;
                        break;
                    case "black" : plateColor = R.color.black;
                        break;
                    case "white" : plateColor = R.color.white;
                        break;
                    default: plateColor = R.color.red;
                        break;
                }

                for (int j = 0; j < numOfPlate; j++) {
                    ImageView pImg = new ImageView(getApplicationContext());
                    TextView pText = new TextView(getApplicationContext());

                    RelativeLayout.LayoutParams lp = new RelativeLayout.
                            LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);

                    RelativeLayout.LayoutParams textLp = new RelativeLayout.
                            LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);


                    pImg.setImageResource(plateSize);
                    pImg.setBackgroundColor(getResources().getColor(plateColor));

                    pText.setTextColor(getResources().getColor(R.color.white));
                    pText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                    DecimalFormat df = new DecimalFormat("0.#");
                    pText.setText(String.valueOf(df.format(plates.get(i).getWeight() / 2)));              // TODO: Remove divide by 2 after updating database AND remember to multiply weights * 2, additionally remove INT cast

                    // margins with left 1 dp, right 2 dp
                    lp.setMargins(marginLeft, 0, marginRight, 0);
                    // align child start edge with anothers end edge, END_OF
                    lp.addRule(17, imgStart);
                    // center in parent, with respects to its relative layout parent (vertical)
                    lp.addRule(15);
                    id = View.generateViewId();
                    pImg.setId(id);
                    pImg.setLayoutParams(lp);

                    this.plateID.add(id);

                    textLp.setMargins(0, 0, 0, marginBottom);
                    textLp.addRule(18, id);                         // align start
                    textLp.addRule(19, id);                         // align end
                    textLp.addRule(12);                             // align parent bottom
                    pText.setLayoutParams(textLp);

                    RelativeLayout frameT = (RelativeLayout)findViewById(R.id.frame);
                    frameT.addView(pImg);
                    imgStart = pImg.getId();
                    frameT.addView(pText);

                    // align barEnd to the last plate added
                    RelativeLayout.LayoutParams barEndLp = new RelativeLayout.
                            LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);

                    // set the parameters for the barEnd
                    barEndLp.setMargins(marginLeft, 0, marginRight, 0);
                    barEndLp.addRule(17, id);
                    barEndLp.addRule(15);

                    barEnd.setLayoutParams(barEndLp);
                }
            }
            i++;

            // runs algorithm twice, if the first solution is not possible,
            // drop down to next highest plate and restart calculations
            // all over again, if weights are re arranged and a solution
            // still can not be found, restart calculations and then
            // take the best solution it can
            if (i >= plates.size() && w > 0 && k <= plates.size()) {
                k++;
                i = k;
                w = weight - 45;
                imgStart = R.id.barMid;
                clearPlates();

                if (k == plates.size()) {
                    i = 0;
                    k++;
                }
            }

            if (i >= plates.size() && w > 0) {
                Context context = getApplicationContext();
                // TODO: Add this string to string.xml
                String text = getString(R.string.not_enough_weights);
                String remaining_weight = " " + Double.toString(weight - w) + " lbs";
                text += remaining_weight;

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
            }
        } while (w > 0);
    }
}

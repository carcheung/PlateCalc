package com.carolyncheung.calc.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.data.PlateData;
import com.carolyncheung.calc.helpers.ListViewAdapterAdd;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static com.carolyncheung.calc.data.Constant.AMOUNT_COLUMN;
import static com.carolyncheung.calc.data.Constant.ID_COLUMN;
import static com.carolyncheung.calc.data.Constant.MULTIPLY_COLUMN;
import static com.carolyncheung.calc.data.Constant.UNIT_COLUMN;
import static com.carolyncheung.calc.data.Constant.WEIGHT_COLUMN;

/**
 * Created by Carolyn Cheung on 6/13/2016.
 * Creates a list of all plates available to the user
 */
public class DisplayPlateSetAddFragment extends Fragment {
    Activity activity;
    private ListView elv;
    private ArrayList<HashMap<String, String>> list;
    public ArrayList<PlateData> plate_set;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_display_plate_set, container, false);
        Context c = getActivity().getApplicationContext();
        DBHandler db = new DBHandler(c);

        elv = (ListView) v.findViewById(R.id.listView_plates);
        list = new ArrayList<HashMap<String, String>>();

        plate_set = populateList(db);
        for (int i = 0; i < plate_set.size(); i++) {
            plate_set.get(i).setAmount(0);
        }

        ListViewAdapterAdd adapter = new ListViewAdapterAdd(this, list, plate_set);
        elv.setAdapter(adapter);

        return v;
    }

    public ArrayList<PlateData> populateList(DBHandler db) {
        ArrayList<PlateData> p = new ArrayList<PlateData>();
        double weight;
        // TODO: Change unit to whatever the user setting is
        String unit = "lbs";
        // TODO: Change units after having shared preferences setup
        p = db.getPlateSet(2);
        for (int i = 0; i < p.size(); i++) {
            HashMap<String, String> pMap = new HashMap<String, String>();
            pMap.put(ID_COLUMN, Integer.toString(p.get(i).getId()));

            pMap.put(AMOUNT_COLUMN, Integer.toString(0));
            pMap.put(MULTIPLY_COLUMN, "x");
            if (p.get(i).getUnit() == 1) {
                unit = "kgs";
            } else {
                unit = "lbs";
            }

            weight = p.get(i).getWeight() / 2;
            DecimalFormat df = new DecimalFormat("0.#");

            pMap.put(UNIT_COLUMN, unit);
            pMap.put(WEIGHT_COLUMN, df.format(weight));
            list.add(pMap);
        }
        return p;
    }

    public ArrayList<PlateData> sendPlateData(){
        for (int i = 0; i < plate_set.size(); i++) {

            Object o = elv.getAdapter().getItem(i);
            Log.d("elv", o.toString());
        }


        return plate_set;
    }
}

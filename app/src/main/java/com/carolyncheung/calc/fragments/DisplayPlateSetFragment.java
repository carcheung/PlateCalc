package com.carolyncheung.calc.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.data.PlateData;
import com.carolyncheung.calc.helpers.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static com.carolyncheung.calc.data.Constant.AMOUNT_COLUMN;
import static com.carolyncheung.calc.data.Constant.MULTIPLY_COLUMN;
import static com.carolyncheung.calc.data.Constant.UNIT_COLUMN;
import static com.carolyncheung.calc.data.Constant.WEIGHT_COLUMN;


/**
 * Created by Carolyn Cheung on 2/28/2016.
 */
public class DisplayPlateSetFragment extends Fragment{
    private ListView elv;
    private ArrayList<HashMap<String, String>> list;
    ArrayList<PlateData> plate_set;

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

        // TODO: Change units after having shared preferences setup
        plate_set = populateList(); //db.getPlateSet(1);

        ListViewAdapter adapter = new ListViewAdapter(this, list, plate_set);
        elv.setAdapter(adapter);

        return v;
    }

    public ArrayList<PlateData> populateList() {
        ArrayList<PlateData> p = new ArrayList<PlateData>();
        HashMap<String, String> pMap = new HashMap<String, String>();

            pMap.put(AMOUNT_COLUMN, "10");
            pMap.put(MULTIPLY_COLUMN, "x");
            pMap.put(UNIT_COLUMN, "lbs");
            pMap.put(WEIGHT_COLUMN, "45");
            list.add(pMap);

        HashMap<String, String> pMap1 = new HashMap<String, String>();
        pMap1.put(AMOUNT_COLUMN, "10");
        pMap1.put(MULTIPLY_COLUMN, "x");
        pMap1.put(UNIT_COLUMN, "lbs");
        pMap1.put(WEIGHT_COLUMN, "25");
        list.add(pMap1);

        HashMap<String, String> pMap2 = new HashMap<String, String>();
        pMap2.put(AMOUNT_COLUMN, "10");
        pMap2.put(MULTIPLY_COLUMN, "x");
        pMap2.put(UNIT_COLUMN, "lbs");
        pMap2.put(WEIGHT_COLUMN, "35");
        list.add(pMap2);

        HashMap<String, String> pMap3 = new HashMap<String, String>();
        pMap3.put(AMOUNT_COLUMN, "10");
        pMap3.put(MULTIPLY_COLUMN, "x");
        pMap3.put(UNIT_COLUMN, "lbs");
        pMap3.put(WEIGHT_COLUMN, "25");
        list.add(pMap3);

        HashMap<String, String> pMap4 = new HashMap<String, String>();
        pMap4.put(AMOUNT_COLUMN, "10");
        pMap4.put(MULTIPLY_COLUMN, "x");
        pMap4.put(UNIT_COLUMN, "lbs");
        pMap4.put(WEIGHT_COLUMN, "10");
        list.add(pMap4);

        HashMap<String, String> pMap5 = new HashMap<String, String>();
        pMap5.put(AMOUNT_COLUMN, "10");
        pMap5.put(MULTIPLY_COLUMN, "x");
        pMap5.put(UNIT_COLUMN, "lbs");
        pMap5.put(WEIGHT_COLUMN, "5");
        list.add(pMap5);
        HashMap<String, String> pMap6 = new HashMap<String, String>();
        pMap6.put(AMOUNT_COLUMN, "10");
        pMap6.put(MULTIPLY_COLUMN, "x");
        pMap6.put(UNIT_COLUMN, "lbs");
        pMap6.put(WEIGHT_COLUMN, "2.5");
        list.add(pMap6);

        return p;
    }

}

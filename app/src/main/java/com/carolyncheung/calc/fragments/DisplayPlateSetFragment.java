package com.carolyncheung.calc.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.Constant;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.data.PlateData;
import com.carolyncheung.calc.helpers.ListViewAdapterCalculate;
import com.carolyncheung.calc.helpers.ListViewAdapterModPlates;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static com.carolyncheung.calc.data.Constant.AMOUNT_COLUMN;
import static com.carolyncheung.calc.data.Constant.ID_COLUMN;
import static com.carolyncheung.calc.data.Constant.MULTIPLY_COLUMN;
import static com.carolyncheung.calc.data.Constant.UNIT_COLUMN;
import static com.carolyncheung.calc.data.Constant.WEIGHT_COLUMN;
import static com.carolyncheung.calc.data.Constant.UNIT_KG;
import static com.carolyncheung.calc.data.Constant.UNIT_LBS;
import static com.carolyncheung.calc.data.Constant.COLOR_COLUMN;
import static com.carolyncheung.calc.data.Constant.SIZE_COLUMN;

/**
 * Created by Carolyn Cheung on 2/28/2016.
 * Gets the list of plates from the database, then chooses a custom listview adapter to display
 * the plates, depending on which activity this is used for.
 */
public class DisplayPlateSetFragment extends Fragment{
    private ListView elv;
    private ArrayList<HashMap<String, String>> list;
    ArrayList<PlateData> plate_set;
    ImageView add;

    public interface OnItemClickedListener {
        public void OnItemClicked();
    }

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
        Bundle bundle = getArguments();

        elv = (ListView) v.findViewById(R.id.listView_plates);
        list = new ArrayList<HashMap<String, String>>();

        if (bundle != null) {
            if (bundle.getString(Constant.FROM_ACTIVITY, "default").equals(Constant.ACTIVITY_MODPLATES)){
                plate_set = populateListModPlates(db);
                ListViewAdapterModPlates adapter = new ListViewAdapterModPlates(this, list, plate_set);
                elv.setAdapter(adapter);

                return v;
            }
        } else {
            plate_set = populateList(db); //db.getPlateSet(1);
        }

        ListViewAdapterCalculate adapter = new ListViewAdapterCalculate(this, list, plate_set);
        elv.setAdapter(adapter);

        return v;
    }

    public ArrayList<PlateData> populateListModPlates (DBHandler db) {
        ArrayList<PlateData> p = new ArrayList<PlateData>();
        double weight;
        String unit = UNIT_LBS;

        p = db.getAllPlates();
        for (int i = 0; i < p.size(); i++) {
            HashMap<String, String> pMap = new HashMap<String, String>();
            pMap.put(ID_COLUMN, Integer.toString(p.get(i).getId()));

            if (p.get(i).getUnit() == 1) {
                unit = UNIT_KG;
            } else if (p.get(i).getUnit() == 2) {
                unit = UNIT_LBS;
            }
            weight = p.get(i).getWeight();
            DecimalFormat df = new DecimalFormat("0.#");

            pMap.put(UNIT_COLUMN, unit);
            pMap.put(WEIGHT_COLUMN, df.format(weight));
            pMap.put(COLOR_COLUMN, p.get(i).getColor());
            pMap.put(SIZE_COLUMN, Integer.toString(p.get(i).getSize()));
            list.add(pMap);
        }

        return p;
    }

    public ArrayList<PlateData> populateList(DBHandler db) {
        ArrayList<PlateData> p = new ArrayList<PlateData>();
        double weight;
        // TODO: Change unit to whatever the user setting is
        String unit = UNIT_LBS;

        // TODO: Change units after having shared preferences setup
        p = db.getPlateSet(2);
        for (int i = 0; i < p.size(); i++) {
            HashMap<String, String> pMap = new HashMap<String, String>();
            pMap.put(ID_COLUMN, Integer.toString(p.get(i).getId()));

            Log.d("ID: ", Integer.toString(p.get(i).getId()));

            pMap.put(AMOUNT_COLUMN, Integer.toString(p.get(i).getAmount()));
            pMap.put(MULTIPLY_COLUMN, "x");
            if (p.get(i).getUnit() == 1) {
                unit = UNIT_KG;
            } else if (p.get(i).getUnit() == 2) {
                unit = UNIT_LBS;
            }

            weight = p.get(i).getWeight() / 2;
            DecimalFormat df = new DecimalFormat("0.#");

            pMap.put(UNIT_COLUMN, unit);
            pMap.put(WEIGHT_COLUMN, df.format(weight));
            list.add(pMap);
        }

        return p;
    }

    ListView getListview() {
        return this.elv;
    }
}

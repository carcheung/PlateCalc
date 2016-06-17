package com.carolyncheung.calc.helpers;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.activities.WeightAddActivity;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.data.PlateData;
import com.carolyncheung.calc.fragments.DisplayPlateSetAddFragment;

import java.util.ArrayList;
import java.util.HashMap;

import static com.carolyncheung.calc.data.Constant.ID_COLUMN;
import static com.carolyncheung.calc.data.Constant.MULTIPLY_COLUMN;
import static com.carolyncheung.calc.data.Constant.UNIT_COLUMN;
import static com.carolyncheung.calc.data.Constant.WEIGHT_COLUMN;
import static com.carolyncheung.calc.data.Constant.AMOUNT_COLUMN;

/**
 * Created by Carolyn Cheung on 6/13/2016.
 * ListViewAdapter
 */
public class ListViewAdapterAdd extends BaseAdapter{
    public ArrayList<HashMap<String, String>> list;
    DisplayPlateSetAddFragment fragment;
    private ArrayList<PlateData> plate_set;
    String id = "999";

    private DataTransferInterface data;

    public ListViewAdapterAdd(DisplayPlateSetAddFragment fragment, ArrayList<HashMap<String, String>> list,
                              ArrayList<PlateData> plate_set) {
        super();
        this.fragment = fragment;
        this.list = list;
        this.plate_set = plate_set;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position).get(WEIGHT_COLUMN);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView plateSize;
        TextView multiply;
        TextView plateAmount;
        TextView plateUnit;
        ImageView plus;
        ImageView minus;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context c = parent.getContext();
        final DBHandler db = new DBHandler(c);
        final ViewHolder holder;
        final int plate_id = plate_set.get(position).getId();
        LayoutInflater inflater = fragment.getActivity().getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_plate_amount_modifier, null);
            holder = new ViewHolder();
            holder.plateSize = (TextView)convertView.findViewById(R.id.weight);
            holder.plateAmount = (TextView)convertView.findViewById(R.id.amt);
            holder.plateUnit = (TextView)convertView.findViewById(R.id.unit);
            holder.multiply = (TextView)convertView.findViewById(R.id.multiply);
            holder.plus = (ImageView)convertView.findViewById(R.id.add);
            holder.minus = (ImageView)convertView.findViewById(R.id.sub);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        // map info from fragment to the listview
        HashMap<String, String> map = list.get(position);
        holder.plateSize.setText(map.get(WEIGHT_COLUMN));
        holder.plateUnit.setText(map.get(UNIT_COLUMN));
        holder.multiply.setText(map.get(MULTIPLY_COLUMN));
        holder.plateAmount.setText(Integer.toString(plate_set.get(position).getAmount()));

        this.id = map.get(ID_COLUMN);

        final ImageView plus = holder.plus;
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amt = plate_set.get(position).getAmount() + 1;
                plate_set.get(position).setAmount(amt);
                list.get(position).put(AMOUNT_COLUMN, Integer.toString(amt));
                notifyDataSetChanged();

                WeightAddActivity w = (WeightAddActivity) fragment.getActivity();
                w.calculateAddedWeight(plate_set);
            }
        });

        final ImageView minus = holder.minus;
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int amt = plate_set.get(position).getAmount() - 1;
                plate_set.get(position).setAmount(amt);
                //holder.plateAmount.setText(Integer.toString(plate_set.get(position).getAmount()));
                list.get(position).put(AMOUNT_COLUMN, Integer.toString(amt));
                notifyDataSetChanged();

                WeightAddActivity w = (WeightAddActivity) fragment.getActivity();
                w.calculateAddedWeight(plate_set);
            }
        });

        return convertView;
    }

    public interface DataTransferInterface {
        public void setValues(int position);
    }

    public ArrayList<PlateData> sendPlateData(){
        Log.d("ADAPTER: ", "Sending plate_set");
        for (int i = 0; i < plate_set.size(); i++) {
            Log.d("Plate: ", Integer.toString(plate_set.get(i).getAmount()));
        }
        return plate_set;
    }
}

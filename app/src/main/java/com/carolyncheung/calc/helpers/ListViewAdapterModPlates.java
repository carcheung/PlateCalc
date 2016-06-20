package com.carolyncheung.calc.helpers;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.Constant;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.data.PlateData;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import static com.carolyncheung.calc.data.Constant.UNIT_COLUMN;
import static com.carolyncheung.calc.data.Constant.WEIGHT_COLUMN;

/**
 * Created by Carolyn Cheung on 6/19/2016.
 */
public class ListViewAdapterModPlates extends BaseAdapter {
    public ArrayList<HashMap<String, String>> list;
    Fragment fragment;
    ArrayList<PlateData> plate_set;

    public ListViewAdapterModPlates(Fragment fragment, ArrayList<HashMap<String, String>> list,
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
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView plateWeight;
        TextView plateSize;
        TextView plateUnit;
        TextView plateColor;
        CheckBox checkBox;
        ImageView arrow;
        LinearLayout button;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context c = parent.getContext();
        final DBHandler db = new DBHandler(c);
        final ViewHolder holder;
        final int plate_id = plate_set.get(position).getId();
        LayoutInflater inflater = fragment.getActivity().getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_mod_plates_modifier, null);
            holder = new ViewHolder();
            holder.plateSize = (TextView)convertView.findViewById(R.id.size);
            holder.plateUnit = (TextView)convertView.findViewById(R.id.unit);
            holder.plateWeight = (TextView)convertView.findViewById(R.id.weight);
            holder.plateColor = (TextView)convertView.findViewById(R.id.color);
            holder.checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);
            holder.arrow = (ImageView)convertView.findViewById(R.id.arrow_left);
            holder.button = (LinearLayout)convertView.findViewById(R.id.mod_plate_button);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map = list.get(position);
        holder.plateSize.setText(map.get(Constant.SIZE_COLUMN));
        holder.plateUnit.setText(map.get(UNIT_COLUMN));
        holder.plateWeight.setText(map.get(Constant.WEIGHT_COLUMN));
        holder.plateColor.setText(map.get(Constant.COLOR_COLUMN));

        LinearLayout mod_button = holder.button;
        mod_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("SUCCESS","YAY");

                new AlertDialog.Builder(c)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        return convertView;
    }
}
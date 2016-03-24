package com.carolyncheung.calc.helpers;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.PlateData;
import static com.carolyncheung.calc.data.Constant.AMOUNT_COLUMN;
import static com.carolyncheung.calc.data.Constant.MULTIPLY_COLUMN;
import static com.carolyncheung.calc.data.Constant.UNIT_COLUMN;
import static com.carolyncheung.calc.data.Constant.WEIGHT_COLUMN;
import static com.carolyncheung.calc.data.Constant.ADD_PLATES_BUTTON;
import static com.carolyncheung.calc.data.Constant.MINUS_PLATES_BUTTON;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Carolyn Cheung on 2/28/2016.
 */
public class ListViewAdapter extends BaseAdapter{
    public ArrayList<HashMap<String, String>> list;
    Fragment fragment;
    ArrayList<PlateData> plate_set;

    public ListViewAdapter(Fragment fragment, ArrayList<HashMap<String, String>> list,
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
        TextView plateSize;
        TextView multiply;
        TextView plateAmount;
        TextView plateUnit;
        ImageView plus;
        ImageView minus;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = fragment.getActivity().getLayoutInflater();
        int index = position;

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

        HashMap<String, String> map = list.get(position);
        holder.plateSize.setText(map.get(WEIGHT_COLUMN));
        holder.plateUnit.setText(map.get(UNIT_COLUMN));
        holder.plateAmount.setText(map.get(AMOUNT_COLUMN));
        holder.multiply.setText(map.get(MULTIPLY_COLUMN));

        return convertView;
    }

}
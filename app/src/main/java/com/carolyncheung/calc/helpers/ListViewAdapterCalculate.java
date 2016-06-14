package com.carolyncheung.calc.helpers;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.DBHandler;
import com.carolyncheung.calc.data.PlateData;
import static com.carolyncheung.calc.data.Constant.AMOUNT_COLUMN;
import static com.carolyncheung.calc.data.Constant.ID_COLUMN;
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
 * ListViewAdapterCalculate
 * Displays platesize, amt, and how many to multiply
 *
 */
public class ListViewAdapterCalculate extends BaseAdapter{
    public ArrayList<HashMap<String, String>> list;
    Fragment fragment;
    ArrayList<PlateData> plate_set;
    String id = "999";

    public ListViewAdapterCalculate(Fragment fragment, ArrayList<HashMap<String, String>> list,
                                    ArrayList<PlateData> plate_set) {
        super();
        this.fragment = fragment;
        this.list = list;
        this.plate_set = plate_set;
        this.id = id;
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
        // info taken from database
        holder.plateAmount.setText(Integer.toString(db.getAmount(plate_id)));

        this.id = map.get(ID_COLUMN);

        final ImageView plus = holder.plus;
        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db.addAmount(plate_id);
                plate_set.get(position).setAmount(db.getAmount(plate_id));
                holder.plateAmount.setText(Integer.toString(db.getAmount(plate_id)));
            }
        });

        final ImageView minus = holder.minus;
        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db.removeAmount(plate_id);
                plate_set.get(position).setAmount(db.getAmount(plate_id));
                holder.plateAmount.setText(Integer.toString(db.getAmount(plate_id)));
            }
        });

        return convertView;
    }
}

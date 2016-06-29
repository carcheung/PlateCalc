package com.carolyncheung.calc.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.activities.AddPlateActivity;
import com.carolyncheung.calc.activities.ModPlatesActivity;
import com.carolyncheung.calc.activities.SettingsActivity;
import com.carolyncheung.calc.activities.WeightAddActivity;
import com.carolyncheung.calc.activities.WeightCalculateActivity;
import com.carolyncheung.calc.data.Constant;

/**
 * Created by Carolyn Cheung on 2/26/2016.
 * Custom action bar, contains icon for swapping between calculation modes
 * for adding plates and calculating plate amounts. Additionally has
 * an icon for settings.
 */
public class ActionBarFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        // this fragment will participate in populationg the options menu
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        // get bundle arguments
        Bundle bundle = getArguments();

        // inflate a new view hierarchy
        // int resource, ViewGroup, the parent of the generated hierarchy
        // false means root is only used to create correct subclass of layout params
        View view = inflater.inflate(R.layout.fragment_action_bar, container, false);
        Toolbar actionBar = (Toolbar) view.findViewById(R.id.action_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(actionBar);
        actionBar.setTitleTextColor(getResources().getColor(R.color.white));

        // if the bundle is not null, we want to modify the toolbar depending on
        // what activity has called it
        // TODO: REMOVE THIS MAYBE ?
        if (bundle != null) {
            if (bundle.getString(Constant.FROM_ACTIVITY, "default").equals(Constant.ACTIVITY_ADDPLATE)) {
                // remove title
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_tool_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // TODO: FIX LATER SO ITS JUST THE SAVE OR BACK OR W/e BUTTON BUT YOU KNOW LET IT LIKE. POPULATE BASED ON BUNDLE ARGS OR SOME SHIT IDK
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        //menu.clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.barAdd:
                Log.v("ActionBarFragment", "barAdd");
                Intent intent = new Intent(getActivity(), WeightAddActivity.class);
                startActivity(intent);
                return true;
            case R.id.barCalc:
                Log.v("ActionBarFragment", "barCalc");
                intent = new Intent(getActivity(), WeightCalculateActivity.class);
                startActivity(intent);
                return true;
            case R.id.settings:
                Log.v("ActionBarFragment", "settings");
                intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.modPlates:
                Log.v("ActionBarFragment", "settings");
                intent = new Intent(getActivity(), ModPlatesActivity.class);
                startActivity(intent);
                return true;
            case R.id.createPlate:
                Log.v("ActionBarFragment", "settings");
                intent = new Intent(getActivity(), AddPlateActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

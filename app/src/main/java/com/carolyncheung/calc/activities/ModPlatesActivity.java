package com.carolyncheung.calc.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.data.Constant;
import com.carolyncheung.calc.fragments.DisplayPlateSetFragment;

/**
 * Created by Carolyn Cheung on 6/19/2016.
 * Activity that contains the features to modify database of plates
 */
public class ModPlatesActivity extends AppCompatActivity implements DisplayPlateSetFragment.OnItemClickedListener{

    @Override
    public void OnItemClicked() {
        // do whatever
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modplates);

        // put args in fragment
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FROM_ACTIVITY, Constant.ACTIVITY_MODPLATES);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DisplayPlateSetFragment displayPlateSetFragment = new DisplayPlateSetFragment();
        displayPlateSetFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.show_plate_set, displayPlateSetFragment,
                "displayPlateSetFragment");
        fragmentTransaction.commit();

/* HOW TO USE FLAOTINGACTIONBUTTON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        }); */
    }


}

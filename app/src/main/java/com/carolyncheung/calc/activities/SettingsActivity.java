package com.carolyncheung.calc.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.carolyncheung.calc.R;
import com.carolyncheung.calc.fragments.PopupAboutDialogFragment;

/**
 * Created by Carolyn Cheung on 6/18/2016.
 */
public class SettingsActivity extends PreferenceActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Preference mod_plate = (Preference) findPreference("pref_key_weight_mod_plate_inventory");
        mod_plate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Log.d("HELLO", "DO A THING PLS");
                return false;
            }
        });

        Preference about = (Preference) findPreference("pref_key_other_about");
        about.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference preference) {

                // dynamically create toolbar
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // add arguments and tag/commit
                PopupAboutDialogFragment popupAbout = new PopupAboutDialogFragment();
                popupAbout.show(fragmentManager, "popupAbout");

                return false;
            }
        });
    }
}

package com.carolyncheung.calc.activities;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.carolyncheung.calc.R;

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
                LayoutInflater layoutInflater =
                        (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View aboutPopUp = layoutInflater.inflate(R.layout.popup_about, null);
                final PopupWindow popupWindow = new PopupWindow(aboutPopUp,
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);

                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                getListView().setAlpha(0.25f);
                popupWindow.showAtLocation(aboutPopUp, Gravity.CENTER, 0, 0);

                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        getListView().setAlpha(1.0f);
                    }
                });
                return false;
            }
        });
    }
}

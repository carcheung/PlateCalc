package com.carolyncheung.calc.helpers;

import android.util.Log;

import com.carolyncheung.calc.data.PlateData;

import java.util.ArrayList;

/**
 * Created by Carolyn Cheung on 2/27/2016.
 * Helper class to handle various calculations
 */
public class CalculationsHelper {

// Rounds weights up or down to the nearest multiple of 5, retains the decimals
    static public double roundWeights(double weight) {
        double d_weight = weight;
        double total = 0;
        int i_weight = (int) weight;
        int remainder;

        if ((i_weight % 5) != 0) {
            remainder = i_weight % 5;
            if (remainder >= 3) {
                total = i_weight / 5;
                total *= 5;
                total += 5;
            } else {
                total = i_weight / 5;
                total *= 5;
            }
            d_weight -= i_weight;
            d_weight += total;
        }
        return d_weight;
    }


}

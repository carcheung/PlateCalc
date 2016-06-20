package com.carolyncheung.calc.data;

import com.carolyncheung.calc.R;

/**
 * Created by Carolyn Cheung on 2/28/2016.
 */
public class Constant {
    // static key strings for plates
    public static final String MINUS_PLATES_BUTTON = "minus_plate";
    public static final String ADD_PLATES_BUTTON = "add_plate";
    public static final String WEIGHT_COLUMN = "Weight";
    public static final String UNIT_COLUMN = "Unit";
    public static final String MULTIPLY_COLUMN = "Times";
    public static final String AMOUNT_COLUMN = "Amount";
    public static final String ID_COLUMN = "id";
    public static final String UNIT_KG = "kg";
    public static final String UNIT_LBS = "lbs";
    public static final String COLOR_COLUMN = "color";
    public static final String SIZE_COLUMN = "size";

    // static key strings for plate colors
    public static final String RED = "red";
    public static final String ORANGE = "orange";
    public static final String YELLOW = "yellow";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";
    public static final String INDIGO = "indigo";
    public static final String VIOLET = "violet";
    public static final String BLACK = "black";
    public static final String WHITE = "white";

    // static key strings for bundles
    public static final String FROM_ACTIVITY = "from_activity";
    public static final String ACTIVITY_ADDPLATE = "activity_addplate";
    public static final String ACTIVITY_MODPLATES = "activity_modplates";
    public static final String ACTIVITY_CALCULATE = "activity_calculate";

    // static values for res files
    public static int colors[] = {R.color.red, R.color.orange, R.color.yellow, R.color.green,
            R.color.blue, R.color.indigo, R.color.violet, R.color.black, R.color.white};
    public static int plate_sizes[] = {R.drawable.plate_1_hdpi, R.drawable.plate_2_hdpi,
            R.drawable.plate_3_hdpi, R.drawable.plate_4_hdpi, R.drawable.plate_5_hdpi,
            R.drawable.plate_6_hdpi, R.drawable.plate_7_hdpi};

    // static values for layout ID
    public static int color_choice_rID[] = {R.id.color_1_1, R.id.color_2_1, R.id.color_3_1,
            R.id.color_1_2, R.id.color_2_2, R.id.color_3_2, R.id.color_1_3, R.id.color_2_3,
            R.id.color_3_3};
    public static int plate_rID[] = {R.id.plate1, R.id.plate2, R.id.plate3,R.id.plate4, R.id.plate5,
            R.id.plate6, R.id.plate7, R.id.plate8, R.id.plate9, R.id.plate10};

}

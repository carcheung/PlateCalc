package com.carolyncheung.calc.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Carolyn Cheung on 2/27/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    /* Database Version */
    private static final int DATABASE_VERSION = 1;
    private static final String DB_PATH = "/data/data/com.carolyncheung.calc/databases/";

    /* Database Name */ // TODO: Finalize database name/app name
    private static final String DATABASE_NAME = "calc.db";

    /* Table names */
    private static final String TABLE_COLOR = "color";
    private static final String TABLE_PLATE = "plate";
    private static final String TABLE_SIZE = "size";
    private static final String TABLE_UNIT = "unit";

    /* Common column names */
    public static final String KEY_ID = "_id";
    public static final String KEY_SIZE = "size";

    /* Color table */
    public static final String KEY_COLOR_NAME = "c_name";

    /* Plate table */
    public static final String KEY_PLATE_AMOUNT = "amount";
    public static final String KEY_PLATE_WEIGHT = "weight";
    public static final String KEY_PLATE_UNIT = "unit";
    public static final String KEY_PLATE_COLOR = "color";

    /* Size table */
    // none

    /* Unit table */
    public static final String KEY_UNIT_NAME = "u_name";

    private SQLiteDatabase myDatabase;

    private final Context myContext;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDatabase();

        if (dbExist) {
            // do nothing
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            }
            catch (IOException e) {
                throw new Error("Error copying database!");
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e) {
            // database doesn't exist yet
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true: false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        String outFileName = DB_PATH + DATABASE_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLException {
        String myPath = DB_PATH + DATABASE_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (myDatabase != null) {
            myDatabase.close();
        }
        super.close();
    }

    /***********************************************************************************************
    **                                     Plate table methods                                    **
    ***********************************************************************************************/

    // add a plate to the database given a plate
    public void addPlate(PlateData plate) {
        // TODO: Add a function to add plates
    }

    // get a set of plates from the database based on the units user is using
    public ArrayList<PlateData> getPlateSet(int unit_ID) {
        ArrayList<PlateData> plate_set = new ArrayList<PlateData>();
        SQLiteDatabase db = this.getReadableDatabase();
/*        String select_query = "SELECT " + TABLE_PLATE + "." + KEY_ID + ", " + KEY_SIZE + ", "
            + KEY_PLATE_AMOUNT + ", " + KEY_PLATE_WEIGHT + ", " + KEY_PLATE_UNIT + ", "
            + TABLE_COLOR + "." + KEY_COLOR_NAME + " FROM " + TABLE_PLATE + " INNER JOIN "
            + TABLE_COLOR + " ON " + TABLE_PLATE + "." + KEY_PLATE_COLOR + "=" + TABLE_COLOR
            + "." + KEY_ID + " WHERE " + KEY_PLATE_UNIT + " = " + unit_ID + " ORDER BY "
            + KEY_PLATE_WEIGHT + " DESC"; */

        String select_query = "SELECT * FROM color";

/*        Cursor curosr = db.rawQuery(select_query, null);
        if (curosr.moveToFirst()) {
            do {
                int id = curosr.getInt(0);
                int size = curosr.getInt(1);
                int amount = curosr.getInt(2);
                double weight = curosr.getDouble(3);
                int unit = curosr.getInt(4);
                String color = curosr.getString(5);
                PlateData plate = new PlateData(id, color, size, amount, weight, unit);
                plate_set.add(plate);
            } while (curosr.moveToNext());
        } */

        int id = 1;
        String color = "red";
        int size = 1;
        int amount = 2;
        double weight = 45;
        int unit = 1;
        PlateData plate = new PlateData(id, color, size, amount, weight, unit);
        plate_set.add(plate);

        return plate_set;
    }
}

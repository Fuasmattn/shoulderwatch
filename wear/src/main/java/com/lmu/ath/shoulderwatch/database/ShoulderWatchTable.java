package com.lmu.ath.shoulderwatch.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by daniela on 11.07.16.
 */
public class ShoulderWatchTable {

    // Database table
    public static final String TABLE_SHOULDERWATCH = "shoulderwatch";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_LOCATION_X = "location_x";
    public static final String COLUMN_LOCATION_Y = "location_y";
    public static final String COLUMN_ENVIRONMENT = "environment";
    public static final String COLUMN_DEVICE_ANALOG = "device_analog";
    public static final String COLUMN_DEVICE_DIGITAL = "device_digital";
    public static final String COLUMN_DEVICETYPE = "devicetype";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_SURF_RATING = "surf_rating";
    public static final String COLUMN_RELATIVE_POSITION = "relative_position";
    public static final String COLUMN_CROWD_DENSITY = "crowd_density";
    public static final String COLUMN_DEFENCE_LEVEL = "defence_level";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_SHOULDERWATCH
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TIME + " text not null"
            + COLUMN_LOCATION_X + " real not null, "
            + COLUMN_LOCATION_Y + " real not null, "
            + COLUMN_ENVIRONMENT + " text not null"
            + COLUMN_DEVICE_ANALOG + " text not null"
            + COLUMN_DEVICE_DIGITAL + " text not null"
            + COLUMN_DEVICETYPE + " text not null"
            + COLUMN_CONTENT + " text not null"
            + COLUMN_SURF_RATING + " integer not null, "
            + COLUMN_RELATIVE_POSITION + " integer not null, "
            + COLUMN_CROWD_DENSITY + " text not null"
            + COLUMN_DEFENCE_LEVEL + " text not null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(ShoulderWatchTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOULDERWATCH);
        onCreate(database);
    }
}

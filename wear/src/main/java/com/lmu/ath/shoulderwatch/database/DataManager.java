package com.lmu.ath.shoulderwatch.database;

import android.content.ContentValues;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by daniela on 12.07.16.
 */
public class DataManager {

    public static DataManager instance;

    public DataManager() {
    }

    private ContentValues currentContentvalue;
    private Uri shoulderwatchUri;
    private Context context;

    public static DataManager getInstance(){
        if (instance == null){
            instance = new DataManager();
        }
        return instance;
    }

    public void createInitalDatabaseRecord(Location location){
        if (currentContentvalue == null) {
            currentContentvalue = new ContentValues();
        }
        if (location != null){
            addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_LOCATION_X, String.valueOf(location.getLatitude()));
            addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_LOCATION_Y, String.valueOf(location.getLongitude()));
        }

        String currentDateTimeString = new SimpleDateFormat("dd.MM.yyyy , HH:mm:ss").format(new Date());
        if (currentDateTimeString != null){
            addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_TIME, currentDateTimeString);
        }

    }

    public void addStringValueToDatabaseRecord(String key, String value){
        currentContentvalue.put(key, value);
    }

    public void addIntValueToDatabaseRecord(String key, Integer value){
        currentContentvalue.put(key, value);
    }

    public void addFloatValueToDatabaseRecord(String key, Double value){
        currentContentvalue.put(key, value);
    }

    public void saveDataBaseRecord(Context context){
        if (currentContentvalue != null) {
            shoulderwatchUri = context.getContentResolver().insert(ShoulderWatchContentProvider.CONTENT_URI, currentContentvalue);
            Toast.makeText(context,"Entry saved to history", Toast.LENGTH_SHORT).show();
            currentContentvalue = null;
        }

    }
}

package com.lmu.ath.shoulderwatch.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lmu.ath.shoulderwatch.model.ShoulderWatch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public String getAllDatabaseEntries(Context context){


        List<ShoulderWatch> shoulderWatches = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(ShoulderWatchContentProvider.CONTENT_URI,null,null,null,null);
        if(cursor!=null && cursor.getCount() != 0){
            cursor.moveToFirst();

            do {
                ShoulderWatch shoulderWatch = new ShoulderWatch();
                shoulderWatch.setTime(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_TIME)));
                shoulderWatch.setLocationX(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_LOCATION_X)));
                shoulderWatch.setLocationY(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_LOCATION_Y)));
                shoulderWatch.setEnvironment(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_ENVIRONMENT)));
                shoulderWatch.setDevicetype(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_DEVICETYPE)));
                shoulderWatch.setDeviceAnalog(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_DEVICE_ANALOG)));
                shoulderWatch.setDeviceDigital(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_DEVICE_DIGITAL)));
                shoulderWatch.setContent(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_CONTENT)));
                shoulderWatch.setSurfRating(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_SURF_RATING)));
                shoulderWatch.setRelativePosition(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_RELATIVE_POSITION)));
                shoulderWatch.setCrowdDensity(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_CROWD_DENSITY)));
                shoulderWatch.setDefenceLevel(cursor.getString(cursor.getColumnIndex(ShoulderWatchTable.COLUMN_DEFENCE_LEVEL)));
                shoulderWatches.add(shoulderWatch);
            } while (cursor.moveToNext());
        }

        String shoulderWatchJSON = null;
        if (shoulderWatches!= null || shoulderWatches.size() != 0) {
            shoulderWatchJSON = new Gson().toJson(shoulderWatches);
        }
        return shoulderWatchJSON;
    }
}

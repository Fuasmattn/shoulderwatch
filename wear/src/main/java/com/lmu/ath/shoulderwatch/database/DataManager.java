package com.lmu.ath.shoulderwatch.database;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

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

    public void createInitalDatabaseRecord(){
        if (currentContentvalue == null) {
            currentContentvalue = new ContentValues();
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

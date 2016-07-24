package com.lmu.ath.shoulderwatch.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.lmu.ath.shoulderwatch.R;
import com.lmu.ath.shoulderwatch.database.DataManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends WearableActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private TextView mClockView;
    private ImageButton mStartButton;
    private DataManager dataManager;
    private GoogleApiClient googleApiClient;
    private Location lastLocation = null;

    private Button intentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();
        initiateUI();
        dataManager = DataManager.getInstance();
        intentBtn = (Button)findViewById(R.id.sendDataBtn);

        intentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.getAllDatabaseEntries(getApplicationContext());
            }
        });



        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        mStartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dataManager.createInitalDatabaseRecord(lastLocation);
                Intent intent = new Intent(view.getContext(), SelectionsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initiateUI() {
        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        mClockView = (TextView) findViewById(R.id.clock);
        mStartButton = (ImageButton) findViewById(R.id.startBtn);
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }


    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

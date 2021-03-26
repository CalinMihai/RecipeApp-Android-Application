package com.example.recipeappstrike3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

public class NetworkSettingsActivity extends Activity {
    NetworkStateReceiver receiver;
    private TextView networkConnectivityView;

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkConnectivityView = (TextView) findViewById(R.id.networkConnectivityView);

        if (getIntent().getBooleanExtra("ACTIVATE_RECEIVER", false)) {
            receiver = new NetworkStateReceiver();
            receiver.addListener(new NetworkStateReceiver.NetworkStateReceiverListener() {
                @Override
                public void networkAvailable() {
                    networkConnectivityView.setText("You are connected to the internet");
                }

                @Override
                public void networkUnavailable() {
                    networkConnectivityView.setText("You are not connected to the internet");
                }
            });
            registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
}
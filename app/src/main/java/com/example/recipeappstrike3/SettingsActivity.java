package com.example.recipeappstrike3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    NetworkStateReceiver receiver;
    private TextView connectionStatus;

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        connectionStatus = (TextView) findViewById(R.id.connectionStatus);

        if (getIntent().getBooleanExtra("ACTIVATE_RECEIVER", false)){
            receiver = new NetworkStateReceiver();
            receiver.addListener(new NetworkStateReceiver.NetworkStateReceiverListener() {
                @Override
                public void networkAvailable() {
                    //do something
                    connectionStatus.setText("Connected");
                }

                @Override
                public void networkUnavailable() {
                    //do something else
                    connectionStatus.setText("Not connected");
                }
            });
            registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
}

package com.example.recipeappstrike3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        button = (Button) findViewById(R.id.networkSettingsBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNetworkSettingsActivity();
            }
        });
    }

    public void openNetworkSettingsActivity() {
        Intent intent = new Intent(this,NetworkSettingsActivity.class);
        intent.putExtra("ACTIVATE_RECEIVER", true);
        startActivity(intent);
    }
}
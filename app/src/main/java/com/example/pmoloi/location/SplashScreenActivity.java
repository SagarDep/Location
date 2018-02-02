package com.example.pmoloi.location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private TextView appVersionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        appVersionCode = findViewById(R.id.textViewSplashScreenVersionCodeLabel);
        appVersionCode.setText(BuildConfig.VERSION_NAME);
    }
}

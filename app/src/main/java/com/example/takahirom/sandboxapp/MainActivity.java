package com.example.takahirom.sandboxapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goLeakActivity(View view) {
        startActivity(new Intent(this, LeakActivity.class));
    }

    public void goAllocationActivity(View view) {
        startActivity(new Intent(this, AllocationActivity.class));
    }

    public void goAnrActivity(View view) {
        startActivity(new Intent(this, AnrActivity.class));
    }
}

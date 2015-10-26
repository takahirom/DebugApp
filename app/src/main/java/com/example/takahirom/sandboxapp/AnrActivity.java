package com.example.takahirom.sandboxapp;

import android.os.Bundle;
import android.support.v4.os.TraceCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_anr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void anr(View view) throws IOException {
        TraceCompat.beginSection("anr");
        for (int i = 0; i < 10; i++) {
            Log.d("AnrActivity", "write");
            String path = getCacheDir().getPath() + "test.txt";
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            for (int j = 0; j < 100000; j++) {
                fileOutputStream.write(100);
            }
            fileOutputStream.close();

            Log.d("AnrActivity", "read");
            StringBuffer stringBuffer = new StringBuffer();
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            int read;
            if ((read = inputStreamReader.read()) != -1) {
                stringBuffer.append(read);
            }
            inputStreamReader.close();
        }
        TraceCompat.endSection();

    }
}

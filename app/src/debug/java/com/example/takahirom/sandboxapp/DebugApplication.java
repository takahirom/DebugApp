package com.example.takahirom.sandboxapp;

import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by takahirom on 15/08/15.
 */
public class DebugApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "this is debug", Toast.LENGTH_SHORT).show();
        Log.d("DebugApplication", "onCreate");
        new Handler().postAtFrontOfQueue(
                new Runnable() {
                    @Override
                    public void run() {
                        // workaround https://code.google.com/p/android/issues/detail?id=35298
                        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                                        .detectDiskReads()
                                        .detectDiskWrites()
                                        .detectAll()   // or .detectAll() for all detectable problems
                                        .penaltyLog()
                                        .build());
                        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                                . detectLeakedSqlLiteObjects()
                                . detectLeakedClosableObjects()
                                . penaltyLog()
                                .penaltyDeath()
                                .build());
                    }
                }
        );


        LeakCanary.install(this);
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                        .enableDumpapp( Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}

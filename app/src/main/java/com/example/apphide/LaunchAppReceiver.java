package com.example.apphide;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;

public class LaunchAppReceiver extends BroadcastReceiver {

    String LAUNCHER_NUMBER = "10000001";
    private static final ComponentName LAUNCHER_COMPONENT_NAME = new ComponentName("com.example.apphide", "com.example.apphide.Launcher");

    @Override
    public void onReceive(Context context, Intent intent) {
        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        if (LAUNCHER_NUMBER.equals(phoneNumber)) {
            setResultData(null);

            if (isLauncherIconVisible(context)) {

            } else {

                final int millisUntilLaunch = 5000;
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("Receiver", "Try to launch activity");
                        Intent appIntent = new Intent(context, com.example.apphide.MainActivity.class);
                      //  appIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        appIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(appIntent);
                    }
                }, millisUntilLaunch);

            }
        }
    }

    private boolean isLauncherIconVisible(Context context) {
        int enabledSetting = context.getPackageManager().getComponentEnabledSetting(LAUNCHER_COMPONENT_NAME);
        return enabledSetting != PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    }

}

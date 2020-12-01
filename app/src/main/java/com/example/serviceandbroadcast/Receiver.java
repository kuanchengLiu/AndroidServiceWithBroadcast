package com.example.serviceandbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        String bundleData= Objects.requireNonNull(bundle).getString("Swe_sum");
        Log.d("onReceive","MainActivity Receiver : "+ bundleData);
    }
}

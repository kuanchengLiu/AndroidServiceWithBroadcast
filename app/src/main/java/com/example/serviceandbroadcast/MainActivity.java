package com.example.serviceandbroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startButton, finishButton;
    Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);
        finishButton = findViewById(R.id.finish_button);

//        receiver = new Receiver();
//        IntentFilter filter=new IntentFilter();
//        filter.addAction("android.intent.action.test");
//        this.registerReceiver(receiver,filter);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BroadCastService.class);
                startService(intent);

                finish();


            }


        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BroadCastService.class);
                stopService(intent);
            }
        });

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Intent intent = new Intent(MainActivity.this, BroadCastService.class);
//        stopService(intent);
//        this.unregisterReceiver(receiver);
//
//    }
}
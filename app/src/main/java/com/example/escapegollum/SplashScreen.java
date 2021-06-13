package com.example.escapegollum;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Activity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);




    }
}
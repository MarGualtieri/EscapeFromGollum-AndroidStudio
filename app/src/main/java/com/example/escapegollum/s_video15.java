package com.example.escapegollum;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.VideoView;


import java.util.Timer;
import java.util.TimerTask;

public class s_video15 extends Activity {
    private static MediaPlayer mp;
    Timer timer;
    private VideoView video;
    TextView nombre_texto;
    private static final String TAG = "";
    static int stopPosition=0;
    static int stopPosition2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_video15);
        mp = MediaPlayer.create(this, R.raw.audiofinal);
        mp.start();

        nombre_texto=(TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        video=(VideoView) findViewById(R.id.fin);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.fin;
        video.setVideoURI(Uri.parse(path));

        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                video.start();
                mp.setVolume(0,0);
            }
        });


        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(s_video15.this, MainActivity.class);

                mp.pause();
                startActivity(intent);
                video.stopPlayback();

                finish();

            }
        },43500);




    }

    @Override
    public void onBackPressed() {  // DESHABILITA LA FELCHA DE SALIDA DEL CELULAR

    }
    @Override
    public void onPause() {


        Log.d(TAG, "onPause called");
        super.onPause();
        stopPosition2 = mp.getCurrentPosition(); //stopPosition is an int
        mp.pause();
        stopPosition = video.getCurrentPosition(); //stopPosition is an int
        video.pause();

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        video.seekTo(stopPosition);
        video.start(); //Or use resume() if it doesn't work. I'm not sure
        mp.seekTo(stopPosition2);
        mp.start(); //Or use resume() if it doesn't work. I'm not sure
    }
}
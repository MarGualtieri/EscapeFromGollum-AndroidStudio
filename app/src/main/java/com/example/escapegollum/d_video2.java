package com.example.escapegollum;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

public class d_video2 extends AppCompatActivity {
    private static final String TAG = "";
    static int stopPosition=0;
    TextView nombre_texto;
    private VideoView video;

    private static MediaPlayer mp_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_video2);

        nombre_texto=(TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        mp_music = MediaPlayer.create(this, R.raw.menusonido);


        video=(VideoView) findViewById(R.id.e_dialogo2);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.d_video2;
        video.setVideoURI(Uri.parse(path));

        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                video.start();
                mp.setVolume(0,0);
                mp_music.start();
                mp_music.setLooping(true);
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
    public void continuar(View view) {

        Intent intent = new Intent(this, d_video3.class);
        intent.putExtra("jugador", nombre_texto.getText().toString());
        mp_music.stop();
        mp_music.release();
        startActivity(intent);
        finish();

    }
    public void salir(View view) {  // BOTON PARA SALIR
        mp_music.seekTo(0);
        video.seekTo(0);

        moveTaskToBack(true);
        finish();
    }


    @Override
    public void onPause() {
        Log.d(TAG, "onPause called");
        super.onPause();
        stopPosition = video.getCurrentPosition(); //stopPosition is an int
        video.pause();
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        video.seekTo(stopPosition);
        video.start(); //Or use resume() if it doesn't work. I'm not sure
    }
}





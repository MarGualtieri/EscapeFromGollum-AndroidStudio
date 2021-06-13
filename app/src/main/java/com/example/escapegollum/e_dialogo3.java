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

public class e_dialogo3 extends AppCompatActivity {
    private static final String TAG = "";
    static int stopPosition=0;
    static int stopPosition2=0;
    TextView nombre_texto;
    private VideoView video;

    private static MediaPlayer mp_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_dialogo3);

        nombre_texto=(TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        mp_music = MediaPlayer.create(this, R.raw.menusonido);
        mp_music.start();
        mp_music.setLooping(true);

        video=(VideoView) findViewById(R.id.e_dialogo2);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.e_dialogo3;
        video.setVideoURI(Uri.parse(path));

        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {


            }
        });

    }

    @Override
    public void onBackPressed() {

    }
    public void continuar(View view) {

        Intent intent = new Intent(this, f_video3.class);
        intent.putExtra("jugador", nombre_texto.getText().toString());

        startActivity(intent);
        video.stopPlayback();
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
        stopPosition2 = mp_music.getCurrentPosition(); //stopPosition is an int
        mp_music.pause();
        stopPosition = video.getCurrentPosition(); //stopPosition is an int
        video.pause();

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        video.seekTo(stopPosition);
        video.start(); //Or use resume() if it doesn't work. I'm not sure
        mp_music.seekTo(stopPosition2);
        mp_music.start(); //Or use resume() if it doesn't work. I'm not sure
    }
}





package com.example.escapegollum;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class g_video4 extends AppCompatActivity {


    private VideoView video;
    private static MediaPlayer mp_music;
    private Button continuar;
    TextView nombre_texto;
    private static final String TAG = "";
    static int stopPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_video4);

        mp_music = MediaPlayer.create(this, R.raw.menusonido);
        mp_music.start();
        mp_music.setLooping(true);

        nombre_texto = (TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        video=(VideoView) findViewById(R.id.e_dialogo2);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.g_video4;
        video.setVideoURI(Uri.parse(path));
        video.start();


    }


    @Override
    public void onBackPressed() {

    }



    public void continuar(View view) {

        Intent intent = new Intent(getApplicationContext(), h_dialogo4.class);
        intent.putExtra("jugador", nombre_texto.getText().toString());
        startActivity(intent);
        video.stopPlayback();
        mp_music.pause();
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
        if (this.isFinishing()){
            mp_music.stop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        video.seekTo(stopPosition);
        video.start(); //Or use resume() if it doesn't work. I'm not sure
    }
}






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

public class i_video5 extends AppCompatActivity {

    TextView nombre_texto;
    private VideoView video;
    private static final String TAG = "";
    static int stopPosition=0;
    private static MediaPlayer mp_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.i_video5);

        nombre_texto=(TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        mp_music = MediaPlayer.create(this, R.raw.menusonido);
        mp_music.start();
        mp_music.setLooping(true);

        video=(VideoView) findViewById(R.id.e_dialogo2);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.i_video5;
        video.setVideoURI(Uri.parse(path));

        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                video.start();
                mp.setVolume(0,0);

            }
        });

    }

    @Override
    public void onBackPressed() {

    }
    public void continuar(View view) {

        Intent intent = new Intent(this, j_dialogo5.class);
        intent.putExtra("jugador", nombre_texto.getText().toString());

        startActivity(intent);
        mp_music.pause();
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





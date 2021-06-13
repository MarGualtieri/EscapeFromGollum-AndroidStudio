package com.example.escapegollum;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.VideoView;


public class m_video8 extends AppCompatActivity {

    private VideoView video;
    private static MediaPlayer mp,mp_voice;
    private Button continuar;
    TextView nombre_texto;
    private static final String TAG = "";
    static int stopPosition=0;
    static int stopPosition2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_video8);

        mp_voice = MediaPlayer.create(this, R.raw.menusonido);
        mp_voice.start();
        mp_voice.setLooping(true);

        continuar = (Button) findViewById(R.id.continuar);
        nombre_texto=(TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        video=(VideoView) findViewById(R.id.e_dialogo2);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.m_video8;
        video.setVideoURI(Uri.parse(path));

        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                video.start();


            }
        });

    }

    @Override
    public void onBackPressed() {  // DESHABILITA LA FELCHA DE SALIDA DEL CELULAR

    }

    public void continuar(View view) {


        Intent intent = new Intent(this, n_video9.class);
        intent.putExtra("jugador", nombre_texto.getText().toString());
       /* mp_voice.stop();
        mp_voice.release();*/
        startActivity(intent);
        video.stopPlayback();
        finish();



    }

    public void salir(View view) {  // BOTON PARA SALIR
        mp_voice.seekTo(0);
        video.seekTo(0);
        mp.seekTo(0);
        moveTaskToBack(true);
        finish();
    }
    @Override
    public void onPause() {


        Log.d(TAG, "onPause called");
        super.onPause();
        stopPosition2 = mp_voice.getCurrentPosition(); //stopPosition is an int
        mp_voice.pause();
        stopPosition = video.getCurrentPosition(); //stopPosition is an int
        video.pause();
        if (this.isFinishing()){
            mp_voice.stop();
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        video.seekTo(stopPosition);
        video.start(); //Or use resume() if it doesn't work. I'm not sure
        mp_voice.seekTo(stopPosition2);
        mp_voice.start(); //Or use resume() if it doesn't work. I'm not sure
    }
}

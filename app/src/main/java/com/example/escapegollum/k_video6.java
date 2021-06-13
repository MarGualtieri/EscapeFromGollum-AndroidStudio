package com.example.escapegollum;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;



public class k_video6 extends AppCompatActivity {


    private VideoView video;
    private static MediaPlayer mp, mp_voice;
    private Button continuar;
    private EditText respuesta;
    String resultado="el viento";
    String resultado2="viento";
    TextView nombre_texto;
    private static final String TAG = "";
    static int stopPosition=0;
    static int stopPosition2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_video6);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("miNivel","6");
        editor.commit();

        nombre_texto=(TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        video = (VideoView) findViewById(R.id.e_dialogo2);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.k_video6;
        video.setVideoURI(Uri.parse(path));

        mp_voice = MediaPlayer.create(this, R.raw.audiodialogo);
        mp_voice.start();
        mp_voice.setLooping(true);


        continuar = (Button) findViewById(R.id.continuar);
        respuesta = (EditText) findViewById(R.id.respuesta);


        video.start();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {

                mp.setVolume(0, 0);
            }
        });
    }
    @Override
    public void onBackPressed() {

    }



    public void continuar(View view) {

        String stringRespuesta = respuesta.getText().toString();
        if (stringRespuesta.equals(resultado) || stringRespuesta.equals(resultado2) ) {

            Intent intent = new Intent(this, l_video7.class);
            intent.putExtra("jugador", nombre_texto.getText().toString());
          /*  mp_voice.stop();
            mp_voice.release();*/
            startActivity(intent);
            video.stopPlayback();
            finish();

        }else{
            Toast.makeText(this, "LA RESPUESTA NO ES CORRECTA", Toast.LENGTH_SHORT).show();
        }

    }

    public void salir(View view) {  // BOTON PARA SALIR

        mp_voice.seekTo(0);
        video.seekTo(0);
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






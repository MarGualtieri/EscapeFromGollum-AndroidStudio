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

public class l_video7 extends AppCompatActivity {

    private static final String TAG = "";
    static int stopPosition=0;
    static int stopPosition2=0;
    private VideoView video;
    private static MediaPlayer mp_voice,mp_dialogo;
    private Button continuar;
    private EditText respuesta;
    String resultado="el tiempo";
    String resultado2="tiempo";
    TextView nombre_texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_video7);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("miNivel","7");
        editor.commit();

        video=(VideoView) findViewById(R.id.e_dialogo2);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.l_video7;
        video.setVideoURI(Uri.parse(path));

        nombre_texto=(TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        mp_dialogo= MediaPlayer.create(this, R.raw.audiodialogo);
        mp_dialogo.start();
        mp_dialogo.setLooping(true);

        continuar = (Button) findViewById(R.id.continuar);
        respuesta = (EditText) findViewById(R.id.respuesta);


       video.start();


    }
    @Override
    public void onBackPressed() {

    }



    public void continuar(View view) {

        String stringRespuesta = respuesta.getText().toString();
        if (stringRespuesta.equals(resultado) || stringRespuesta.equals(resultado2) ) {

            Intent intent = new Intent(this, m_video8.class);
            intent.putExtra("jugador", nombre_texto.getText().toString());
          /*  mp_dialogo.stop();
            mp_dialogo.release();*/
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
        mp_dialogo.seekTo(0);
        moveTaskToBack(true);
        finish();
    }
    @Override
    public void onPause() {


        Log.d(TAG, "onPause called");
        super.onPause();
        stopPosition2 = mp_dialogo.getCurrentPosition(); //stopPosition is an int
        mp_dialogo.pause();
        stopPosition = video.getCurrentPosition(); //stopPosition is an int
        video.pause();
        if (this.isFinishing()){
            mp_dialogo.stop();
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        video.seekTo(stopPosition);
        video.start(); //Or use resume() if it doesn't work. I'm not sure
        mp_dialogo.seekTo(stopPosition2);
        mp_dialogo.start(); //Or use resume() if it doesn't work. I'm not sure
    }
}






package com.example.escapegollum;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;




public class a_dialogo1 extends AppCompatActivity {


    private static MediaPlayer mp, mp_voice;
    static int stopPosition=0;
    static int stopPosition2=0;
    private static final String TAG = "";
    String nombre_jugador;
    TextView nombre_texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_dialogo1);

         nombre_texto=(TextView) findViewById(R.id.nombre_texto);
         nombre_jugador = getIntent().getStringExtra("jugador");
         nombre_texto.setText(nombre_jugador);


        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        mp = MediaPlayer.create(this, R.raw.menusonido);
        mp.start();
        mp.setLooping(true);

        mp_voice = MediaPlayer.create(this, R.raw.introvoice);
        mp_voice.start();

    }




    @Override
    public void onBackPressed() {  // DESHABILITA LA FELCHA DE SALIDA DEL CELULAR

    }

    public void continuar(View view) {

        Intent intent = new Intent(this, b_video1.class);
        intent.putExtra("jugador", nombre_texto.getText().toString());
        /*mp_voice.stop();
        mp_voice.release();
        mp.stop();
        mp.release();*/
        startActivity(intent);
        finish();

    }
    public void salir(View view) {  // BOTON PARA SALIR

        mp_voice.seekTo(0);
        mp.seekTo(0);

        finish();

    }
    @Override
    public void onPause() {
        Log.d(TAG, "onPause called");
        super.onPause();
        stopPosition = mp.getCurrentPosition(); //stopPosition is an int
        mp.pause();
        stopPosition2 = mp_voice.getCurrentPosition(); //stopPosition is an int
        mp_voice.pause();

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        mp_voice.seekTo(stopPosition2);
        mp_voice.start(); //Or use resume() if it doesn't work. I'm not sure
        mp.seekTo(stopPosition);
        mp.start(); //Or use resume() if it doesn't work. I'm not sure
    }

    }

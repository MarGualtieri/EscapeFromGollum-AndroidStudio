package com.example.escapegollum;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


public class n_video9 extends AppCompatActivity {

    private static final String TAG = "";
    static int stopPosition=0;
    static int stopPosition2=0;
    private static MediaPlayer mp, mp_voice;
    private Button continuar;
    private EditText respuesta;
    TextView nombre_texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_video9);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("miNivel","8");
        editor.commit();

        mp = MediaPlayer.create(this, R.raw.audiodialogo);
        mp.start();
        mp.setLooping(true);

        mp_voice = MediaPlayer.create(this, R.raw.n_video9);
        mp_voice.start();


        nombre_texto=(TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        continuar = (Button) findViewById(R.id.continuar);
        respuesta = (EditText) findViewById(R.id.respuesta);


    }

    @Override
    public void onBackPressed() {  // DESHABILITA LA FELCHA DE SALIDA DEL CELULAR

    }

    public void continuar(View view) {
        String resultado = respuesta.getText().toString();
        if (TextUtils.isEmpty(resultado)) {
            Toast.makeText(this, "LA RESPUESTA NO ES CORRECTA", Toast.LENGTH_SHORT).show();

        } else if (!resultado.isEmpty()) {
            int stringRespuesta = Integer.parseInt(resultado);

            if (stringRespuesta == 32) {
                Intent intent = new Intent(this, o_video10.class);
                intent.putExtra("jugador", nombre_texto.getText().toString());
              /*  mp_voice.stop();
                mp_voice.release();
                mp.stop();
                mp.release();*/
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "LA RESPUESTA NO ES CORRECTA", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void salir(View view) {  // BOTON PARA SALIR
        mp_voice.seekTo(0);

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
        stopPosition = mp.getCurrentPosition(); //stopPosition is an int
        mp.pause();

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        mp.seekTo(stopPosition);
        mp.start(); //Or use resume() if it doesn't work. I'm not sure
        mp_voice.seekTo(stopPosition2);
        mp_voice.start(); //Or use resume() if it doesn't work. I'm not sure
    }
}

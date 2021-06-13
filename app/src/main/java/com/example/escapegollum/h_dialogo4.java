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


public class h_dialogo4 extends AppCompatActivity {


    private static MediaPlayer mp_music, mp_voice2;
    private Button continuar;
    private EditText respuesta;
    TextView nombre_texto;
    static int stopPosition=0;
    static int stopPosition2=0;
    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_dialogo4);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("miNivel","4");
        editor.commit();

        nombre_texto = (TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);



        mp_voice2 = MediaPlayer.create(this, R.raw.h_dialogo4);
       // mp_voice2.seekTo(0);
        mp_voice2.start();

        mp_music = MediaPlayer.create(this, R.raw.audiodialogo);
       // mp_music.seekTo(0);
        mp_music.start();
        mp_music.setLooping(true);





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

            if (stringRespuesta == 96) {
                Intent intent = new Intent(this, i_video5.class);
                intent.putExtra("jugador", nombre_texto.getText().toString());
                mp_voice2.pause();
                mp_music.pause();
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "LA RESPUESTA NO ES CORRECTA", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void salir(View view) {  // BOTON PARA SALIR

        mp_voice2.seekTo(0);
        mp_music.seekTo(0);
finish();
    }

   public void onPause() {
     /*  Log.d(TAG, "onPause called");
       super.onPause();
       stopPosition = mp_music.getCurrentPosition(); //stopPosition is an int
       mp_music.pause();
       stopPosition2 = mp_voice2.getCurrentPosition(); //stopPosition is an int
       mp_voice2.pause();*/
       super.onPause();
       if (mp_voice2 != null) {

           mp_voice2.pause();
           stopPosition2 = mp_voice2.getCurrentPosition();
           if (isFinishing()) {
               mp_voice2.stop();
               mp_voice2.release();

           }
       }
       if (mp_music != null) {
           mp_music.pause();
           stopPosition = mp_music.getCurrentPosition();
           if (isFinishing()) {
               mp_music.stop();
               mp_music.release();
           }
       }

   }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        mp_voice2.seekTo(stopPosition2);
        mp_voice2.start(); //Or use resume() if it doesn't work. I'm not sure
        mp_music.seekTo(stopPosition);
        mp_music.start(); //Or use resume() if it doesn't work. I'm not sure
    }


}

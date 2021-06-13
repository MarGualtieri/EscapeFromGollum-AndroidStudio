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

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class r_video13 extends AppCompatActivity {
    TextView nombre_texto;
    View screenview;
    View view;
    private static MediaPlayer mp;
    private Button continuar;
    private EditText respuesta;
    ImageButton anillo;
    private static final String TAG = "";
    static int stopPosition=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_video13);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("miNivel","10");
        editor.commit();

        ImageButton ani = findViewById(R.id.anillo);
        Glide.with(this).load(R.drawable.anillogif).into(ani);

        screenview=findViewById(R.id.rView);

        mp = MediaPlayer.create(this, R.raw.r_video13);
        mp.start();
        mp.setLooping(true);

        nombre_texto=(TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        continuar = (Button) findViewById(R.id.continuar);
        anillo = (ImageButton) findViewById(R.id.anillo);
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

            if (stringRespuesta == 125) {
                Intent intent = new Intent(this, s_video15.class);
                intent.putExtra("jugador", nombre_texto.getText().toString());
                /*mp.stop();
                mp.release();*/
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "LA RESPUESTA NO ES CORRECTA", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void salir(View view) {  // BOTON PARA SALIR

        mp.seekTo(0);

        moveTaskToBack(true);
        finish();
    }
    public void anillo(View view) {

        screenview.setBackgroundResource(R.drawable.r_video14);
    }
    @Override
    public void onPause() {

        Log.d(TAG, "onPause called");
        super.onPause();
        stopPosition = mp.getCurrentPosition(); //stopPosition is an int
        mp.pause();

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        mp.seekTo(stopPosition);
        mp.start(); //Or use resume() if it doesn't work. I'm not sure

    }
}

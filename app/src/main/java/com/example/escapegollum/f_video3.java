package com.example.escapegollum;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class f_video3 extends AppCompatActivity {
    private static final String TAG = "";
    static int stopPosition = 0;
    static int stopPosition2 = 0;
    String resultado = "las montañas";
    String resultado2 = "la montaña";
    String resultado3 = "montaña";
    String resultado4 = "montañas";
    TextView nombre_texto;
    private VideoView video;
    private EditText respuesta;
    int cont = 0;
    private Button continuar;
    private static MediaPlayer mp_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_video3);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("miNivel", "3");
        editor.commit();

        respuesta = (EditText) findViewById(R.id.respuesta);
        continuar = (Button) findViewById(R.id.continuar);

        nombre_texto = (TextView) findViewById(R.id.nombre_texto);
        String nombre_jugador = getIntent().getStringExtra("jugador");
        nombre_texto.setText(nombre_jugador);
        nombre_texto.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        mp_music = MediaPlayer.create(this, R.raw.audiodialogo);
        mp_music.start();
        mp_music.setLooping(true);

        video=(VideoView) findViewById(R.id.e_dialogo2);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.f_video3;
        video.setVideoURI(Uri.parse(path));
        video.start();
       /* video.setZOrderOnTop(true);

        SurfaceHolder surfaceholder = video.getHolder();
        surfaceholder.setFormat(PixelFormat.TRANSPARENT);
        //video.setZOrderOnTop(true);//this line solve the problem
        video.seekTo(1);
        */

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {


            }
        });

    }


    @Override
    public void onBackPressed() {

    }

    public void continuar(View view) {

        String stringRespuesta = respuesta.getText().toString();
        if (stringRespuesta.equals(resultado) || stringRespuesta.equals(resultado2) || stringRespuesta.equals(resultado3)|| stringRespuesta.equals(resultado4)) {

            Intent intent = new Intent(this, g_video4.class);
            intent.putExtra("jugador", nombre_texto.getText().toString());
            startActivity(intent);
            video.stopPlayback();
            mp_music.pause();
            finish();

        } else {
            Toast.makeText(this, "LA RESPUESTA NO ES CORRECTA", Toast.LENGTH_SHORT).show();
        }
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
        mp_music.seekTo(stopPosition2);
        mp_music.start(); //Or use resume() if it doesn't work. I'm not sure
    }
}





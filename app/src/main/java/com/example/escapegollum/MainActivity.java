package com.example.escapegollum;




import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;




public class MainActivity extends AppCompatActivity {


    private MediaPlayer mp = new MediaPlayer();
    private Button btn_jugar, btn_continuar;
    private TextView tv_bestScore;
    private EditText et_nombre;
    String nivelJugador = "0";
    static String valor = "";
    static String name = "";
    Context context = this;
      int stopPosition=0;
      String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mp = MediaPlayer.create(this, R.raw.menusonido);
        mp.start();
        mp.setLooping(true);

        btn_jugar = (Button) findViewById(R.id.Boton_jugar);
        btn_continuar = (Button) findViewById(R.id.continuar);
        tv_bestScore = (TextView) findViewById(R.id.nombreDeDatos);
        et_nombre = (EditText) findViewById(R.id.Ingresar_nombre);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String miNombre = preferences.getString("miNombre", "No hay datos");
        final String miNivel = preferences.getString("miNivel", "No hay datos");
        tv_bestScore.setText("Ultimo Jugador : " + miNombre + "    Nivel: " + miNivel);
        valor = miNivel;
        name = miNombre;
    }

    public void jugar(View view) {

        String nombre = et_nombre.getText().toString();

        if (!nombre.equals("")) {

            Intent intro = new Intent(this, a_dialogo1.class);
            intro.putExtra("jugador", et_nombre.getText().toString());
            mp.pause();
            startActivity(intro);
            finish();
        } else {
            Toast.makeText(this, "Debes escribir tu nombre antes", Toast.LENGTH_SHORT).show();
            et_nombre.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_nombre, InputMethodManager.SHOW_IMPLICIT);
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("miNombre", et_nombre.getText().toString());
        //editor.putString("miNivel", nivelJugador);
        editor.commit();


    }

    @Override
    public void onBackPressed() {  // DESHABILITA LA FELCHA DE SALIDA DEL CELULAR

    }

    public void salir(View view) {  // BOTON PARA SALIR
      //  mp.stop();
      //  mp.release();

        finish();
    }

    public void continuar(View view) {  // BOTON PARA SALIR
        int a = Integer.valueOf(valor);

        if(mp.isPlaying()){
            mp.reset();

        }


        switch (a) {
            case 1:
                Intent intro = new Intent(this, c_dialogo2.class);
                intro.putExtra("jugador", name);
                startActivity(intro);
                finish();
                break;

            case 2:
                Intent intro1 = new Intent(this, d_video3.class);
                intro1.putExtra("jugador", name);
                startActivity(intro1);
                finish();
                break;
            case 3:
                Intent intro2 = new Intent(this, f_video3.class);
                intro2.putExtra("jugador", name);
                startActivity(intro2);
                finish();
                break;
            case 4:
                Intent intro3 = new Intent(this, h_dialogo4.class);
                intro3.putExtra("jugador", name);
                startActivity(intro3);
                finish();
                break;
            case 5:
                Intent intro5 = new Intent(this, j_dialogo5.class);
                intro5.putExtra("jugador", name);
                startActivity(intro5);
                finish();
                break;
            case 6:
                Intent intro6 = new Intent(this, k_video6.class);
                intro6.putExtra("jugador", name);
                startActivity(intro6);
                finish();
                break;
            case 7:
                Intent intro7 = new Intent(this, l_video7.class);
                intro7.putExtra("jugador", name);
                startActivity(intro7);
                finish();
                break;
            case 8:
                Intent intro8 = new Intent(this, n_video9.class);
                intro8.putExtra("jugador", name);
                startActivity(intro8);
                finish();
                break;
            case 9:
                Intent intro9 = new Intent(this, p_video11.class);
                intro9.putExtra("jugador", name);
                startActivity(intro9);
                finish();
                break;
            case 10:
                Intent intro10 = new Intent(this, r_video13.class);
                intro10.putExtra("jugador", name);
                startActivity(intro10);
                finish();
                break;
            default:

        }

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




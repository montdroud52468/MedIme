package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

public class Configuracion extends AppCompatActivity implements View.OnClickListener {
    public CardView Elimi;
    Slider tamano;
    ConstraintLayout back;
    private ContentResolver contentResolver;
    private Window window;
    TextView text;
    float X;
    Switch swi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("CONFIGURACIONES");
        setContentView(R.layout.activity_configuracion);
        Elimi = (CardView) findViewById(R.id.eliminar);
        Elimi.setOnClickListener(this);
        back=(ConstraintLayout)findViewById(R.id.back);
        text=(TextView)findViewById(R.id.eje);
        tamano=(Slider) findViewById(R.id.slider1);
        swi=(Switch)findViewById(R.id.sw1th);
        contentResolver=getContentResolver();

        textsize();

        tamano.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                float value = tamano.getValue();
                Log.d("value= ",value+"");
                X=value;
                text.setTextSize(value);
                SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putFloat("Tamanio", value);
                editor.commit();



            }
        });


        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(swi.isChecked()){
                    updateTh("DARK","#424242","#9e9e9e");
                }else{
                    updateTh("DEFAULT","#fafafa","#fafafa");
                }
            }
        });


        cargar();

    }

    public void updateTh(String cla, String cl1, String cl2){
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("theme", cla);
        editor.commit();

        back.setBackgroundColor(Color.parseColor(cl1));
        Elimi.setBackgroundColor(Color.parseColor(cl2));
    }

    public void cargar(){
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String actual = preferencias.getString("theme","DEFAULT");

        if(actual.equals("DEFAULT")){
            updateTh("DEFAULT","#FFFFFF","#FF370083");
        }else if(actual.equals("DARK")){
            updateTh("DARK","#212121","#37474f");
            swi.setChecked(true);
        }
    }

    private void textsize() {
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        float pase = preferencias.getFloat("Tamanio", 15);
        Toast.makeText(getApplicationContext(), "Valor: "+pase, Toast.LENGTH_SHORT).show();
        text.setTextSize(pase);
        tamano.setValue(pase);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.eliminar:

                eliminardata();
                break;

        }
    }

    private void eliminardata() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("¿Estas seguro de eliminar todos los datos del usuario?").setTitle("Eliminacion de Datos");

        builder.setPositiveButton("Si, Eliminar Datos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Eliminando Datos", Toast.LENGTH_SHORT).show();
                Eliminar();
                Intent eli = new Intent(Configuracion.this, Carga.class);
                startActivity(eli);
                finish();
            }
        });
        builder.setNegativeButton("No Eliminar Datos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        //Toast.makeText(getApplicationContext(), "Eliminacion", Toast.LENGTH_SHORT).show();

    }

    //ELIMINA LOS DATOS COMPLETOS
    public void Eliminar() {
        SharedPreferences.Editor editor = getSharedPreferences("Credenciales", Context.MODE_PRIVATE).edit();
        editor.clear().apply();

    }
}
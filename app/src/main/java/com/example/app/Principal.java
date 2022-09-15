package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.textfield.TextInputLayout;

public class Principal extends AppCompatActivity implements View.OnClickListener{
    EditText cad1, cad2, cad3, cad4, cad5;
    Switch switch1, switch2;
    GridLayout layout;
    CardView omi, env;
    ScrollView padr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_principal);
        cad1 = (EditText) findViewById(R.id.Nombre);
        cad2 = (EditText) findViewById(R.id.Apellidos);
        cad3 = (EditText) findViewById(R.id.NombreT);
        cad4 = (EditText) findViewById(R.id.ApellidosT);
        cad5 = (EditText) findViewById(R.id.NumeroT);
        switch1 = (Switch) findViewById(R.id.sw1);
        switch2 = (Switch) findViewById(R.id.sw2);
        omi = (CardView) findViewById(R.id.Omitir);
        env = (CardView)findViewById(R.id.Enviar);
        omi.setOnClickListener(this);
        env.setOnClickListener(this);
        layout = (GridLayout) findViewById(R.id.enc);
        padr = (ScrollView) findViewById(R.id.padre);
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.int_ab_ar);
        //Cargamos el estado del Switch y asi bloquear los editText restantes
        cargarPreferencias();
        if (switch1.isChecked()) {
            ence();

        } else {
            apag();
        }
        cad1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int aux=0;
                if (switch1.isChecked()) {
                    if ((ActivityCompat.checkSelfPermission(Principal.this, Manifest.permission.SEND_SMS)//Permisos para enviar mensajes desde la app
                            != PackageManager.PERMISSION_GRANTED)) {
                        //Toast.makeText(this,"No",Toast.LENGTH_LONG).show();
                        switch1.setChecked(false);
                        apag();
                        cad1.setEnabled(false);
                        cad2.setEnabled(false);
                        cad3.setEnabled(false);
                        cad4.setEnabled(false);
                        cad5.setEnabled(false);
                        cad1.setText(null);
                        cad2.setText(null);
                        cad3.setText(null);
                        cad4.setText(null);
                        cad5.setText(null);
                        switch2.setChecked(false);
                        Toast.makeText(Principal.this,"Se requiere de activar mensajes para usar esta informacion",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        cad3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (switch2.isChecked()) {
                    if (ActivityCompat.checkSelfPermission(Principal.this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Principal.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        switch2.setChecked(false);
                        Toast.makeText(getApplicationContext(), "No se autorizo envios de GPS via SMS", Toast.LENGTH_SHORT).show();
                    }else{
                        switch2.setChecked(true);
                    }
                }
            }
        });
    }

    //Aqui se encienden los objetos en el switch
    public void ence() {
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.int_ab_ar);
        layout.setVisibility(View.VISIBLE);
        layout.setEnabled(true);
        layout.setAnimation(animacion);
        omi.setVisibility(View.GONE);
        omi.setEnabled(false);

    }

    //Aqui se encienden los objetos en el switch
    public void apag() {
        layout.setVisibility(View.GONE);
        layout.setEnabled(false);
        omi.setVisibility(View.VISIBLE);
        omi.setEnabled(true);
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.int_ab_ar);
        omi.setAnimation(animacion);
    }

    //Actualizacion del switch ya dentro en la intefaz
    public void validarSw1(View view) {
        if (switch1.isChecked()) {
            ence();
            cad1.setEnabled(true);
            cad2.setEnabled(true);
            cad3.setEnabled(true);
            cad4.setEnabled(true);
            cad5.setEnabled(true);
        } else {
            apag();
            cad1.setText(null);
            cad2.setText(null);
            cad3.setText(null);
            cad4.setText(null);
            cad5.setText(null);
        }
        if (switch1.isChecked()) {
            if ((ActivityCompat.checkSelfPermission(Principal.this, Manifest.permission.SEND_SMS)//Permisos para enviar mensajes desde la app
                    != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(Principal.this, new String[]{
                        Manifest.permission.SEND_SMS,}, 4000);
            }
        }
    }

    public void validarSw2(View view) {
        if (switch2.isChecked()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                switch2.setChecked(false);
                Toast.makeText(getApplicationContext(), "No se autorizo envios de GPS via SMS", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,}, 4000);
            } else {
                switch2.setChecked(true);
                Toast.makeText(getApplicationContext(), "Se autorizo envios de GPS via SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //En caso de que se edite esta informacion Se cargaran los datos que ya se habian puesto con anterioridad
    private void cargarPreferencias() {
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String usuario = preferencias.getString("Nombre", "No Existe la informacion");
        String apelli = preferencias.getString("Apellido", "No Existe la informacion");
        String usuariot = preferencias.getString("Nombret", "No Existe la informacion");
        String apellit = preferencias.getString("Apellidot", "No Existe la informacion");
        String numbert = preferencias.getString("Numerot", "No Existe la informacion");
        int pase = preferencias.getInt("MenuMidIme", 0);

        if (pase == 0) {
            Toast.makeText(getApplicationContext(), "CREA UN NUEVO USUARIO", Toast.LENGTH_SHORT).show();
        }
        if (pase == 1) {
            Toast.makeText(getApplicationContext(), "INICIASTE COMO:" + usuario + " " + apelli, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Principal.this, Presentacion27.class);
            startActivity(intent);
            finish();
        }
        if (pase == 2) {
            Toast.makeText(getApplicationContext(), "EDITA USUARIO", Toast.LENGTH_SHORT).show();
            switch1.setChecked(true);
            switch2.setChecked(true);
            cad1.setText(usuario);
            cad2.setText(apelli);
            cad3.setText(usuariot);
            cad4.setText(apellit);
            cad5.setText(numbert);
        }

    }

    //No llenamos nada en caso de que se omita la informacion y asi bloquear el boton emergencia en presentacion27
    private void Omitir() {
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String nom = "";
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("Nombre", nom);
        editor.putString("Apellido", nom);
        editor.putString("Nombret", nom);
        editor.putString("Apellidot", nom);
        editor.putString("Numerot", nom);
        editor.putInt("MenuMidIme", 1);

        editor.commit();
    }

    //Se guarda la informacion y se reactiva el boton de emergencia en presentacion27
    private void guardar() {
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String nom = cad1.getText().toString();
        String ape = cad2.getText().toString();
        String num = cad5.getText().toString();
        String nomt = cad3.getText().toString();
        String apet = cad4.getText().toString();

        if(nom.equals("")||ape.equals("")||num.equals("")||nomt.equals("")||apet.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Es necesario que llene toda la información").setTitle("DATOS INCOMPLETOS");

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("Nombre", nom);
            editor.putString("Apellido", ape);
            editor.putString("Nombret", nomt);
            editor.putString("Apellidot", apet);
            editor.putString("Numerot", num);
            editor.putInt("MenuMidIme", 1);

            editor.commit();
            Toast.makeText(getApplicationContext(), "Datos Guardados con Exito", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Presentacion27.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Enviar:
                guardar();
                break;
            case R.id.Omitir:
                Omitir();
                Toast.makeText(getApplicationContext(), "Datos Omitidos", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, Presentacion27.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}
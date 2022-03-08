package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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

public class Principal extends AppCompatActivity {
    EditText cad1, cad2, cad3, cad4, cad5;
    Switch switch1, switch2;
    GridLayout layout;
    Button omi, env;
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
        omi = (Button) findViewById(R.id.Omitir);
        layout = (GridLayout) findViewById(R.id.enc);
        padr = (ScrollView) findViewById(R.id.padre);

        //Cargamos el estado del Switch y asi bloquear los editText restantes
        cargarPreferencias();
        if (switch1.isChecked()) {
            ence();

        } else {
            apag();
        }
    }

    //Aqui se encienden los objetos en el switch
    public void ence() {

        layout.setVisibility(View.VISIBLE);
        layout.setEnabled(true);
        omi.setVisibility(View.GONE);
        omi.setEnabled(false);

    }

    //Aqui se encienden los objetos en el switch
    public void apag() {
        layout.setVisibility(View.GONE);
        layout.setEnabled(false);
        omi.setVisibility(View.VISIBLE);
        omi.setEnabled(true);
    }

    //Actualizacion del switch ya dentro en la intefaz
    public void validarSw(View view) {
        if (switch1.isChecked()) {
            ence();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)//Permisos para enviar mensajes desde la app
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                apag();
                switch1.setChecked(false);
                Toast.makeText(getApplicationContext(), "No se autorizo envios de SMS", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.SEND_SMS,}, 4000);
            } else {
                switch1.setChecked(true);
                Toast.makeText(getApplicationContext(), "Se autorizo envios de SMS", Toast.LENGTH_SHORT).show();
            }
        }else {
            apag();
        }

        if (switch2.isChecked()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                switch2.setChecked(false);
                Toast.makeText(getApplicationContext(), "No se autorizo envios de GPS via SMS", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,}, 4000);
            } else {
                switch1.setChecked(true);
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
        int pase = preferencias.getInt("Pase", 0);


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

    //Evento del boton
    public void onclickPrin(View view) {
        switch (view.getId()) {
            case R.id.Enviar:
                guardar();
                Toast.makeText(getApplicationContext(), "Datos Guardados con Exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Presentacion27.class);
                startActivity(intent);
                finish();
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
        editor.putInt("Pase", 1);
        editor.putString("ID", "0");

        editor.commit();
    }

    //Se guarda la informacion y se reactiva el boton de emergencia en presentacion27
    public void guardar() {
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String nom = cad1.getText().toString();
        String ape = cad2.getText().toString();
        String num = cad5.getText().toString();
        String nomt = cad3.getText().toString();
        String apet = cad4.getText().toString();

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("Nombre", nom);
        editor.putString("Apellido", ape);
        editor.putString("Nombret", nomt);
        editor.putString("Apellidot", apet);
        editor.putString("Numerot", num);
        editor.putString("ID", "0");
        editor.putInt("Pase", 1);

        editor.commit();

    }
}
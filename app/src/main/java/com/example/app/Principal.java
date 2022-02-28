package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Principal extends AppCompatActivity {
    TextInputLayout t1, t2, t3;
    EditText cad1, cad2, cad3, cad4, cad5;
    Switch sw1;
    GridLayout layout;
    Button omi, env;
    ScrollView padr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        cad1 = (EditText) findViewById(R.id.Nombre);
        cad2 = (EditText) findViewById(R.id.Apellidos);
        cad3 = (EditText) findViewById(R.id.NombreT);
        cad4 = (EditText) findViewById(R.id.ApellidosT);
        cad5 = (EditText) findViewById(R.id.NumeroT);
        sw1 = (Switch) findViewById(R.id.sw1);
        omi = (Button) findViewById(R.id.Omitir);
        layout = (GridLayout) findViewById(R.id.enc);
        padr = (ScrollView) findViewById(R.id.padre);

        cargarPreferencias();
        if (sw1.isChecked()) {
            ence();

        } else {
            apag();
        }
    }

    public void ence() {

        layout.setVisibility(View.VISIBLE);
        layout.setEnabled(true);
        omi.setVisibility(View.INVISIBLE);
        omi.setEnabled(false);

    }

    public void apag() {
        layout.setVisibility(View.INVISIBLE);
        layout.setEnabled(false);
        omi.setVisibility(View.VISIBLE);
        omi.setEnabled(true);
    }

    public void validarSw(View view) {
        if (sw1.isChecked()) {
            ence();

        } else {
            apag();
        }
    }

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
            sw1.setChecked(true);
            cad1.setText(usuario);
            cad2.setText(apelli);
            cad3.setText(usuariot);
            cad4.setText(apellit);
            cad5.setText(numbert);
        }

    }

    public void onclickPrin(View view) {
        switch (view.getId()) {
            case R.id.Enviar:
                guardar();
                Toast.makeText(getApplicationContext(), "Datos Guardados con Exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Principal.this, Presentacion27.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Omitir:
                Omitir();
                Toast.makeText(getApplicationContext(), "Datos Omitidos", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Principal.this, Presentacion27.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

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

        editor.commit();
    }

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
        editor.putInt("Pase", 1);

        editor.commit();

    }
}
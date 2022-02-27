package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    EditText cad1,cad2,cad3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        cad1   = (EditText) findViewById(R.id.Nombre);
        cad2   = (EditText) findViewById(R.id.Apellidos);
        cad3   = (EditText) findViewById(R.id.Numero);

        cargarPreferencias();
    }

    private void cargarPreferencias() {
        SharedPreferences preferencias=getSharedPreferences("Credenciales",Context.MODE_PRIVATE);

        String usuario=preferencias.getString("Nombre","No Existe la informacion");
        String apelli=preferencias.getString("Apellido","No Existe la informacion");
        String number=preferencias.getString("Numero","No Existe la informacion");
        int pase=preferencias.getInt("Pase",0);



        if(pase==0){
            Toast.makeText(getApplicationContext(),"CREA UN NUEVO USUARIO",Toast.LENGTH_SHORT).show();
        }
        if(pase==1){
            Toast.makeText(getApplicationContext(),"INICIASTE COMO:"+usuario+" "+apelli,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Principal.this,Presentacion27.class);
            startActivity(intent);
            finish();
        }
        if(pase==2){
            Toast.makeText(getApplicationContext(),"EDITA USUARIO",Toast.LENGTH_SHORT).show();
            cad1.setText(usuario);
            cad2.setText(apelli);
            cad3.setText(number);
        }

    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.Enviar:
                guardar();
                Toast.makeText(getApplicationContext(),"Datos Guardados con Exito",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Principal.this,Presentacion27.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Omitir:
                guardar();
                Toast.makeText(getApplicationContext(),"Datos Guardados con Exito",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Principal.this,Presentacion27.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    public void guardar(){
        SharedPreferences preferencias=getSharedPreferences("Credenciales",Context.MODE_PRIVATE);

        String nom=cad1.getText().toString();
        String ape=cad2.getText().toString();
        String num=cad3.getText().toString();

        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("Nombre",nom);
        editor.putString("Apellido",ape);
        editor.putString("Numero",num);
        editor.putInt("Pase",1);

        editor.commit();

    }
}
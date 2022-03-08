package com.example.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoNoti extends AppCompatActivity {

    EditText res1n,res2n,res3n,res4n,res5n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_resultado_noti);
        res1n=(EditText)findViewById(R.id.NomMedRes);
        res2n=(EditText)findViewById(R.id.TieMediRes);
        res3n=(EditText)findViewById(R.id.CanMediRes);
        res4n=(EditText)findViewById(R.id.CatMediRes);
        res5n=(EditText)findViewById(R.id.TotMediRes);
        cargarDatos();
    }

    private void cargarDatos() {
        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        String dato=getIntent().getStringExtra("ResID");
        SharedPreferences preferencias2 = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String Tie = preferencias.getString(dato, "No Existe la informacion");
        String Tom = preferencias.getString(Tie, "No Existe la informacion");
        String Medi = preferencias.getString(Tom, "No Existe la informacion");
        String Can = preferencias.getString(Medi, "No Existe la informacion");
        float tam = preferencias2.getFloat("Tamanio",15);

        res1n.setEnabled(false);
        res2n.setEnabled(false);
        res3n.setEnabled(false);
        res4n.setEnabled(false);
        res5n.setEnabled(false);

        res1n.setTextSize(tam);
        res2n.setTextSize(tam);
        res3n.setTextSize(tam);
        res4n.setTextSize(tam);
        res5n.setTextSize(tam);

        res1n.setText(dato);
        res2n.setText(Tie);
        res3n.setText(Tom);
        res4n.setText(Medi);
        res5n.setText(Can);

    }

}
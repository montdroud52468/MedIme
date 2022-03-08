package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.security.PrivilegedExceptionAction;

public class ResultadoNoti extends AppCompatActivity implements View.OnClickListener{

    EditText res1n,res2n,res3n,res4n,res5n;
    CardView card1,card2,card3;
    String dato,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_resultado_noti);
        dato=getIntent().getStringExtra("ResID");
        this.setTitle("RECORDATORIO "+dato);
        res1n=(EditText)findViewById(R.id.NomMedRes);
        res2n=(EditText)findViewById(R.id.TieMediRes);
        res3n=(EditText)findViewById(R.id.CanMediRes);
        res4n=(EditText)findViewById(R.id.CatMediRes);
        res5n=(EditText)findViewById(R.id.TotMediRes);
        card1=(CardView)findViewById(R.id.EditarResNot);
        card2=(CardView)findViewById(R.id.EliminarResNot);
        card3=(CardView)findViewById(R.id.GuardarResNot);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);

        cargarDatos();
    }

    private void cargarDatos() {
        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        dato=getIntent().getStringExtra("ResID");
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



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.EditarResNot:
                Editar();
                break;
            case R.id.EliminarResNot:
                Elimina();
                break;
            case R.id.GuardarResNot:
                Guardar();
                break;
        }
    }

    private void Guardar() {
        card3.setVisibility(View.GONE);
        card1.setVisibility(View.VISIBLE);

        res2n.setEnabled(false);
        res3n.setEnabled(false);
        res4n.setEnabled(false);
        res5n.setEnabled(false);

        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);

        String uno= res1n.getText().toString();
        String dos = res2n.getText().toString();
        String tres = res3n.getText().toString();
        String cuatro = res4n.getText().toString();
        String cinco = res5n.getText().toString();

        SharedPreferences.Editor editor = preferencias.edit();

        editor.remove(dos);
        editor.remove(tres);
        editor.remove(cuatro);
        editor.remove(cinco);

        editor.putString(uno, dos);
        editor.putString(dos, tres);
        editor.putString(tres, cuatro);
        editor.putString(cuatro, cinco);

        editor.commit();

    }

    private void Elimina() {

        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);

        String uno= res1n.getText().toString();
        String dos = res2n.getText().toString();
        String tres = res3n.getText().toString();
        String cuatro = res4n.getText().toString();
        String cinco = res5n.getText().toString();

        SharedPreferences.Editor editor = preferencias.edit();
        id=getIntent().getStringExtra("IDRea");
        Toast.makeText(this, "dato: " +id, Toast.LENGTH_SHORT).show();
        editor.remove(id);
        editor.remove(uno);
        editor.remove(dos);
        editor.remove(tres);
        editor.remove(cuatro);
        editor.remove(cinco);

        editor.commit();
        Intent regreso=new Intent(this, Presentacion27.class);
        startActivity(regreso);
        finish();

    }

    private void Editar() {
        card3.setVisibility(View.VISIBLE);
        card1.setVisibility(View.GONE);

        res2n.setEnabled(true);
        res3n.setEnabled(true);
        res4n.setEnabled(true);
        res5n.setEnabled(true);
    }
}
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.security.PrivilegedExceptionAction;

public class ResultadoNoti extends AppCompatActivity implements View.OnClickListener{

    EditText res1n,res2n,res3n,res4n,res5n;
    CardView card1,card2,card3;
    String dato,id;
    TextView fe;
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
        fe=(TextView)findViewById(R.id.textView);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);

        cargarDatos();
    }

    private void cargarDatos() {
        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        dato=getIntent().getStringExtra("ResID");
        SharedPreferences preferenciast = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        SharedPreferences preferencias1 = getSharedPreferences("nombremed", Context.MODE_PRIVATE);
        SharedPreferences preferencias2 = getSharedPreferences("tiempo", Context.MODE_PRIVATE);
        SharedPreferences preferencias3 = getSharedPreferences("tiempo2", Context.MODE_PRIVATE);
        SharedPreferences preferencias4 = getSharedPreferences("cantidad", Context.MODE_PRIVATE);
        SharedPreferences preferencias5 = getSharedPreferences("cantidadt", Context.MODE_PRIVATE);
        SharedPreferences preferencias6 = getSharedPreferences("fecha", Context.MODE_PRIVATE);
        SharedPreferences preferencias7 = getSharedPreferences("fechaf", Context.MODE_PRIVATE);

        String Tie = preferencias2.getString(dato, "No Existe la informacion");
        String Tom = preferencias3.getString(dato, "No Existe la informacion");
        String Medi = preferencias4.getString(dato, "No Existe la informacion");
        String Can = preferencias5.getString(dato, "No Existe la informacion");
        String fecha= preferencias7.getString(dato,"");
        float tam = preferenciast.getFloat("Tamanio",15);

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
        //fe.setTextSize(tam);

        res1n.setText(dato);
        res2n.setText(Tie);
        res3n.setText(Tom);
        res4n.setText(Medi);
        res5n.setText(Can);
        fe.setText("La proxima dosis es el dia "+fecha+" horas");

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
        SharedPreferences preferencias1 = getSharedPreferences("nombremed", Context.MODE_PRIVATE);
        SharedPreferences preferencias2 = getSharedPreferences("tiempo", Context.MODE_PRIVATE);
        SharedPreferences preferencias3 = getSharedPreferences("tiempo2", Context.MODE_PRIVATE);
        SharedPreferences preferencias4 = getSharedPreferences("cantidad", Context.MODE_PRIVATE);
        SharedPreferences preferencias5 = getSharedPreferences("cantidadt", Context.MODE_PRIVATE);

        String uno = res1n.getText().toString();
        String dos = res2n.getText().toString();
        String tres = res3n.getText().toString();
        String cuatro = res4n.getText().toString();
        String cinco = res5n.getText().toString();

        SharedPreferences.Editor editor = preferencias.edit();
        SharedPreferences.Editor editor1 = preferencias1.edit();
        SharedPreferences.Editor editor2 = preferencias2.edit();
        SharedPreferences.Editor editor3 = preferencias3.edit();
        SharedPreferences.Editor editor4 = preferencias4.edit();
        SharedPreferences.Editor editor5 = preferencias5.edit();

        editor2.remove(dos);
        editor3.remove(tres);
        editor4.remove(cuatro);
        editor5.remove(cinco);

        editor2.putString(uno, dos);
        editor3.putString(uno, tres);
        editor4.putString(uno, cuatro);
        editor5.putString(uno, cinco);

        editor.commit();
        editor1.commit();
        editor2.commit();
        editor3.commit();
        editor4.commit();
        editor5.commit();


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
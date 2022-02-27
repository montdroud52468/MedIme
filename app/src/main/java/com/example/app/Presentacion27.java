package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Presentacion27 extends AppCompatActivity implements View.OnClickListener {
    int alar=0;
    public CardView card1,card2,card3,card4,card5,card6,help;
    TextView no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion27);

        no= (TextView)findViewById(R.id.info2);
        card1=(CardView)findViewById(R.id.uno);
        card2=(CardView)findViewById(R.id.dos);
        card3=(CardView)findViewById(R.id.tres);
        card4=(CardView)findViewById(R.id.cuatro);
        card5=(CardView)findViewById(R.id.cinco);
        card6=(CardView)findViewById(R.id.seis);
        help=(CardView)findViewById(R.id.emergencia);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        help.setOnClickListener(this);

        Obtener();
    }
    private void Obtener() {
        SharedPreferences preferencias=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String usuario=preferencias.getString("Nombre","No Existe la informacion");
        String apelli=preferencias.getString("Apellido","No Existe la informacion");
        String number=preferencias.getString("Numero","No Existe la informacion");
        int pase=preferencias.getInt("Pase",0);

        String fin =usuario+" "+apelli;

        no.setText(fin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.uno:
                Toast.makeText(getApplicationContext(),"Todos los Recordatorios",Toast.LENGTH_SHORT).show();
                Intent agre = new Intent(Presentacion27.this,RECORDATORIOS.class);
                startActivity(agre);
                break;
            case R.id.dos:
                Toast.makeText(getApplicationContext(),"Cargando datos del usuario",Toast.LENGTH_SHORT).show();
                SharedPreferences preferencias=getSharedPreferences("Credenciales",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferencias.edit();
                editor.putInt("Pase",2);
                editor.commit();
                Intent edi = new Intent(Presentacion27.this,Carga.class);
                startActivity(edi);
                break;
            case R.id.tres:
                Toast.makeText(getApplicationContext(),"Editar Recordatorio",Toast.LENGTH_SHORT).show();
                Intent ediR= new Intent(this,EditaRecord.class);
                startActivity(ediR);
                break;
            case R.id.cuatro:
                Toast.makeText(getApplicationContext(),"MEDICAMENTO RESTANTE",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cinco:
                Toast.makeText(getApplicationContext(),"Calendario",Toast.LENGTH_SHORT).show();
                break;
            case R.id.seis:
                Toast.makeText(getApplicationContext(),"Eliminando Datos",Toast.LENGTH_SHORT).show();
                Eliminar();
                Intent eli = new Intent(Presentacion27.this,Carga.class);
                startActivity(eli);
                finish();
                break;
            case R.id.emergencia:
                Alarma();
                break;


        }
    }
    public void Eliminar(){
        SharedPreferences.Editor editor=getSharedPreferences("Credenciales",Context.MODE_PRIVATE).edit();
        editor.clear().apply();

    }
     public void Alarma(){
        alar++;
        if(alar<3){
            int eme=3-alar;
            Toast.makeText(getApplicationContext(),"Haz precionado Alerta "+alar+" Restantes: "+eme+" para enviar mensaje",Toast.LENGTH_SHORT).show();
        }else{
            alar=0;
            Toast.makeText(getApplicationContext(),"SE ENVIO UN MENSAJE DE EMERGENCIA",Toast.LENGTH_SHORT).show();
        }
     }
}
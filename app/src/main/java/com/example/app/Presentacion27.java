package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Presentacion27 extends AppCompatActivity implements View.OnClickListener {
    int alar = 0;
    public CardView card1, card2, card3, card4, card5, card6, help;
    TextView no,txtEme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion27);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        != PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.SEND_SMS,},1000);
            }else {

        };

        no = (TextView) findViewById(R.id.info2);
        txtEme = (TextView) findViewById(R.id.TxtEme);
        card1 = (CardView) findViewById(R.id.uno);
        card2 = (CardView) findViewById(R.id.dos);
        card3 = (CardView) findViewById(R.id.tres);
        card4 = (CardView) findViewById(R.id.cuatro);
        card5 = (CardView) findViewById(R.id.cinco);
        card6 = (CardView) findViewById(R.id.seis);
        help = (CardView) findViewById(R.id.emergencia);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        help.setOnClickListener(this);

        infor();
        Obtener();
    }

    private void infor() {
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String usuario = preferencias.getString("Nombre", "No Existe la informacion");
        String apelli = preferencias.getString("Apellido", "No Existe la informacion");
        String usuariot = preferencias.getString("Nombret", "No Existe la informacion");
        String apellit = preferencias.getString("Apellidot", "No Existe la informacion");
        String numbert = preferencias.getString("Numerot", "No Existe la informacion");

        if(numbert==""){
            help.setEnabled(false);
            txtEme.setText("Solo funciona al llenar formulario");
        }

    }

    private void Obtener() {
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String usuario = preferencias.getString("Nombre", "No Existe la informacion");
        String apelli = preferencias.getString("Apellido", "No Existe la informacion");
        String usuariot = preferencias.getString("Nombret", "No Existe la informacion");
        String apellit = preferencias.getString("Apellidot", "No Existe la informacion");
        String numbert = preferencias.getString("Numerot", "No Existe la informacion");

        int pase = preferencias.getInt("Pase", 0);

        String fin = usuario + " " + apelli;

        no.setText(fin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.uno:
                Toast.makeText(getApplicationContext(), "Todos los Recordatorios", Toast.LENGTH_SHORT).show();
                Intent agre = new Intent(Presentacion27.this, RECORDATORIOS.class);
                startActivity(agre);
                break;
            case R.id.dos:
                Toast.makeText(getApplicationContext(), "Cargando datos del usuario", Toast.LENGTH_SHORT).show();
                SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putInt("Pase", 2);
                editor.commit();
                Intent edi = new Intent(Presentacion27.this, Carga.class);
                startActivity(edi);
                break;
            case R.id.tres:
                Toast.makeText(getApplicationContext(), "Editar Recordatorio", Toast.LENGTH_SHORT).show();
                Intent ediR = new Intent(this, EditaRecord.class);
                startActivity(ediR);
                break;
            case R.id.cuatro:
                Toast.makeText(getApplicationContext(), "MEDICAMENTO RESTANTE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cinco:
                Toast.makeText(getApplicationContext(), "Calendario", Toast.LENGTH_SHORT).show();
                break;
            case R.id.seis:
                eliminardata();
                break;
            case R.id.emergencia:
                Alarma();
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
                Intent eli = new Intent(Presentacion27.this, Carga.class);
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

    public void Eliminar() {
        SharedPreferences.Editor editor = getSharedPreferences("Credenciales", Context.MODE_PRIVATE).edit();
        editor.clear().apply();

    }

    public void Alarma() {
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String usuariot = preferencias.getString("Nombret", "No Existe la informacion");
        String apellit = preferencias.getString("Apellidot", "No Existe la informacion");
        String numbert = preferencias.getString("Numerot", "No Existe la informacion");
        alar++;
        if (alar < 3) {
            int eme = 3 - alar;
            Toast.makeText(getApplicationContext(), "Haz precionado Alerta " + alar + " Restantes: " + eme + " para enviar mensaje", Toast.LENGTH_SHORT).show();
        } else {
            MSJ();
            alar = 0;
        }
    }

    private void MSJ() {
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String usuario = preferencias.getString("Nombre", "No Existe la informacion");
        String apelli = preferencias.getString("Apellido", "No Existe la informacion");
        String usuariot = preferencias.getString("Nombret", "No Existe la informacion");
        String apellit = preferencias.getString("Apellidot", "No Existe la informacion");
        String numbert = preferencias.getString("Numerot", "No Existe la informacion");

        enviarMensaje(numbert,"Tu familiar "+usuario+" "+apelli+" Se encuentra en riesgo de favor de comunicrate con el en caso de que no conteste llama a emergencias.");
    }

    private void enviarMensaje(String num, String str) {
        try {
            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(num,null,str,null,null);
            Toast.makeText(getApplicationContext(),"Mensaje enviado", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Mensaje no enviado Vuelva intentarlo", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
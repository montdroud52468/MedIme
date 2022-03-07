package com.example.app;

import androidx.annotation.NonNull;
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
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class Presentacion27 extends AppCompatActivity implements View.OnClickListener {
    int alar = 0;
    public CardView card1, card2, card3, card4, card5, card6, help;
    TextView no, txtEme,txtuno, txtdos,txttre,txtcua ,txtcin ,txtsei,txtsie;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Ubicacion ub= new Ubicacion(this);
        Toast.makeText(getApplicationContext(),"ACTULIZANDO UBICACION",Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_presentacion27);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)//Permisos para enviar mensajes desde la app
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.SEND_SMS,}, 1000);
        } else {

        }

        no = (TextView) findViewById(R.id.info2);
        txtEme = (TextView) findViewById(R.id.TxtEme);
        txtuno = (TextView) findViewById(R.id.txtrec);
        txtdos = (TextView) findViewById(R.id.txtinfo);
        txttre = (TextView) findViewById(R.id.txted1);
        txtcua = (TextView) findViewById(R.id.txted2);
        txtcin = (TextView) findViewById(R.id.txtmed1);
        txtsei = (TextView) findViewById(R.id.txtmed2);
        txtsie = (TextView) findViewById(R.id.txtconf);
        card1 = (CardView) findViewById(R.id.uno);
        card2 = (CardView) findViewById(R.id.dos);
        card3 = (CardView) findViewById(R.id.tres);
        card4 = (CardView) findViewById(R.id.cuatro);
        card5 = (CardView) findViewById(R.id.cinco);
        card6 = (CardView) findViewById(R.id.seis);
        help = (CardView) findViewById(R.id.emergencia);

        card1.setOnClickListener(this);//Solo son los cardView que los hice como botones
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        help.setOnClickListener(this);

        infor();//Para que no me funione el boton emergencia en caso de no llenar el primer formulario y cambiamos la etiqueta de la presentacion
    }



    private void infor() {//Para que no me funione el boton emergencia en caso de no llenar el primer formulario y cambiamos la etiqueta de la presentacion
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String usuario = preferencias.getString("Nombre", "No Existe la informacion");
        String apelli = preferencias.getString("Apellido", "No Existe la informacion");
        String usuariot = preferencias.getString("Nombret", "No Existe la informacion");
        String apellit = preferencias.getString("Apellidot", "No Existe la informacion");
        String numbert = preferencias.getString("Numerot", "No Existe la informacion");

        int pase = preferencias.getInt("Pase", 0);

        String fin = usuario + " " + apelli;

        no.setText(fin);

        if (usuario == "" || apelli == "" || usuariot == "" || apellit == "" || numbert == "") {
            help.setEnabled(false);
            txtEme.setText("Solo funciona al llenar formulario");
        }

    }

    //Evento para cada uno de los CardView como botones
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
                Toast.makeText(getApplicationContext(), "MEDICAMENTO RESTANTE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.seis:
                Toast.makeText(getApplicationContext(), "Calendario", Toast.LENGTH_SHORT).show();
                Intent Conf = new Intent(this, Configuracion.class);
                startActivity(Conf);
                break;
            case R.id.emergencia:
                Alarma();
                break;


        }
    }

    //Para un mensaje emergente en caso de borrar los datos completos USUARIO Y MEDICAMENTO


    //Solo es como un seguro en caso de precionar el boton de emergencia 3 veces
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

    //Para enviar el mensaje
    private void MSJ() {
        Ubicacion u= new Ubicacion(this);
        Toast.makeText(getApplicationContext(),"ACTULIZANDO UBICACION",Toast.LENGTH_LONG).show();
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String usuario = preferencias.getString("Nombre", "No Existe la informacion");
        String apelli = preferencias.getString("Apellido", "No Existe la informacion");
        String usuariot = preferencias.getString("Nombret", "No Existe la informacion");
        String apellit = preferencias.getString("Apellidot", "No Existe la informacion");
        String numbert = preferencias.getString("Numerot", "No Existe la informacion");
        String ub = preferencias.getString("Ubicacion", "No hay info de GPS");
        enviarMensaje(numbert, "¡¡¡EMERGENCIA!!!\nTu familiar " + usuario + " " + apelli + " se encuentra en riesgo,por favor comunicate con él, en caso de que no conteste llama a emergencias.");
        Toast.makeText(this,ub,Toast.LENGTH_LONG).show();
        enviarMensaje(numbert," La encuentras en: "+"\n\nhttps://maps.google.com/?q="+ub);

    }

    //Verificacion del envio de mensaje
    private void enviarMensaje(String num, String str) {
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(num, null, str, null, null);
            Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Mensaje no enviado Vuelva intentarlo", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
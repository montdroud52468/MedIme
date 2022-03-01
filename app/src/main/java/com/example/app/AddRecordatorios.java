package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class AddRecordatorios extends AppCompatActivity implements View.OnClickListener {


    private Button noti;
    private PendingIntent pendingIntent, editarRec, informacionRec;
    private final static String CHANNEL_ID = "notifiacion";
    public final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recordatorios);

        noti = findViewById(R.id.not);

    }

    @Override
    public void onClick(View view) {//Evento paralos botones
        switch (view.getId()) {
            case R.id.not:

                setPendingIntent();
                setPendinfo();
                setPendedita();

                createNoficationChannel();
                createNofication();
                break;
        }

    }

    private void setPendinfo() {
        Intent intent = new Intent(this, ResultadoNoti.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ResultadoNoti.class);
        stackBuilder.addNextIntent(intent);
        informacionRec = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private void setPendedita() {
        Intent intent = new Intent(this, EditaRecord.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(EditaRecord.class);
        stackBuilder.addNextIntent(intent);
        editarRec = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private void setPendingIntent() {
        Intent intent = new Intent(this, RECORDATORIOS.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(RECORDATORIOS.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private void createNoficationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notificacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            /*NotificationCompat.Builder builder= new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
            builder.setSmallIcon(R.drawable.iconcap,2000);
            builder.setContentTitle("Titulo de la notificacion");
            builder.setContentText("Informacion sobre la notificacion blablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablabla");
            builder.setColor(Color.BLACK);
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
            builder.setLights(Color.MAGENTA,1000,1000);
            builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
            builder.setDefaults(Notification.DEFAULT_SOUND);
            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getApplicationContext());
            notificationManagerCompat.notify(NOTIFICACION_ID,builder.build());*/
        }
    }

    private void createNofication() {//Funciona para version android 10
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String usuario = preferencias.getString("Nombre", "No Existe la informacion");
        String apelli = preferencias.getString("Apellido", "No Existe la informacion");
        String number = preferencias.getString("Numero", "No Existe la informacion");
        int pase = preferencias.getInt("Pase", 0);

        String fin = usuario + " " + apelli;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.iconcap, 2000);
        builder.setContentTitle(usuario);
        builder.setContentText("Esta es una notificacion para " + usuario + " " + apelli + " Con el numero de celular " + number + " Tiene un medicamento que tomar llamando" + " No se a registrado medicamento prueba final");
        builder.setColor(Color.BLACK);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setContentIntent(pendingIntent);
        builder.addAction(R.drawable.ic_baseline_info_24, "Informacion", informacionRec);
        builder.addAction(R.drawable.ic_baseline_edit_24, "Editar Recordatorio", editarRec);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }


}
package com.example.app;

import static android.app.Notification.VISIBILITY_PUBLIC;

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
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class AddRecordatorios extends AppCompatActivity implements View.OnClickListener {


    private Button noti;
    private PendingIntent pendingIntent, editarRec, informacionRec;
    private final static String CHANNEL_ID = "notifiacion";
    public final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recordatorios);

        noti =(Button) findViewById(R.id.not);

    }


    @Override
    public void onClick(View view) {//Evento paralos botones
        switch (view.getId()) {
            case R.id.not:

                esperarYCerrar(5000);

                break;
        }

    }

    public void esperarYCerrar(int milisegundos) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                setPendingIntent();
                setPendinfo();
                setPendedita();

                creaNotificacion();
                creaNotificacionNuevas();
            }
        }, milisegundos);
    }

    private void setPendinfo() {
        Intent intent = new Intent(this, RECORDATORIOS.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(RECORDATORIOS.class);
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
        Intent intent = new Intent(this, ResultadoNoti.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ResultadoNoti.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    //Funciona en versiones viejas de android
    private void creaNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notificacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    //Funciona para version android 10
    private void creaNotificacionNuevas() {
        SharedPreferences preferencias = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String usuario = preferencias.getString("Nombre", "No Existe la informacion");
        String apelli = preferencias.getString("Apellido", "No Existe la informacion");
        String number = preferencias.getString("Numerot", "No Existe la informacion");
        int pase = preferencias.getInt("Pase", 0);

        String fin = usuario + " " + apelli;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.iconcap, 2000);
        builder.setContentTitle(usuario);
        builder.setContentText("Esta es una notificacion para " + usuario + " " + apelli + " Con el numero de celular " + number + " Tiene un medicamento que tomar llamando" + " No se a registrado medicamento prueba final");
        builder.setColor(Color.CYAN);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        builder.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setContentIntent(pendingIntent);
        builder.addAction(R.drawable.ic_baseline_info_24, "Recordatorios", informacionRec);
        builder.addAction(R.drawable.ic_baseline_edit_24, "Editar Recordatorio", editarRec);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }


}
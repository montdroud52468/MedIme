package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

public class Pruebas extends AppCompatActivity implements View.OnClickListener {
    CardView boton;
    String nombre,tiempo,pastillas, cantidad_total_pastillas,tipo,imagen,presentacion;
    Vibrator vibrator;
    static final String CHANNEL_ID="canal";
    PendingIntent pendingIntent,YaMedicamento,VerMedicamentos;
    int clr;
    int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);
        vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        boton=(CardView) findViewById(R.id.boton);
        boton.setOnClickListener(this);
    }

    private void setSiPendingIntent(){
        Intent intent = new Intent(this, Presentacion27.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Presentacion27.class);
        stackBuilder.addNextIntent(intent);
        YaMedicamento = stackBuilder.getPendingIntent(x, PendingIntent.FLAG_UPDATE_CURRENT);
    }
    private void setNoPendingIntent(){
        Intent intent = new Intent(this, EditaRecord.class);
        intent.putExtra("RestanteID",nombre);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(AddRecordatorios.class);
        stackBuilder.addNextIntent(intent);
        VerMedicamentos = stackBuilder.getPendingIntent(x, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void setPendingIntent(){
        Intent intent = new Intent(this, ResultadoNoti.class);
        intent.putExtra("ResID",nombre);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ResultadoNoti.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(x, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Noticacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification(){
        SharedPreferences preferencias8 = getSharedPreferences("imagen", Context.MODE_PRIVATE);
        imagen=preferencias8.getString(nombre,"");
        if (imagen==""){
            Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.iconcap);
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setSmallIcon(R.drawable.iconcap)
                    .setContentTitle(nombre.toUpperCase())
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentText(presentacion)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.iconcap)
                    .setCategory(NotificationCompat.CATEGORY_CALL)
                    .setAutoCancel(true)
                    .setSound(soundUri)
                    .setColor(clr)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setLargeIcon(img)
                    .addAction(R.drawable.iconcap,"Ya lo he tomado",YaMedicamento)
                    .addAction(R.drawable.iconcap,"Ver medicamentos",VerMedicamentos)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(img)
                            .bigLargeIcon(null));
            vibrator.vibrate(1000);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
            notificationManagerCompat.notify(x, builder.build());
        }else{
            Bitmap img = BitmapFactory.decodeFile(imagen);
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setSmallIcon(R.drawable.iconcap)
                    .setContentTitle(nombre.toUpperCase())
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentText(presentacion)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.iconcap)
                    .setCategory(NotificationCompat.CATEGORY_ALARM)
                    .setAutoCancel(true)
                    .setSound(soundUri)
                    .setColor(clr)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setLargeIcon(img)
                    .addAction(R.drawable.iconcap,"Ya lo he tomado",YaMedicamento)
                    .addAction(R.drawable.iconcap,"Ver medicamentos",VerMedicamentos)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(img)
                            .bigLargeIcon(null));
            vibrator.vibrate(1000);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
            notificationManagerCompat.notify(x, builder.build());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.boton):
                cargar(x);
                Anuma();
                Toast.makeText(this,""+x,Toast.LENGTH_LONG).show();
                x=x+1;
                break;
        }
    }
    public void Anuma(){
        setNoPendingIntent();
        setSiPendingIntent();
        setPendingIntent();
        createNotification();
        createNotificationChannel();
    }

    private void cargar(int w) {
        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        SharedPreferences preferencias1 = getSharedPreferences("nombremed", Context.MODE_PRIVATE);
        SharedPreferences preferencias2 = getSharedPreferences("tiempo", Context.MODE_PRIVATE);
        SharedPreferences preferencias3 = getSharedPreferences("tiempo2", Context.MODE_PRIVATE);
        SharedPreferences preferencias4 = getSharedPreferences("cantidad", Context.MODE_PRIVATE);
        SharedPreferences preferencias5 = getSharedPreferences("cantidadt", Context.MODE_PRIVATE);
        SharedPreferences preferencias6 = getSharedPreferences("fecha", Context.MODE_PRIVATE);
        SharedPreferences preferencias7 = getSharedPreferences("fechaf", Context.MODE_PRIVATE);
        SharedPreferences preferencias8 = getSharedPreferences("imagen", Context.MODE_PRIVATE);
        SharedPreferences preferencias9 = getSharedPreferences("tipoMedicamentoDB", Context.MODE_PRIVATE);
        SharedPreferences preferencias10 = getSharedPreferences("Medicamento", Context.MODE_PRIVATE);
        String id=String.valueOf(w+1);
        nombre=preferencias1.getString(id,"");
        cantidad_total_pastillas=preferencias4.getString(nombre,"");
        tipo=preferencias9.getString(nombre,"");
        tiempo=preferencias7.getString(nombre,"");
        pastillas=preferencias5.getString(nombre,"");
        int x=preferencias10.getInt(nombre,0);

        if(x<0){
            clr=Color.RED;
        }else{
            clr=Color.CYAN;
        }

        if(tipo.equals("Pastillas")){
            presentacion="Toma tus "+cantidad_total_pastillas+" "+tipo+" de "+nombre;
        }
        if(tipo.equals("Pomadas")){
            presentacion="Unta tu pomada "+nombre+" en el area afectada";
        }
        if(tipo.equals("Jarabes")){
            presentacion="Toma tus "+cantidad_total_pastillas+" ml de "+tipo+" "+nombre;
        }
        if(tipo.equals("Inyecciones")){
            presentacion="Es momento de inyectar "+cantidad_total_pastillas+" "+tipo+" de "+nombre;
        }
        if(tipo.equals("Gotas")){
            presentacion="Aplica "+cantidad_total_pastillas+" "+tipo+" de "+nombre+"en el area afectada";
        }
    }
}
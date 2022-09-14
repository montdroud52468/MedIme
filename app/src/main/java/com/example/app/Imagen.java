package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class Imagen extends AppCompatActivity {

    ImageView imag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_imagen);
        String ruta=getIntent().getStringExtra("datodir");
        this.setTitle("FOTO RECORDATORIO "+ruta.toUpperCase());
        imag=(ImageView)findViewById(R.id.resimage);
        SharedPreferences preferencias8 = getSharedPreferences("imagen", Context.MODE_PRIVATE);
        String ima=preferencias8.getString(ruta,"");
        Bitmap img = BitmapFactory.decodeFile(ima);
        imag.setImageBitmap(img);
    }
}
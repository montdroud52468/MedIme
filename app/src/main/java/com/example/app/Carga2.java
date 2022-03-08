package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Carga2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getApplicationContext(),"Eliminando Recordatorios",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Carga2.this, Presentacion27.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
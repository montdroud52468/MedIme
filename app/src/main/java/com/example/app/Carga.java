package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Carga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {//No es necesario mover nada en esta paguina solo es el el efecto de carga en alguna accion
                Intent intent = new Intent(Carga.this, Principal.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
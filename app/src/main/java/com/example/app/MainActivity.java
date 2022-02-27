package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //Agregamos animaciones
        Animation animacion1= AnimationUtils.loadAnimation(this, R.anim.des_arriba);
        Animation animacion2= AnimationUtils.loadAnimation(this, R.anim.des_abajo);

        //ImageView uno = findViewById(R.id.imageView1);
        GridLayout arriba=findViewById(R.id.arriba);
        GridLayout abajo=findViewById(R.id.abajo);
        //ImageView dos = findViewById(R.id.imageView2);
        //ImageView logo= findViewById(R.id.imageView3);

        arriba.setAnimation(animacion2);
        abajo.setAnimation(animacion1);
        //logo.setAnimation(animacion2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Principal.class);
                startActivity(intent);
                finish();
            }
        }, 6000);
    }

}
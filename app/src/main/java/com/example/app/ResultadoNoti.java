package com.example.app;

import android.os.Bundle;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoNoti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_resultado_noti);
    }
}
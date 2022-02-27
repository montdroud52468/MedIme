package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RECORDATORIOS extends AppCompatActivity implements View.OnClickListener {
    public CardView addRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios);

        addRec=(CardView)findViewById(R.id.agregarRecord);
        addRec.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agregarRecord:
                Toast.makeText(getApplicationContext(),"Agregar Recordatorio",Toast.LENGTH_SHORT).show();
                Intent agregar = new Intent(RECORDATORIOS.this,AddRecordatorios.class);
                startActivity(agregar);
                break;
        }
    }
}
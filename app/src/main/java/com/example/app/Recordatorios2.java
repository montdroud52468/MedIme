package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Recordatorios2 extends AppCompatActivity implements View.OnClickListener {
    public CardView addRec;
    private ListView lv1;
    public ArrayList <String> NombMedicamemto2 =new ArrayList<String>();
    public ArrayList <String> TiemMedicamento2 =new ArrayList<String>();
    public ArrayList <String> TomaMedicamento2 =new ArrayList<String>();
    public ArrayList <String> MediMedicamento2 =new ArrayList<String>();
    public ArrayList <String> TotaMedicamento2 =new ArrayList<String>();
    public ArrayList <String> TipoMedicamento2 =new ArrayList<String>();
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorios2);
        this.setTitle("RECORDATORIOS ");
        addRec = (CardView) findViewById(R.id.agregarRecord);
        addRec.setOnClickListener(this);

        lv1=(ListView) findViewById(R.id.Recordatiorioslist);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list_item_medicamentorecord,NombMedicamemto2);
        lv1.setAdapter(adapter);
        cargarDatos();

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Nombre: "+lv1.getItemAtPosition(i)+"\nTiempo: "+ TiemMedicamento2.get(i),Toast.LENGTH_SHORT).show();
                Intent resNot = new Intent(Recordatorios2.this, ResultadoNoti.class);
                resNot.putExtra("ResID", (String) lv1.getItemAtPosition(i));
                startActivity(resNot);
            }
        });

    }

    //Evento del boton Agregar Nuevo recordatorio
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agregarRecord:
                //Toast.makeText(getApplicationContext(), "Agregar Recordatorio", Toast.LENGTH_SHORT).show();
                Intent agregar = new Intent(Recordatorios2.this, AddRecordatorios.class);
                startActivity(agregar);
                break;
        }
    }

    private void cargarDatos() {
        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        String id = preferencias.getString("ID", "0");
        int v=Integer.parseInt(id);

        while (j<v){
            j++;
            String a=Integer.toString(j);

            String Nom = preferencias.getString(a, "No Existe la informacion");
            String Tie = preferencias.getString(Nom, "No Existe la informacion");
            String Tom = preferencias.getString(Tie, "No Existe la informacion");
            String Medi = preferencias.getString(Tom, "No Existe la informacion");
            String Can = preferencias.getString(Medi, "No Existe la informacion");

            NombMedicamemto2.add(Nom);
            TiemMedicamento2.add(Tie);
            TomaMedicamento2.add(Tom);
            MediMedicamento2.add(Medi);
            TotaMedicamento2.add(Can);
        }
    }
}
package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MedicamentoRestante extends AppCompatActivity implements View.OnClickListener{
    private ListView Listamedicamento;
    public ArrayList<String> NombMedicamemto2 =new ArrayList<String>();
    public ArrayList <String> TiemMedicamento2 =new ArrayList<String>();
    public ArrayList <String> TomaMedicamento2 =new ArrayList<String>();
    public ArrayList <String> MediMedicamento2 =new ArrayList<String>();
    public ArrayList <String> TotaMedicamento2 =new ArrayList<String>();
    public ArrayList <String> TipoMedicamento2 =new ArrayList<String>();
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento_restante);
        this.setTitle("MEDICAMENTO RESTANTE ");

        Listamedicamento =(ListView) findViewById(R.id.Recordatiorioslist);
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.int_ab_ar);
        Listamedicamento.setAnimation(animacion);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list_item_medicamentorecord,NombMedicamemto2);
        Listamedicamento.setAdapter(adapter);
        cargarDatos();

        Listamedicamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent resNot = new Intent(MedicamentoRestante.this, EditaRecord.class);
                resNot.putExtra("RestanteID", (String) Listamedicamento.getItemAtPosition(i));
                int val=1+i;
                String id2=Integer.toString(val);
                resNot.putExtra("IDRea",id2);
                startActivity(resNot);
            }
        });

    }

    //Evento del boton Agregar Nuevo recordatorio
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agregarRecord:
                Intent agregar = new Intent(MedicamentoRestante.this, AddRecordatorios.class);
                startActivity(agregar);
                finish();
                break;
        }
    }

    private void cargarDatos() {
        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        String id = preferencias.getString("ID", "0");
        int v=Integer.parseInt(id);

        SharedPreferences preferencias1 = getSharedPreferences("nombremed", Context.MODE_PRIVATE);
        SharedPreferences preferencias2 = getSharedPreferences("tiempo", Context.MODE_PRIVATE);
        SharedPreferences preferencias3 = getSharedPreferences("tiempo2", Context.MODE_PRIVATE);
        SharedPreferences preferencias4 = getSharedPreferences("cantidad", Context.MODE_PRIVATE);
        SharedPreferences preferencias5 = getSharedPreferences("cantidadt", Context.MODE_PRIVATE);


        while (j<v){
            j++;
            String a=Integer.toString(j);

            String Nom = preferencias1.getString(a, "");
            String Tie = preferencias2.getString(Nom, "");
            String Tom = preferencias3.getString(Nom, "");
            String Medi= preferencias4.getString(Nom, "");
            String Can = preferencias5.getString(Nom, "");

            if(Nom==""&&Tie==""&&Tom==""&&Medi==""&&Can==""){
                NombMedicamemto2.remove(Nom);
                TiemMedicamento2.remove(Tie);
                TomaMedicamento2.remove(Tom);
                MediMedicamento2.remove(Medi);
                TotaMedicamento2.remove(Can);
            }else{
                NombMedicamemto2.add(Nom);
                TiemMedicamento2.add(Tie);
                TomaMedicamento2.add(Tom);
                MediMedicamento2.add(Medi);
                TotaMedicamento2.add(Can);

            }
        }
    }
}
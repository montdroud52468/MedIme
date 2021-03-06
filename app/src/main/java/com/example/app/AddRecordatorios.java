package com.example.app;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class AddRecordatorios extends AppCompatActivity {


    EditText        nommT,tieMT,canMT,cantMT,cadMT;
    TextInputLayout nomM,tieM,canM,cantM,cadM;
    AutoCompleteTextView spi;
    Button Rec;
    int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("AGREGAR RECORDATORIOS");
        setContentView(R.layout.activity_add_recordatorios);

        nommT   = (EditText) findViewById(R.id.NomMed);
        tieMT   = (EditText) findViewById(R.id.TimMed);
        cadMT   = (EditText) findViewById(R.id.TomMed);
        canMT   = (EditText) findViewById(R.id.CanMed);
        cantMT   = (EditText) findViewById(R.id.CantMed);

        nomM   = (TextInputLayout) findViewById(R.id.NomMedT);
        tieM   = (TextInputLayout) findViewById(R.id.TimMedT);
        cadM   = (TextInputLayout) findViewById(R.id.TomMedT);
        canM   = (TextInputLayout) findViewById(R.id.CanMedT);
        cantM   = (TextInputLayout) findViewById(R.id.CantMedT);

        spi   = (AutoCompleteTextView) findViewById(R.id.TipoMed);
        Rec=(Button)findViewById(R.id.GuarMed);

        String[] tam={"Pastillas","Pomadas","Jarabes","Inyecciones","Multiples"};
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, tam);
        spi.setAdapter(adapter);


        ResEspiner();
        spi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Tipo();
            }
        });
    }

    private void ResEspiner() {

    }

    public void Tipo() {
        String tam=this.spi.getEditableText().toString();

        if(tam.equals("Pastillas")){
            //Toast.makeText(this,"Pastillas",Toast.LENGTH_LONG).show();
            nomM.setHint("Nombre del medicamento");
            tieM.setHint("Tiempo de medicamento");
            cadM.setHint("Cada cuando se toma el medicamento");
            canM.setHint("Cantidad que tomas de medicamento");
            cantM.setHint("Cantidad total del medicamento");

            nomM.setVisibility(View.VISIBLE);
            tieM.setVisibility(View.VISIBLE);
            cadM.setVisibility(View.VISIBLE);
            canM.setVisibility(View.VISIBLE);
            cantM.setVisibility(View.VISIBLE);

            spi.setVisibility(View.VISIBLE);
            Rec.setVisibility(View.VISIBLE);
        }
        if(tam.equals("Pomadas")){
            //Toast.makeText(this,"Pomadas",Toast.LENGTH_LONG).show();

            nomM.setHint("Nombre de la pomada");
            tieM.setHint("Tiempo del medicamento");
            cadM.setHint("Cada cuando se unta la pomada");
            canM.setHint("");
            cantM.setHint("");

            nomM.setVisibility(View.VISIBLE);
            tieM.setVisibility(View.VISIBLE);
            cadM.setVisibility(View.VISIBLE);
            canM.setVisibility(View.GONE);
            cantM.setVisibility(View.GONE);

            spi.setVisibility(View.VISIBLE);
            Rec.setVisibility(View.VISIBLE);
        }
        if(tam.equals("Jarabes")){
            //Toast.makeText(this,"Jarabes",Toast.LENGTH_LONG).show();
            nomM.setHint("Nombre del Jarabe ");
            tieM.setHint("Tiempo de Jarabe");
            cadM.setHint("Cada cuando se toma el Jarabe ");
            canM.setHint("Cantidad que tomas de Jarabe");
            cantM.setHint("Cantidad total del Jarabe");

            nomM.setVisibility(View.VISIBLE);
            tieM.setVisibility(View.VISIBLE);
            cadM.setVisibility(View.VISIBLE);
            canM.setVisibility(View.VISIBLE);
            cantM.setVisibility(View.VISIBLE);

            spi.setVisibility(View.VISIBLE);
            Rec.setVisibility(View.VISIBLE);
        }
        if(tam.equals("Inyecciones")){
            //Toast.makeText(this,"Inyecciones",Toast.LENGTH_LONG).show();
            nomM.setHint("Nombre del Inyecci??n");
            tieM.setHint("Tiempo de Inyecci??n");
            cadM.setHint("Cada cuando se Inyecta");
            canM.setHint("");
            cantM.setHint("Cantidad total de Inyecciones");

            nomM.setVisibility(View.VISIBLE);
            tieM.setVisibility(View.VISIBLE);
            cadM.setVisibility(View.VISIBLE);
            canM.setVisibility(View.GONE);
            cantM.setVisibility(View.VISIBLE);

            spi.setVisibility(View.VISIBLE);
            Rec.setVisibility(View.VISIBLE);
        }
    }

    public void onclickaddRec(View view){
        switch (view.getId()){
            case R.id.GuarMed:
                guardarDatos();
                Intent intent = new Intent(this, Presentacion27.class);
                startActivity(intent);
                break;
            case R.id.CargMed:
                cargarDatos();
                break;

        }
    }

    private void cargarDatos() {
        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        String id = preferencias.getString("ID", "0");
        int v=Integer.parseInt(id);

        //Toast.makeText(getApplicationContext(),"valor j: "+j,Toast.LENGTH_SHORT).show();
        while (j<v){
            j++;
            String a=Integer.toString(j);
            //Toast.makeText(getApplicationContext(),"indice: "+j,Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"valor v: "+v,Toast.LENGTH_SHORT).show();

            String apelli = preferencias.getString(a, "No Existe la informacion");
            String usuariot = preferencias.getString(apelli, "No Existe la informacion");
            String apellit = preferencias.getString(usuariot, "No Existe la informacion");
            String numbert = preferencias.getString(apellit, "No Existe la informacion");
            String apodo = preferencias.getString(numbert, "No Existe la informacion");
            //Toast.makeText(getApplicationContext(),a+" "+apelli+" "+usuariot+" "+apellit+" "+numbert+" "+ apodo,Toast.LENGTH_LONG).show();
            /*
            editor.putString("ID", ID);//Contador desde 1
            editor.putString(ID, nomMed);en 1 esta nombre
            editor.putString(nomMed, tieMed);en nombre esta el tiempo
            editor.putString(tieMed, canMed);entiempo esta can
            editor.putString(canMed, canmed);can esta cantidad med
            editor.putString(canmed, cantMed);cantidad med cantidad toma
             */

        }



    }

    private void guardarDatos() {
        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        String ID = preferencias.getString("ID", "0");
        int id=Integer.parseInt(ID);
        id=id+1;

        String nomMed=nommT.getText().toString();
        String tieMed=tieMT.getText().toString();
        String canMed=cadMT.getText().toString();
        String canmed=canMT.getText().toString();
        String cantMed=cantMT.getText().toString();
        
        ID=Integer.toString(id);
        SharedPreferences.Editor editor = preferencias.edit();

        editor.putString("ID", ID);
        editor.putString(ID, nomMed);
        editor.putString(nomMed, tieMed);
        editor.putString(tieMed, canMed);
        editor.putString(canMed, canmed);
        editor.putString(canmed, cantMed);

        editor.commit();

    }

}
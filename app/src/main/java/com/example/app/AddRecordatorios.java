package com.example.app;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecordatorios extends AppCompatActivity {


    EditText cad;
    AutoCompleteTextView spi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recordatorios);
        cad   = (EditText) findViewById(R.id.NomMed);
        spi   = (AutoCompleteTextView) findViewById(R.id.TipoMed);

        String[] tam={"Pastillas","Pomadas","Jarabes","Inyecciones"};
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
                tamanio();
            }
        });
    }

    private void ResEspiner() {
        String a=spi.getText().toString();
        Toast.makeText(getApplicationContext(),"Res:"+a,Toast.LENGTH_LONG).show();
    }

    public void tamanio() {
        String tam=this.spi.getEditableText().toString();

        if(tam.equals("Pastillas")){
            Toast.makeText(this,"Pastillas",Toast.LENGTH_LONG).show();
        }
        if(tam.equals("Pomadas")){
            Toast.makeText(this,"Pomadas",Toast.LENGTH_LONG).show();
        }
        if(tam.equals("Jarabes")){
            Toast.makeText(this,"Jarabes",Toast.LENGTH_LONG).show();
        }
        if(tam.equals("Inyecciones")){
            Toast.makeText(this,"Inyecciones",Toast.LENGTH_LONG).show();
        }
    }

    public void onclick(View view){
        switch (view.getId()){
            case R.id.GuarMed:
                break;

        }
    }
}
package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputLayout;

public class EditaRecord extends AppCompatActivity implements View.OnClickListener{
    String valor;
    TextView falsetxt,truetxt;
    CardView resultadoimagenc,btrue,bfalse;
    ImageView resimag,falseimag,trueimag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edita_record);
        valor =getIntent().getStringExtra("RestanteID");
        this.setTitle("MEDICAMENTO RESTANTE "+ valor.toUpperCase());
        falseimag=(ImageView) findViewById(R.id.falses);
        falsetxt=(TextView)findViewById(R.id.falsestext);
        trueimag=(ImageView)findViewById(R.id.trues);
        truetxt=(TextView)findViewById(R.id.truestext);
        resimag=(ImageView) findViewById(R.id.imagen);
        resultadoimagenc=(CardView) findViewById(R.id.resultadoimagen);
        bfalse=(CardView)findViewById(R.id.Botonfalse);
        btrue=(CardView)findViewById(R.id.botontrue);
        btrue.setOnClickListener(this);
        bfalse.setOnClickListener(this);
        resultadoimagenc.setOnClickListener(this);
        cargaAnnimaciones();
        cargarDatoRes();

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.resultadoimagen:
                Intent nu=new Intent(EditaRecord.this,Imagen.class);
                nu.putExtra("datodir", valor);
                startActivity(nu);
                break;
            case R.id.botontrue:
                nu=new Intent(this,Presentacion27.class);
                finish();
                startActivity(nu);
                break;
            case R.id.Botonfalse:
                nu=new Intent(this,ResultadoNoti.class);
                nu.putExtra("ResID", valor );
                startActivity(nu);
                break;
        }
    }

    private void cargarDatoRes(){
        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        SharedPreferences preferencias1 = getSharedPreferences("nombremed", Context.MODE_PRIVATE);
        SharedPreferences preferencias2 = getSharedPreferences("tiempo", Context.MODE_PRIVATE);
        SharedPreferences preferencias3 = getSharedPreferences("tiempo2", Context.MODE_PRIVATE);
        SharedPreferences preferencias4 = getSharedPreferences("cantidad", Context.MODE_PRIVATE);
        SharedPreferences preferencias5 = getSharedPreferences("cantidadt", Context.MODE_PRIVATE);
        SharedPreferences preferencias6 = getSharedPreferences("fecha", Context.MODE_PRIVATE);
        SharedPreferences preferencias7 = getSharedPreferences("fechaf", Context.MODE_PRIVATE);
        SharedPreferences preferencias8 = getSharedPreferences("imagen", Context.MODE_PRIVATE);
        SharedPreferences preferencias9 = getSharedPreferences("tipoMedicamentoDB", Context.MODE_PRIVATE);
        SharedPreferences preferencias10 = getSharedPreferences("Medicamento", Context.MODE_PRIVATE);

        String Tie = preferencias2.getString(valor, "No Existe la informacion");
        String Tom = preferencias3.getString(valor, "No Existe la informacion");
        String Medi = preferencias4.getString(valor, "No Existe la informacion");
        String Can = preferencias5.getString(valor, "No Existe la informacion");
        String fecha= preferencias7.getString(valor,"");
        String fechaf=preferencias6.getString(valor,"");
        String ima=preferencias8.getString(valor,"");
        String tipo=preferencias9.getString(valor,"");
        int res=preferencias10.getInt(valor,-100);

        if(ima==""){
            resultadoimagenc.setVisibility(View.GONE);
        }else{
            resultadoimagenc.setVisibility(View.VISIBLE);
            Bitmap img = BitmapFactory.decodeFile(ima);
            resimag.setImageBitmap(img);
        }
        Toast.makeText(this,tipo,Toast.LENGTH_LONG).show();
        if(tipo.equals("Pastillas")||tipo.equals("Jarabes")||tipo.equals("Inyecciones")||tipo.equals("Gotas")){
            int total=(((24*Integer.parseInt(Tie))/Integer.parseInt(Tom))*Integer.parseInt(Medi));
            if (res<0){
                verdadero(res,Can,tipo,total);
            }else{
                falso(res,Can,tipo,total);
            }
        }else{
            pomada();
        }
    }

    private void pomada(){
        falsetxt.setVisibility(View.GONE);
        falseimag.setVisibility(View.GONE);
        bfalse.setVisibility(View.GONE);
        truetxt.setVisibility(View.VISIBLE);
        trueimag.setVisibility(View.VISIBLE);
        btrue.setVisibility(View.VISIBLE);//
        truetxt.setText("LA POMADA NO PUESE SER CALCULADA CON EXACTITUD POR LO QUE SE SUGIERE QUE LA ADMINISTREN FUERA DE LA APLICACIÓN PERO PUEDE PONER COMO RECORDATORIO COMPRAR UNA NUEVA EN CUANTO SIENTA QUE SEA NECESARIO EN EL BOTON");
    }

    private void verdadero(int x, String can,String tipo,int total){
        x=x*-1;
        falsetxt.setVisibility(View.VISIBLE);
        falseimag.setVisibility(View.VISIBLE);
        bfalse.setVisibility(View.VISIBLE);
        truetxt.setVisibility(View.GONE);
        trueimag.setVisibility(View.GONE);
        btrue.setVisibility(View.GONE);//
        falsetxt.setText("EL MEDICAMENTO ESTA INCOMPLETO, ES NECESARIO QUE ACUDA CON SU MEDICO FAMILIAR PARA RECIBIR MAS MEDICAMENTO EN CASO DE SER NECESARIO\n\nACTUALMENTE CUENTA CON "+can+" "+tipo.toUpperCase()+"\n\nY USTED NECESITA "+total+" "+tipo.toUpperCase()+"\n\nLE FALTAN "+x+" "+tipo.toUpperCase()+" PARA PODER TERMINAR EL TRATAMIENTO");
    }

    private void falso(int x, String can,String tipo,int total){
        falsetxt.setVisibility(View.GONE);
        falseimag.setVisibility(View.GONE);
        bfalse.setVisibility(View.GONE);
        truetxt.setVisibility(View.VISIBLE);
        trueimag.setVisibility(View.VISIBLE);
        btrue.setVisibility(View.VISIBLE);
        truetxt.setText("EL MEDICAMENTO ESTA COMPLETO PARA TERMINAR CON SU TRATAMIENTO\n\n\n\nACTUALMENTE CUENTA CON "+can+" "+tipo.toUpperCase()+"\n\nUSTED NECESITA "+total+" "+tipo.toUpperCase()+"\n\nLE SOBRARAN "+x+" "+tipo.toUpperCase());
    }

    private void cargaAnnimaciones(){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.int_ab_ar);
        falsetxt.setAnimation(animacion);
        falseimag.setAnimation(animacion);
        truetxt.setAnimation(animacion);
        trueimag.setAnimation(animacion);
        btrue.setAnimation(animacion);
        bfalse.setAnimation(animacion);
    }

}
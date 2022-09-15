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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputLayout;

import java.security.PrivilegedExceptionAction;

public class ResultadoNoti extends AppCompatActivity implements View.OnClickListener{

    EditText res1n,res2n,res3n,res4n,res5n;
    CardView card1,card2,card3,resimag;
    String dato,id;
    TextView fe;
    ImageView imagen;
    TextInputLayout r1,r2,r3,r4,r5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_resultado_noti);
        dato=getIntent().getStringExtra("ResID");
        this.setTitle("RECORDATORIO "+dato.toUpperCase());
        res1n=(EditText)findViewById(R.id.NomMedRes);
        res2n=(EditText)findViewById(R.id.TieMediRes);
        res3n=(EditText)findViewById(R.id.CanMediRes);
        res4n=(EditText)findViewById(R.id.CatMediRes);
        res5n=(EditText)findViewById(R.id.TotMediRes);
        card1=(CardView)findViewById(R.id.EditarResNot);
        card2=(CardView)findViewById(R.id.EliminarResNot);
        card3=(CardView)findViewById(R.id.GuardarResNot);
        fe=(TextView)findViewById(R.id.textView);
        imagen=(ImageView)findViewById(R.id.ima);
        resimag=(CardView)findViewById(R.id.cardimag);
        r1=(TextInputLayout)findViewById(R.id.R1);
        r2=(TextInputLayout)findViewById(R.id.R2);
        r3=(TextInputLayout)findViewById(R.id.R3);
        r4=(TextInputLayout)findViewById(R.id.R4);
        r5=(TextInputLayout)findViewById(R.id.R5);
        cargaAnnimaciones();
        resimag.setOnClickListener(this);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);


        cargarDatos();
    }

    private void cargarDatos() {
        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        dato=getIntent().getStringExtra("ResID");
        SharedPreferences preferenciast = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        SharedPreferences preferencias1 = getSharedPreferences("nombremed", Context.MODE_PRIVATE);
        SharedPreferences preferencias2 = getSharedPreferences("tiempo", Context.MODE_PRIVATE);
        SharedPreferences preferencias3 = getSharedPreferences("tiempo2", Context.MODE_PRIVATE);
        SharedPreferences preferencias4 = getSharedPreferences("cantidad", Context.MODE_PRIVATE);
        SharedPreferences preferencias5 = getSharedPreferences("cantidadt", Context.MODE_PRIVATE);
        SharedPreferences preferencias6 = getSharedPreferences("fecha", Context.MODE_PRIVATE);
        SharedPreferences preferencias7 = getSharedPreferences("fechaf", Context.MODE_PRIVATE);
        SharedPreferences preferencias8 = getSharedPreferences("imagen", Context.MODE_PRIVATE);
        SharedPreferences preferencias9 = getSharedPreferences("tipoMedicamentoDB", Context.MODE_PRIVATE);

        String Tie = preferencias2.getString(dato, "No Existe la informacion");
        String Tom = preferencias3.getString(dato, "No Existe la informacion");
        String Medi = preferencias4.getString(dato, "No Existe la informacion");
        String Can = preferencias5.getString(dato, "No Existe la informacion");
        String fecha= preferencias7.getString(dato,"");
        String fechaf=preferencias6.getString(dato,"");
        String ima=preferencias8.getString(dato,"");
        String tipo=preferencias9.getString(dato,"");

        Toast.makeText(this,"El tipo de medicamento es: "+tipo,Toast.LENGTH_LONG).show();

        float tam = preferenciast.getFloat("Tamanio",15);
        if(ima==""){
            resimag.setVisibility(View.GONE);
        }else{
            resimag.setVisibility(View.VISIBLE);
            Bitmap img = BitmapFactory.decodeFile(ima);
            imagen.setImageBitmap(img);
        }

        res1n.setTextSize(tam);
        res2n.setTextSize(tam);
        res3n.setTextSize(tam);
        res4n.setTextSize(tam);
        res5n.setTextSize(tam);
        //fe.setTextSize(tam);

        res1n.setText(dato);
        res2n.setText(Tie);
        res3n.setText(Tom);
        res4n.setText(Medi);
        res5n.setText(Can);
        fe.setText("La ultima docis fue el "+fechaf+"\n"+"La proxima dosis es el dia "+fecha);


        res1n.setEnabled(false);
        res2n.setEnabled(false);
        res3n.setEnabled(false);
        res4n.setEnabled(false);
        res5n.setEnabled(false);


        r1.setVisibility(View.GONE);
        r2.setVisibility(View.GONE);
        r3.setVisibility(View.GONE);
        r4.setVisibility(View.GONE);
        r5.setVisibility(View.GONE);

        if(dato==""){
            r1.setVisibility(View.GONE);
        }else{
            r1.setVisibility(View.VISIBLE);
        }
        if(Tie==""){
            r2.setVisibility(View.GONE);
        }else{
            r2.setVisibility(View.VISIBLE);
        }
        if(Tom==""){
            r3.setVisibility(View.GONE);
        }else{
            r3.setVisibility(View.VISIBLE);
        }
        if(Medi==""){
            r4.setVisibility(View.GONE);
        }else{
            r4.setVisibility(View.VISIBLE);
        }
        if(Can==""){
            r5.setVisibility(View.GONE);
        }else{
            r5.setVisibility(View.VISIBLE);
        }

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardimag:
                Intent nu=new Intent(ResultadoNoti.this,Imagen.class);
                nu.putExtra("datodir",dato);
                startActivity(nu);
                break;
            case R.id.EditarResNot:
                Editar();
                break;
            case R.id.EliminarResNot:
                Elimina();
                break;
            case R.id.GuardarResNot:
                Guardar();
                break;
        }
    }

    private void Guardar() {
        card3.setVisibility(View.GONE);
        card1.setVisibility(View.VISIBLE);

        res2n.setEnabled(false);
        res3n.setEnabled(false);
        res4n.setEnabled(false);
        res5n.setEnabled(false);

        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);
        SharedPreferences preferencias1 = getSharedPreferences("nombremed", Context.MODE_PRIVATE);
        SharedPreferences preferencias2 = getSharedPreferences("tiempo", Context.MODE_PRIVATE);
        SharedPreferences preferencias3 = getSharedPreferences("tiempo2", Context.MODE_PRIVATE);
        SharedPreferences preferencias4 = getSharedPreferences("cantidad", Context.MODE_PRIVATE);
        SharedPreferences preferencias5 = getSharedPreferences("cantidadt", Context.MODE_PRIVATE);
        SharedPreferences preferencias9 = getSharedPreferences("tipoMedicamentoDB", Context.MODE_PRIVATE);
        SharedPreferences preferencias10 = getSharedPreferences("Medicamento", Context.MODE_PRIVATE);

        String uno = res1n.getText().toString();
        String dos = res2n.getText().toString();
        String tres = res3n.getText().toString();
        String cuatro = res4n.getText().toString();
        String cinco = res5n.getText().toString();
        String tipo=preferencias9.getString(dato,"");

        SharedPreferences.Editor editor = preferencias.edit();
        SharedPreferences.Editor editor1 = preferencias1.edit();
        SharedPreferences.Editor editor2 = preferencias2.edit();
        SharedPreferences.Editor editor3 = preferencias3.edit();
        SharedPreferences.Editor editor4 = preferencias4.edit();
        SharedPreferences.Editor editor5 = preferencias5.edit();

        if (tipo!="Pomadas"){
            int pastilla=(((24*Integer.parseInt(dos))/Integer.parseInt(tres))*Integer.parseInt(cuatro));
            int medicamentores=Integer.parseInt(cinco)-pastilla;

            SharedPreferences.Editor editor10 = preferencias10.edit();
            editor10.putInt(dato, medicamentores);
            editor10.commit();
            if(medicamentores<=0){
                Toast.makeText(this,"SE RECOMIENDA CONSEGUIR MAS MEDICAMENTO PARA: "+uno.toUpperCase(),Toast.LENGTH_LONG).show();
            }
        }

        editor2.remove(dos);
        editor3.remove(tres);
        editor4.remove(cuatro);
        editor5.remove(cinco);

        editor2.putString(uno, dos);
        editor3.putString(uno, tres);
        editor4.putString(uno, cuatro);
        editor5.putString(uno, cinco);

        editor.commit();
        editor1.commit();
        editor2.commit();
        editor3.commit();
        editor4.commit();
        editor5.commit();
        finish(); //finish Activity.
    }

    private void Elimina() {

        SharedPreferences preferencias = getSharedPreferences("MedicamentoBDD", Context.MODE_PRIVATE);

        String uno= res1n.getText().toString();
        String dos = res2n.getText().toString();
        String tres = res3n.getText().toString();
        String cuatro = res4n.getText().toString();
        String cinco = res5n.getText().toString();

        SharedPreferences.Editor editor = preferencias.edit();
        id=getIntent().getStringExtra("IDRea");
        Toast.makeText(this, "dato: " +id, Toast.LENGTH_SHORT).show();
        editor.remove(id);
        editor.remove(uno);
        editor.remove(dos);
        editor.remove(tres);
        editor.remove(cuatro);
        editor.remove(cinco);

        editor.commit();
        Intent regreso=new Intent(this, Presentacion27.class);
        startActivity(regreso);
        finish();

    }

    private void Editar() {
        card3.setVisibility(View.VISIBLE);
        card1.setVisibility(View.GONE);

        res2n.setEnabled(true);
        res3n.setEnabled(true);
        res4n.setEnabled(true);
        res5n.setEnabled(true);
    }

    private void cargaAnnimaciones(){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.int_ab_ar);
        res1n.setAnimation(animacion);
        res2n.setAnimation(animacion);
        res3n.setAnimation(animacion);
        res4n.setAnimation(animacion);
        res5n.setAnimation(animacion);
        card1.setAnimation(animacion);
        card2.setAnimation(animacion);
        card3.setAnimation(animacion);
        fe.setAnimation(animacion);
        imagen.setAnimation(animacion);
        resimag.setAnimation(animacion);
        r1.setAnimation(animacion);
        r2.setAnimation(animacion);
        r3.setAnimation(animacion);
        r4.setAnimation(animacion);
        r5.setAnimation(animacion);
    }
}
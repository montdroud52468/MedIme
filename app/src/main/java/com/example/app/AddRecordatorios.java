package com.example.app;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.google.android.material.textfield.TextInputLayout;

public class AddRecordatorios extends AppCompatActivity {

    CardView cam,Rec;
    EditText nommT,tieMT,canMT,cantMT,cadMT;
    TextInputLayout nomM,tieM,canM,cantM,cadM;
    AutoCompleteTextView spi;
    TextView texto;
    GridLayout cama,guar;
    ImageView imageView,imageView2;
    String rutaImagen,tipoMedicamento;
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

        texto=(TextView)findViewById(R.id.Textofoto);

        imageView2=(ImageView)findViewById(R.id.cam2);

        spi   = (AutoCompleteTextView) findViewById(R.id.TipoMed);

        Rec=(CardView) findViewById(R.id.GuarMed);
        cam = (CardView)findViewById(R.id.camara);

        cama=(GridLayout)findViewById(R.id.gridcam);
        guar=(GridLayout)findViewById(R.id.gridgua);
        cargaAnnimaciones();

        cam.setOnClickListener(this::onclickaddRec);
        Rec.setOnClickListener(this::onclickaddRec);

        String[] tam={"Pastillas","Pomadas","Jarabes","Inyecciones","Gotas"};
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, tam);
        spi.setAdapter(adapter);


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

    public void Tipo() {
        String tam=this.spi.getEditableText().toString();

        if(tam.equals("Pastillas")){
            tipoMedicamento="Pastillas";
            //Toast.makeText(this,"Pastillas",Toast.LENGTH_LONG).show();
            nomM.setHint("Nombre de la pastilla");
            tieM.setHint("Tiempo de medicacion");
            cadM.setHint("Cada cuando se toma la pastilla");
            canM.setHint("Cantidad de pastillas a tomar");
            cantM.setHint("Cantidad total del pastillas");
            texto.setText("TOCA AQUI PARA TOMAR FOTO DE LAS PASTILLAS");

            nomM.setVisibility(View.VISIBLE);
            tieM.setVisibility(View.VISIBLE);
            cadM.setVisibility(View.VISIBLE);
            canM.setVisibility(View.VISIBLE);
            cantM.setVisibility(View.VISIBLE);
            cama.setVisibility(View.VISIBLE);
            guar.setVisibility(View.VISIBLE);
            spi.setVisibility(View.VISIBLE);
            Rec.setVisibility(View.VISIBLE);
            cargaAnnimaciones();
        }
        if(tam.equals("Pomadas")){
            //Toast.makeText(this,"Pomadas",Toast.LENGTH_LONG).show();
            tipoMedicamento="Pomadas";

            nomM.setHint("Nombre de la pomada");
            tieM.setHint("Tiempo que usaras la pomada");
            cadM.setHint("Cada cuando se unta la pomada");
            canM.setHint("");
            cantM.setHint("");
            texto.setText("TOCA AQUI PARA TOMAR FOTO DE LA POMADA");

            nomM.setVisibility(View.VISIBLE);
            tieM.setVisibility(View.VISIBLE);
            cadM.setVisibility(View.VISIBLE);
            canM.setVisibility(View.GONE);
            cantM.setVisibility(View.GONE);
            cama.setVisibility(View.VISIBLE);
            guar.setVisibility(View.VISIBLE);
            spi.setVisibility(View.VISIBLE);
            Rec.setVisibility(View.VISIBLE);
            cargaAnnimaciones();
        }
        if(tam.equals("Jarabes")){
            tipoMedicamento="Jarabes";
            //Toast.makeText(this,"Jarabes",Toast.LENGTH_LONG).show();
            nomM.setHint("Nombre del Jarabe ");
            tieM.setHint("Tiempo que estaras tomando el Jarabe");
            cadM.setHint("Cada cuanto se toma el Jarabe ");
            canM.setHint("Cantidad que tomas de Jarabe (ml)");
            cantM.setHint("Cantidad total del Jarabe (ml)");
            texto.setText("TOCA AQUI PARA TOMAR FOTO DEL JARABE");

            nomM.setVisibility(View.VISIBLE);
            tieM.setVisibility(View.VISIBLE);
            cadM.setVisibility(View.VISIBLE);
            canM.setVisibility(View.VISIBLE);
            cantM.setVisibility(View.VISIBLE);
            cama.setVisibility(View.VISIBLE);
            guar.setVisibility(View.VISIBLE);
            spi.setVisibility(View.VISIBLE);
            Rec.setVisibility(View.VISIBLE);
            cargaAnnimaciones();
        }
        if(tam.equals("Inyecciones")){
            tipoMedicamento="Inyecciones";
            //Toast.makeText(this,"Inyecciones",Toast.LENGTH_LONG).show();
            nomM.setHint("Nombre del Inyección");
            tieM.setHint("Tiempo de Inyección");
            cadM.setHint("Cada cuando se Inyecta");
            canM.setHint("Cuantas inyecciones aplica");
            cantM.setHint("Cantidad total de Inyecciones");
            texto.setText("TOCA AQUI PARA TOMAR FOTO DE LA INYECCION");

            nomM.setVisibility(View.VISIBLE);
            tieM.setVisibility(View.VISIBLE);
            cadM.setVisibility(View.VISIBLE);
            canM.setVisibility(View.VISIBLE);
            cantM.setVisibility(View.VISIBLE);
            cama.setVisibility(View.VISIBLE);
            guar.setVisibility(View.VISIBLE);
            spi.setVisibility(View.VISIBLE);
            Rec.setVisibility(View.VISIBLE);
            cargaAnnimaciones();
        }if(tam.equals("Gotas")){
            tipoMedicamento="Gotas";
            //Toast.makeText(this,"Inyecciones",Toast.LENGTH_LONG).show();
            nomM.setHint("Nombre del Medicamento de gota");
            tieM.setHint("Tiempo de que usaras la gotas");
            cadM.setHint("Cada cuando se las aplica");
            canM.setHint("Cantidad de gotas a aplicar");
            cantM.setHint("Cantidad total (ml)");
            texto.setText("TOCA AQUI PARA TOMAR FOTO DE LAS GOTAS");

            nomM.setVisibility(View.VISIBLE);
            tieM.setVisibility(View.VISIBLE);
            cadM.setVisibility(View.VISIBLE);
            canM.setVisibility(View.VISIBLE);
            cantM.setVisibility(View.VISIBLE);
            cama.setVisibility(View.VISIBLE);
            guar.setVisibility(View.VISIBLE);
            spi.setVisibility(View.VISIBLE);
            Rec.setVisibility(View.VISIBLE);
            cargaAnnimaciones();
        }
    }

    public void onclickaddRec(View view){
        switch (view.getId()){
            case R.id.GuarMed:
                guardarDatos1();
                break;
            case R.id.camara:
                Toast.makeText(this,"camara",Toast.LENGTH_LONG).show();
                camara();
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
            String apelli = preferencias.getString(a, "No Existe la informacion");
            String usuariot = preferencias.getString(apelli, "No Existe la informacion");
            String apellit = preferencias.getString(usuariot, "No Existe la informacion");
            String numbert = preferencias.getString(apellit, "No Existe la informacion");
            String apodo = preferencias.getString(numbert, "No Existe la informacion");

        }
    }

    private void camara(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            File imagenArchivo=null;
            try {
                imagenArchivo=crearImagen();
            }catch (IOException ex){
                Log.e("Error",ex.toString());
            }

            if(imagenArchivo!=null){
                Uri fotoUri= FileProvider.getUriForFile(this,"com.example.app.fileprovider",imagenArchivo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                startActivityForResult(intent,1);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Bundle extras = data.getExtras();
            Bitmap img = BitmapFactory.decodeFile(rutaImagen);//(Bitmap) extras.get("data");
            texto.setText("TOCA PARA CAMBIAR LA FOTO");
            imageView2.setImageBitmap(img);
            imageView2.setVisibility(View.VISIBLE);
            texto.setVisibility(View.GONE);
        }
    }

    private File crearImagen() throws IOException {
        String nombreIm="foto_";
        File directorio=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen=File.createTempFile(nombreIm,".jpg",directorio);
        rutaImagen=imagen.getAbsolutePath();
        return imagen;
    }

    private void guardarDatos1() {
        if (rutaImagen==null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("No has agregado una imagen, Se recomienda que la imagen este presente para que a la hora de tomarlo se identifique con mas facilidad.\n¿Deseas continuar?").setTitle("RECOMENDACIÓN");

            builder.setPositiveButton("Si, Deseo continuar sin imagen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    guardarDatos();
                }
            });
            builder.setNegativeButton("No, Agregar imagen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            //Toast.makeText(getApplicationContext(), "Eliminacion", Toast.LENGTH_SHORT).show();
        }else{
            guardarDatos();
        }
    }


    private void guardarDatos() {

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
        editor.commit();

        SharedPreferences.Editor editor1 = preferencias1.edit();
        editor1.putString(ID, nomMed);
        editor1.commit();

        SharedPreferences.Editor editor2 = preferencias2.edit();
        editor2.putString(nomMed, tieMed);
        editor2.commit();

        SharedPreferences.Editor editor3 = preferencias3.edit();
        editor3.putString(nomMed, canMed);
        editor3.commit();

        SharedPreferences.Editor editor4 = preferencias4.edit();
        editor4.putString(nomMed, canmed);
        editor4.commit();

        SharedPreferences.Editor editor5 = preferencias5.edit();
        editor5.putString(nomMed, cantMed);
        editor5.commit();

        int i=Integer.parseInt(canMed);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE, dd MMMM yyyy hh:mm aaa", Locale.getDefault());
        Calendar ahora = Calendar.getInstance();
        Date fechaActual = ahora.getTime();
        String fechai=formatoFecha.format(fechaActual);
        ahora.add(Calendar.HOUR, i);
        Date fechamod = ahora.getTime();
        String fechaf=formatoFecha.format(fechamod);

        SimpleDateFormat dia = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat mes = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat anio = new SimpleDateFormat("yyyy", Locale.getDefault());
        SimpleDateFormat hora = new SimpleDateFormat("hh", Locale.getDefault());
        SimpleDateFormat minuto = new SimpleDateFormat("mm", Locale.getDefault());

        String diaa=dia.format(fechamod);
        String mesm=mes.format(fechamod);
        String anioa=anio.format(fechamod);
        String horah=hora.format(fechamod);
        String minutom=minuto.format(fechamod);



        SharedPreferences.Editor editor6 = preferencias6.edit();
        editor6.putString(nomMed, fechai);
        editor6.commit();

        SharedPreferences.Editor editor7 = preferencias7.edit();
        editor7.putString(nomMed, fechaf);
        editor7.commit();

        SharedPreferences.Editor editor8 = preferencias8.edit();
        editor8.putString(nomMed, rutaImagen);
        editor8.commit();

        SharedPreferences.Editor editor9 = preferencias9.edit();
        editor9.putString(nomMed, tipoMedicamento);
        editor9.commit();

/*
*       String nomMed=nommT.getText().toString();
        String tieMed=tieMT.getText().toString();
        String canMed=cadMT.getText().toString();
        String canmed=canMT.getText().toString();
        String cantMed=cantMT.getText().toString();
* */    if (tipoMedicamento!="Pomadas"){
            int pastilla=(((24*Integer.parseInt(tieMed))/Integer.parseInt(canMed))*Integer.parseInt(canmed));
            int medicamentores=Integer.parseInt(cantMed)-pastilla;

            SharedPreferences.Editor editor10 = preferencias10.edit();
            editor10.putInt(nomMed, medicamentores);
            editor10.commit();
            if(medicamentores<=0){
                Toast.makeText(this,"SE RECOMIENDA CONSEGUIR MAS MEDICAMENTO PARA: "+nomMed.toUpperCase(),Toast.LENGTH_LONG).show();
            }
        }


        Intent intent = new Intent(this, Presentacion27.class);
        startActivity(intent);
        finish();
    }
    private void cargaAnnimaciones(){
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.int_ab_ar);
        nommT.setAnimation(animacion);
        tieMT.setAnimation(animacion);
        cadMT.setAnimation(animacion);
        canMT.setAnimation(animacion);
        cantMT.setAnimation(animacion);
        nomM.setAnimation(animacion);
        tieM.setAnimation(animacion);
        cadM.setAnimation(animacion);
        canM.setAnimation(animacion);
        cantM.setAnimation(animacion);
        texto.setAnimation(animacion);
        imageView2.setAnimation(animacion);
        spi.setAnimation(animacion);
        Rec.setAnimation(animacion);
        cam.setAnimation(animacion);
        cama.setAnimation(animacion);
        guar.setAnimation(animacion);
    }

}
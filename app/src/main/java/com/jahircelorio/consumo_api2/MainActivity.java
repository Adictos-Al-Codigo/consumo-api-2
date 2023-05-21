package com.jahircelorio.consumo_api2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.jahircelorio.consumo_api2.interfaces.ComentarioApi;
import com.jahircelorio.consumo_api2.models.Comentario;


import java.io.Console;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText txtid, txtid_comentario, txtname, txtemail, txtbody;

    TableLayout tableLayout;
    Button mostrar, ocultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtid = findViewById(R.id.id);
        txtid_comentario = findViewById(R.id.id_comentario);
        txtname = findViewById(R.id.name);
        txtemail = findViewById(R.id.email);
        txtbody = findViewById(R.id.body);
        mostrar = findViewById(R.id.mostrar);
        ocultar = findViewById(R.id.ocultar);
        tableLayout = findViewById(R.id.table_layout);
        this.OcultarBoton();


        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar(txtid_comentario.getText().toString());
                tableLayout.setVisibility(View.VISIBLE);
            }
        });


        ocultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OcultarBoton();
            }
        });



    }

    private void buscar(String codigo){
        Retrofit retrofit  = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ComentarioApi ComentarioApi = retrofit.create(ComentarioApi.class);
        Call<Comentario> call = ComentarioApi.find(codigo);
        call.enqueue(new Callback<Comentario>() {
            @Override
            public void onResponse(Call<Comentario> call, Response<Comentario> response) {
                try {
                    if (response.isSuccessful()){
                        Comentario comentario = response.body();
                        txtid.setText(comentario.getId());
                        txtname.setText(comentario.getName());
                        txtemail.setText(comentario.getEmail());
                        txtbody.setText(comentario.getBody());
                    }
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Comentario> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error de Red",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OcultarBoton(){
        tableLayout.setVisibility(View.INVISIBLE);
    }
}
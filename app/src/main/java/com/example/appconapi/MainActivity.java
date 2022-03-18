package com.example.appconapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView nombrem1;
    ListView nombrem2;
    ListView nombrem3;
    Button btnsave;
    List<Integer> data;
    List<Integer> data2;
    List<Integer> data3;
    Maquina1 maquina1;
    Maquina1 maquina2;
    Maquina1 maquina3;
    MaquinaPeticion maquinaPeticion;
    int numero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaracion de los campos
        data = new ArrayList<>();
        data2 = new ArrayList<>();
        data3 = new ArrayList<>();
        nombrem1 = (ListView)findViewById(R.id.nombrem1);
        nombrem2 = (ListView)findViewById(R.id.nombrem2);
        nombrem3 = (ListView)findViewById(R.id.nombrem3);
        btnsave = findViewById(R.id.btnAct);

        //Declaracion de la maquina con las peticiones
        maquinaPeticion = new MaquinaPeticion();

        //Insert de los datos
        maquina1 = new Maquina1();
        maquina2 = new Maquina1();
        maquina3 = new Maquina1();
        maquina1.nombre = "Máquina Uno";
        maquina2.nombre = "Máquina Dos";
        maquina3.nombre = "Máquina Tres";

        //Ejecutamos el timer
        ejecutar();

        ///Obtencion de informacion
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitarInformacion();
            }
        });
    }
    private void ejecutar(){
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                metodoEjecutar();//llamamos nuestro metodo
                handler.postDelayed(this,40000);
            }
        },5000);
    }

    public void metodoEjecutar() {
        //Agregamos el tempoorizador y enviamos los datos
        numero = (int) (Math.random() * 100) + 1;
        maquina1.temperatura = numero;
        this.maquinaPeticion.saveM1(maquina1);
        numero = (int) (Math.random() * 100) + 1;
        maquina2.temperatura = numero;
        this.maquinaPeticion.saveM2(maquina2);
        numero = (int) (Math.random() * 100) + 1;
        maquina3.temperatura = numero;
        this.maquinaPeticion.saveM3(maquina3);
    }

    public void solicitarInformacion(){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data2);
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, data3);
        data.clear();
        new DB().getConnection().collection("Maquina1").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Maquina1 maquina1 = document.toObject(Maquina1.class);
                                data.add(maquina1.temperatura);
                            }
                            nombrem1.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.d("Error en el 1", "Error getting documents: ", task.getException());
                        }
                    }
                });
        data2.clear();
        new DB().getConnection().collection("Maquina2").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document2 : task.getResult()) {
                                Maquina2 maquina2 = document2.toObject(Maquina2.class);
                                data2.add(maquina2.temperatura);
                            }
                            nombrem2.setAdapter(adapter2);
                            adapter2.notifyDataSetChanged();
                        } else {
                            Log.d("Error en el 2", "Error getting documents: ", task.getException());
                        }
                    }
                });
        data3.clear();
        new DB().getConnection().collection("Maquina3").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document3 : task.getResult()) {
                                Maquina3 maquina3 = document3.toObject(Maquina3.class);
                                data3.add(maquina3.temperatura);
                            }
                            nombrem3.setAdapter(adapter3);
                            adapter3.notifyDataSetChanged();
                        } else {
                            Log.d("Error en el 3", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void IrGrafico(View view){
        Intent i = new Intent(this,Graficos.class);
        startActivity(i);
    }
}
package com.example.appconapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Graficos extends AppCompatActivity {

    private BarChart chart;
    public ArrayList<Float> valueList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public float env = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        valueList = new ArrayList<Float>();
        getInfo();
    }

    private void getInfo(){
        db.collection("Maquina1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            float c = 0;
                            env = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object datoa = document.get("temperatura");
                                float dato = ((Long) datoa).floatValue();
                                env = env + dato;
                                c = c + 1;

                            }
                            float prom = env / c;
                            createList(prom);
                        } else {
                            Log.w("Buenas", "Error getting documents.", task.getException());
                        }
                    }
                });
        db.collection("Maquina2")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            float c = 0;
                            env = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object datoa = document.get("temperatura");
                                int dato = ((Long) datoa).intValue();
                                env = env + dato;
                                c = c + 1;

                            }
                            float prom = env / c;
                            createList(prom);
                        } else {
                            Log.w("Buenas", "Error getting documents.", task.getException());
                        }
                    }
                });
        db.collection("Maquina3")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            float c = 0;
                            env = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Object datoa = document.get("temperatura");
                                int dato = ((Long) datoa).intValue();
                                env = env + dato;
                                c = c + 1;
                            }
                            float prom = env / c;
                            createList(prom);
                        } else {
                            Log.w("Buenas", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private  void createList(float value){
        valueList.add(value);
        showBarChart();
    }

    private void showBarChart() {

        setTitle("Promedio de temperaturas");
        chart = findViewById(R.id.barChart_view);
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "Máquinas";

        //fit the data into a bar
        for (int i = 0; i < valueList.size(); i++) {
            BarEntry barEntry = new BarEntry(i, valueList.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries,title);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData data = new BarData(barDataSet);
        chart.setData(data);
        chart.invalidate();
    }
}
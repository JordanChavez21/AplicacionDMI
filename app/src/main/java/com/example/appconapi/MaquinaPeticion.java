package com.example.appconapi;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

public class MaquinaPeticion {

    public void saveM1(Maquina1 cliente){
        // Create a new user with a first and last name
        Map<String, Object> clienteMap = new HashMap<>();
        clienteMap.put("nombre", cliente.nombre);
        clienteMap.put("temperatura", cliente.temperatura);

        new DB().getConnection().collection("Maquina1")

                // Add a new document with a generated ID
                .add(clienteMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void saveM2(Maquina1 cliente){
        // Create a new user with a first and last name
        Map<String, Object> clienteMap = new HashMap<>();
        clienteMap.put("nombre", cliente.nombre);
        clienteMap.put("temperatura", cliente.temperatura);

        new DB().getConnection().collection("Maquina2")

                // Add a new document with a generated ID
                .add(clienteMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    public void saveM3(Maquina1 cliente){
        Map<String, Object> clienteMap = new HashMap<>();
        clienteMap.put("nombre", cliente.nombre);
        clienteMap.put("temperatura", cliente.temperatura);

        new DB().getConnection().collection("Maquina3")

                .add(clienteMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

}

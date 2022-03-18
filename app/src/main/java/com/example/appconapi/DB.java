package com.example.appconapi;

import com.google.firebase.firestore.FirebaseFirestore;

public class DB {

    public FirebaseFirestore getConnection() {
        return FirebaseFirestore.getInstance();
    }

}

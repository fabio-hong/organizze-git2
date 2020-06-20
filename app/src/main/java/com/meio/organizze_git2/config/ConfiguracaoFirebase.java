package com.meio.organizze_git2.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Essa classe irá

public class ConfiguracaoFirebase {

    //Fazer com que o atributo autenticacao seja sempre o mesmo independente de quantas instancias
    //eu criar dessa classe
    //é um objeto estatico
    private static FirebaseAuth autenticacao;
    private static DatabaseReference firebase;

    //Método que retorne a instancia do firebaseAuth
    //é um metodo estático
    public static FirebaseAuth getFirebaseAutenticacao() {

        //Se autenticacao não tiver instancia, pegue a instancia
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }


    //Método que retorne a instancia (referencia?) do firebaseDatabase
    public static DatabaseReference getFirebaseDatabase() {

        //Se firebase não tiver instancia, pegue a instancia e a referencia
        if (firebase == null) {
            firebase = FirebaseDatabase.getInstance().getReference();
        }

        return firebase;
    }

}
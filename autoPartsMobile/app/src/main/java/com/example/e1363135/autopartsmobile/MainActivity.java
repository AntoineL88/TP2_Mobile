package com.example.e1363135.autopartsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.e1363135.autopartsmobile.Fournisseurs.FournisseursAffichage;
import com.example.e1363135.autopartsmobile.Produits.ProduitsAffichage;

import org.json.JSONObject;

public class MainActivity extends OptionMenu {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToProduits(View view) {
        Intent produits = new Intent(MainActivity.this, ProduitsAffichage.class);
        startActivity(produits);
    }

    public void goToFournisseurs(View view) {
        Intent fournisseurs = new Intent(MainActivity.this, FournisseursAffichage.class);
        startActivity(fournisseurs);
    }


}


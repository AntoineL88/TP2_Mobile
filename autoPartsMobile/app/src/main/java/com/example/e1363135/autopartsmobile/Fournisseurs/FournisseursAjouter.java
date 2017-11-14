package com.example.e1363135.autopartsmobile.Fournisseurs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.e1363135.autopartsmobile.MySingleton;
import com.example.e1363135.autopartsmobile.OptionMenu;
import com.example.e1363135.autopartsmobile.Produits.ProduitsAffichage;
import com.example.e1363135.autopartsmobile.R;

import java.util.HashMap;
import java.util.Map;

public class FournisseursAjouter extends OptionMenu {

    EditText editName,editAddress_Street,editAddress_City,editAddress_Province,editAddress_Postal_Code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseurs_ajouter);

        editName = (EditText) findViewById(R.id.editName);
        editAddress_Street = (EditText) findViewById(R.id.editAddress_Street);
        editAddress_City = (EditText) findViewById(R.id.editAddress_City);
        editAddress_Province = (EditText) findViewById(R.id.editAddress_Province);
        editAddress_Postal_Code = (EditText) findViewById(R.id.editAddress_Postal_Code);
    }

    protected void ajouter(View vue){
        newSupplier();
        Intent fournisseurs = new Intent(FournisseursAjouter.this, FournisseursAffichage.class);
        startActivity(fournisseurs);
    }

    protected void newSupplier() {
        String url = "http://10.0.2.2:3033/suppliers";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> fournisseur = new HashMap<String, String>();
                fournisseur.put("name", editName.getText().toString());
                fournisseur.put("address_street", editAddress_Street.getText().toString());
                fournisseur.put("address_city", editAddress_City.getText().toString());
                fournisseur.put("address_province", editAddress_Province.getText().toString());
                fournisseur.put("address_postal_code", editAddress_Postal_Code.getText().toString());

                return fournisseur;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(postRequest);
    }
}

package com.example.e1363135.autopartsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ProduitsAjouter extends OptionMenu {
    EditText editCode,editName,editPrice,editFournisseur;
//    String code, name, price, fournisseur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produits_ajouter);

        editCode = (EditText) findViewById(R.id.editCode);
        editName = (EditText) findViewById(R.id.editName);
        editPrice = (EditText) findViewById(R.id.editPrice);
        editFournisseur = (EditText) findViewById(R.id.editFournisseur);

//        code = editCode.getText().toString();
//        name = editName.getText().toString();
//        price = editPrice.getText().toString();
//        fournisseur = editFournisseur.getText().toString();
    }

    protected void ajouter(View vue){
        newProduct();
        Intent produits = new Intent(ProduitsAjouter.this, ProduitsAffichage.class);
        startActivity(produits);
    }

    protected void newProduct() {
        String url = "http://10.0.2.2:3033/products";
        JSONObject product = new JSONObject();
        try{

            product.put("code", editCode.getText().toString());
            product.put("name", editName.getText().toString());
            product.put("price", editPrice.getText().toString());
            product.put("suppliers", editFournisseur.getText().toString());



        } catch (JSONException j) {}
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, product, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}

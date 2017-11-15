package com.example.e1363135.autopartsmobile.Produits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.e1363135.autopartsmobile.MySingleton;
import com.example.e1363135.autopartsmobile.OptionMenu;
import com.example.e1363135.autopartsmobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProduitsAjouter extends OptionMenu {
    EditText editCode,editName,editPrice;
    Spinner spinnerFournisseur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produits_ajouter);

        editCode = (EditText) findViewById(R.id.editCode);
        editName = (EditText) findViewById(R.id.editName);
        editPrice = (EditText) findViewById(R.id.editPrice);
        spinnerFournisseur = (Spinner) findViewById(R.id.spinnerFournisseur);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.getAllFournisseurs();
    }

    protected void getAllFournisseurs() {
        String url = "http://10.0.2.2:3033/suppliers";
        final ArrayList<String> listSuppliers = new ArrayList<String>();
        listSuppliers.add("Aucun Fournisseur");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++){
                                String supplier = data.getJSONObject(i).getString("name");
                                listSuppliers.add(supplier);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProduitsAjouter.this, android.R.layout.simple_list_item_1, listSuppliers);
                            spinnerFournisseur.setAdapter(adapter);
                        } catch(JSONException j) {

                        }
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

    protected void ajouter(View vue){
        String text = editPrice.getText().toString();
        try {
            int num = Integer.parseInt(text);
            newProduct();
            Intent produits = new Intent(ProduitsAjouter.this, ProduitsAffichage.class);
            startActivity(produits);
        } catch (NumberFormatException e) {
            Toast toast = Toast.makeText(getBaseContext(), "Veuillez entrer un nombre pour le prix", Toast.LENGTH_LONG);
            toast.show();        }
    }

    protected void newProduct() {
        String url = "http://10.0.2.2:3033/products";
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
               Map<String, String> product = new HashMap<String, String>();
               product.put("code", editCode.getText().toString());
               product.put("name", editName.getText().toString());
               product.put("price", editPrice.getText().toString());
               product.put("supplier", spinnerFournisseur.getSelectedItem().toString());
               return product;
           }
        };
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(postRequest);
    }
}

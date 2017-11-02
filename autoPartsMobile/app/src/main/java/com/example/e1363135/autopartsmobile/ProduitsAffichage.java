package com.example.e1363135.autopartsmobile;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProduitsAffichage extends OptionMenu {

    ListView listViewProduits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produits);

        listViewProduits = (ListView) findViewById(R.id.listProduits);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.getAllProducts();
    }

    protected void getAllProducts() {
        String url = "http://10.0.2.2:3033/products";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            ArrayList<String> listProducts = new ArrayList<String>();
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++){
                                listProducts.add(data.getJSONObject(i).getString("code") + " " + data.getJSONObject(i).getString("name") + " " + data.getJSONObject(i).getString("price"));
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProduitsAffichage.this, android.R.layout.simple_list_item_1, listProducts);
                            listViewProduits.setAdapter(adapter);
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
}

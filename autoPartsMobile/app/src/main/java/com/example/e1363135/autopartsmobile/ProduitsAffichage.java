package com.example.e1363135.autopartsmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        listViewProduits.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                return false;
            }
        });
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
                            ArrayList<Products> listProducts = new ArrayList<Products>();
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++){
                                Products produit = new Products(data.getJSONObject(i).getString("_id"),
                                        data.getJSONObject(i).getString("code"),
                                        data.getJSONObject(i).getString("name"),
                                        data.getJSONObject(i).getInt("price"),
                                        data.getJSONObject(i).getString("supplier"));
                                listProducts.add(produit);
                            }
                            ArrayAdapter<Products> adapter = new ArrayAdapter<Products>(ProduitsAffichage.this, android.R.layout.simple_list_item_1, listProducts);
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

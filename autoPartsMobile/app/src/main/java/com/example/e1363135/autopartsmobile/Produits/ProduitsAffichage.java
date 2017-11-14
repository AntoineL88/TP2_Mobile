package com.example.e1363135.autopartsmobile.Produits;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.e1363135.autopartsmobile.MySingleton;
import com.example.e1363135.autopartsmobile.OptionMenu;
import com.example.e1363135.autopartsmobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProduitsAffichage extends OptionMenu {

    ListView listViewProduits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produits_affichage);


        listViewProduits = (ListView) findViewById(R.id.listViewProduits);
        listViewProduits.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, final int position, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProduitsAffichage.this);
                dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i){
                        Products p = (Products) adapterView.getItemAtPosition(position);
                        Intent editProduits = new Intent(ProduitsAffichage.this, ProduitsEdit.class);
                        editProduits.putExtra("_id", p._id);
                        startActivity(editProduits);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        Products p = (Products) adapterView.getItemAtPosition(position);
                        deleteProduct(p._id);
                    }
                });

                dialog.show();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.getAllProducts();
    }

    protected void deleteProduct(String id) {
        String url = "http://10.0.2.2:3033/products?_id=" + id;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    };
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
        finish();
        startActivity(getIntent());
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

package com.example.e1363135.autopartsmobile.Fournisseurs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.example.e1363135.autopartsmobile.Produits.Products;
import com.example.e1363135.autopartsmobile.Produits.ProduitsAffichage;
import com.example.e1363135.autopartsmobile.Produits.ProduitsEdit;
import com.example.e1363135.autopartsmobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FournisseursAffichage extends OptionMenu {

    ListView listViewFournisseurs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseurs_affichage);

        listViewFournisseurs = (ListView) findViewById(R.id.listViewFournisseurs);
        listViewFournisseurs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, final int position, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(FournisseursAffichage.this);
                dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i){
                        Suppliers s = (Suppliers) adapterView.getItemAtPosition(position);
                        Intent editFournisseurs = new Intent(FournisseursAffichage.this, FournisseursEdit.class);
                        editFournisseurs.putExtra("_id", s._id);
                        startActivity(editFournisseurs);
                    }
                }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                Suppliers s = (Suppliers) adapterView.getItemAtPosition(position);
                                deleteFournisseur(s._id);
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
        this.getAllFournisseurs();
    }

    protected void deleteFournisseur(String id) {
        String url = "http://10.0.2.2:3033/suppliers?_id=" + id;

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

    protected void getAllFournisseurs() {
        String url = "http://10.0.2.2:3033/suppliers";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            ArrayList<Suppliers> listSuppliers = new ArrayList<Suppliers>();
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++){
                                Suppliers supplier = new Suppliers(data.getJSONObject(i).getString("_id"),
                                        data.getJSONObject(i).getString("name"),
                                        data.getJSONObject(i).getString("address_street"),
                                        data.getJSONObject(i).getString("address_city"),
                                        data.getJSONObject(i).getString("address_province"),
                                        data.getJSONObject(i).getString("address_postal_code"));
                                listSuppliers.add(supplier);
                            }
                            ArrayAdapter<Suppliers> adapter = new ArrayAdapter<Suppliers>(FournisseursAffichage.this, android.R.layout.simple_list_item_1, listSuppliers);
                            listViewFournisseurs.setAdapter(adapter);
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

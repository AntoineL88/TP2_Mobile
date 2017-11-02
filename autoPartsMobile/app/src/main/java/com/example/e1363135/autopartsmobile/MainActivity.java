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

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView mTxtDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtDisplay = (TextView) findViewById(R.id.textView);
    }

    public void goToProduits(View view) {
        Intent produits = new Intent(MainActivity.this, ProduitsAffichage.class);
        startActivity(produits);
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
                        mTxtDisplay.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        mTxtDisplay.setText("Response: " + error.toString());
                    }
                });

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}


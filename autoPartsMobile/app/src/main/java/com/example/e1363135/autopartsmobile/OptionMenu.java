package com.example.e1363135.autopartsmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.e1363135.autopartsmobile.Fournisseurs.FournisseursAjouter;
import com.example.e1363135.autopartsmobile.Produits.ProduitsAjouter;

public class OptionMenu extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_AjouterProduit:
                Intent produitsAjouter = new Intent(this, ProduitsAjouter.class);
                startActivity(produitsAjouter);
                return true;
            case R.id.action_AjouterFournisseur:
                Intent fournisseurAjouter = new Intent(this, FournisseursAjouter.class);
                startActivity(fournisseurAjouter);
                return true;
            case R.id.action_Accueil:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

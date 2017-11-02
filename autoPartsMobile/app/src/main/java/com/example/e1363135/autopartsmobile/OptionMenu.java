package com.example.e1363135.autopartsmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class OptionMenu extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_Ajouter:
                Intent produitsAjouter = new Intent(this, ProduitsAjouter.class);
                startActivity(produitsAjouter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

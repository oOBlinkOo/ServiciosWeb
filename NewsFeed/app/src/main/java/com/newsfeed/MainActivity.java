package com.newsfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.apache.http.Header;
import org.json.*;

import java.util.ArrayList;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pulsa);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void ingresar(View view){
        EditText nombre =(EditText)findViewById(R.id.editText1);

        String vacio="";
        Log.i("pro", nombre.getText().toString());
        Log.i("Entro al ingresar", vacio);

                Intent intent = new Intent();
                intent.setClass(this, ListaObjetos.class);
//                intent.putExtra("cancel", "cancel");
                startActivity(intent);
                Log.i("Entro a ListaObjet", "norecuerdo");

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
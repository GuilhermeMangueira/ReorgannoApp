package com.example.reorgannoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class Activity2 extends AppCompatActivity {
    String items[] = new String [] {"Fernanda" , "Gabi", "Diego", "Rodrigo"}; // nome dos revendedores
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ListView listView  = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // click para nova tela
                Intent intent = new Intent(getApplicationContext(), ActivityInfo.class);
                //intent.putExtra("name", items[position]);
                startActivity(intent);
            }
        });

     }


}

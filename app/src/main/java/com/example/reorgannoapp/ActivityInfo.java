package com.example.reorgannoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityInfo extends AppCompatActivity {

    Button revendedor;
    Button produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        revendedor = (Button)findViewById(R.id.buttonRevendedor);
        produtos = (Button)findViewById(R.id.buttonProdutos);


        revendedor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ActivityInfo.this, RevendedorActivity.class));
            }
        });

    }
}

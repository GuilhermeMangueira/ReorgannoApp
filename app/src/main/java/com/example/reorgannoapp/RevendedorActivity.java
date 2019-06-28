package com.example.reorgannoapp;

import android.Manifest;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RevendedorActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText revendedor_name;
    EditText revendedor_telefone;
    Button button_cadastrar;
    Button button_localizacao;

    double lat;
    double lon;

    DatabaseReference databaseRevendedores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revendedor);

        databaseRevendedores = FirebaseDatabase.getInstance().getReference("revendedores");
        mAuth = FirebaseAuth.getInstance();

        revendedor_name = (EditText)findViewById(R.id.revendedor_name);
        revendedor_telefone  = (EditText) findViewById(R.id.revendedor_telefone);
        button_cadastrar = (Button)findViewById(R.id.button_cadastrar);
        button_localizacao = (Button) findViewById(R.id.button_localizacao);

        lat = 0;
        lon = 0;

        ActivityCompat.requestPermissions(RevendedorActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        button_localizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalizacaoGPS localizador = new LocalizacaoGPS(getApplicationContext());
                Location location = localizador.getLocation();
                if(location != null){
                     lat = location.getLatitude();
                     lon = location.getLongitude();
                }
            }
        });

        button_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRevendedor();
            }
        });


    }


    private void addRevendedor(){
        String nome = revendedor_name.getText().toString().trim();
        String telefone = revendedor_telefone.getText().toString().trim();
        FirebaseUser user = mAuth.getCurrentUser();

        if((lat ==lon)&& lat==0){
            Toast.makeText(this,"Por favor libere a localizacao",Toast.LENGTH_LONG).show();
        }

        if(!TextUtils.isEmpty(nome)){
            if(!TextUtils.isEmpty(telefone)){

                String id = databaseRevendedores.push().getKey();
                boolean hidratante = false;

                Revendedor revendedor = new Revendedor(user.getUid(), nome, telefone, lat,lon, hidratante);

                databaseRevendedores.child(id).setValue(revendedor);

                Toast.makeText(this, "Revendedor adicionado", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, "Favor insira um telefone", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Favor insira um nome",Toast.LENGTH_LONG).show();
        }

    }

}

package com.example.reorgannoapp;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RevendedorActivity extends AppCompatActivity {
    EditText revendedor_name;
    EditText revendedor_telefone;
    Button button_cadastrar;
    Button button_localizacao;
    Button voltar;
    TextView lat_show;
    TextView lon_show;
    double lat;
    double lon;
    DatabaseReference rev_ref;
    DatabaseReference databaseRevendedores;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revendedor);

        databaseRevendedores = FirebaseDatabase.getInstance().getReference("revendedores");
        mAuth = FirebaseAuth.getInstance();
        rev_ref = databaseRevendedores.getRef();

        revendedor_name = (EditText)findViewById(R.id.revendedor_name);
        revendedor_telefone  = (EditText) findViewById(R.id.revendedor_telefone);
        button_cadastrar = (Button)findViewById(R.id.button_cadastrar);
        button_localizacao = (Button) findViewById(R.id.button_localizacao);
        voltar = (Button)  findViewById(R.id.buttonVoltar);
        lat_show = (TextView)findViewById(R.id.lat_show_text);
        lon_show = (TextView)findViewById(R.id.lon_show_text);

        rev_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                initializeVariables(viewData(dataSnapshot));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ActivityCompat.requestPermissions(RevendedorActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        button_localizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalizacaoGPS localizador = new LocalizacaoGPS(getApplicationContext());
                Location location = localizador.getLocation();
                if(location != null){
                     lat = location.getLatitude();
                     lon = location.getLongitude();
                     Toast.makeText(RevendedorActivity.this,"Localização Atualizada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRevendedor();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RevendedorActivity.this, ActivityInfo.class));            }
        });

    }

    private void initializeVariables(Revendedor rev){
        FirebaseUser user = mAuth.getCurrentUser();

        if(rev == null){
            databaseRevendedores.push();
        }
        else{
          revendedor_name.setText(rev.getRevendedor_nome());
          revendedor_telefone.setText(rev.getRevendedor_telefone());
          lat = rev.getLat();
          lon = rev.getLon();
        }
        lat_show.setText("" +lat);
        lon_show.setText(""+lon);

    }

    public Revendedor viewData(DataSnapshot dataSnapshot) {
        FirebaseUser user = mAuth.getCurrentUser();
        Revendedor rev = null;
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            if(ds.getKey().equals(user.getUid())) {
                rev =ds.getValue(Revendedor.class);
            }
        }
        return rev;

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

                String id = user.getUid();
                boolean hidratante = false;

                Revendedor revendedor = new Revendedor( nome, telefone, lat,lon, hidratante);

                databaseRevendedores.child(id).setValue(revendedor);


                Toast.makeText(this, "Revendedor Atualizado", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, "Favor insira um telefone", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Favor insira um nome",Toast.LENGTH_LONG).show();
        }

    }

}

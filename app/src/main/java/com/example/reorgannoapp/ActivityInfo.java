package com.example.reorgannoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ActivityInfo extends AppCompatActivity {

    DatabaseReference rev_ref;
    DatabaseReference databaseRevendedores;
    private FirebaseAuth mAuth;
    TextView nome;
    TextView telefone;
    TextView lat;
    TextView lon;
    TextView hidratante;


    Button revendedor;
    Button produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        revendedor = (Button)findViewById(R.id.buttonRevendedor);
        produtos = (Button)findViewById(R.id.buttonProdutos);

        nome = (TextView)findViewById(R.id.nomeView);
        telefone = (TextView)findViewById(R.id.telefoneView);
        lat = (TextView)findViewById(R.id.latView);
        lon = (TextView)findViewById(R.id.lonView);
        hidratante = (TextView)findViewById(R.id.hidratanteView);


        databaseRevendedores = FirebaseDatabase.getInstance().getReference("revendedores");
        mAuth = FirebaseAuth.getInstance();
        rev_ref = databaseRevendedores.getRef();


        rev_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showRevendedor(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        revendedor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ActivityInfo.this, RevendedorActivity.class));
            }
        });


        produtos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ActivityInfo.this, ProdutosActivity.class));
            }
        });

    }

    private void showRevendedor(DataSnapshot dataSnapshot){
        FirebaseUser user = mAuth.getCurrentUser();
        Revendedor rev = null;
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            if(ds.getKey().equals(user.getUid())) {
                rev =ds.getValue(Revendedor.class);
            }
        }
        if(rev == null){
            nome.setText("");
            telefone.setText("");
            hidratante.setText("");
            lat.setText("" + 0);
            lon.setText(""+0);
        }
        else{
            nome.setText(rev.getRevendedor_nome());
            telefone.setText(rev.getRevendedor_telefone());
            lat.setText(""+ rev.getLat());
            lon.setText(""+rev.getLon());
            if(rev.getHidratante()){
                hidratante.setText("Hidratante");
            }
            if(!rev.getHidratante()){
                hidratante.setText("");
            }
        }
    }
}

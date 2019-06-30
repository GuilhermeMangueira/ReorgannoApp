package com.example.reorgannoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProdutosActivity extends AppCompatActivity {

    Switch hidratante;
    Button voltar;
    DatabaseReference rev_ref;
    DatabaseReference databaseRevendedores;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    Revendedor revendedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        voltar = (Button)findViewById(R.id.buttonVoltar);
        hidratante = (Switch)findViewById(R.id.switchHidratante);

        databaseRevendedores = FirebaseDatabase.getInstance().getReference("revendedores");
        mAuth = FirebaseAuth.getInstance();
        rev_ref = databaseRevendedores.getRef();



        hidratante.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ativarHidratante(b);
            }
        });

        rev_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                revendedor = viewData(dataSnapshot);
                if(revendedor == null){
                    return;
                }
                hidratante.setChecked(revendedor.getHidratante());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProdutosActivity.this, ActivityInfo.class));            }
        });

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

    private void ativarHidratante(boolean ativar){
        FirebaseUser user = mAuth.getCurrentUser();
        if(revendedor == null){
            Toast.makeText(ProdutosActivity.this,"Por favor atualize seu cadastro", Toast.LENGTH_SHORT).show();
        }
        else{
            revendedor.setHidratante(ativar);
            databaseRevendedores.child(user.getUid()).setValue(revendedor);
            if(ativar) {
                Toast.makeText(ProdutosActivity.this, "Venda de hidratante habilitada", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(ProdutosActivity.this,"Venda de hidratante desabilitada", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

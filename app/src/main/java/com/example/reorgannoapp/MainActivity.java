package com.example.reorgannoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button registrar;
    Button logar;
    EditText email;
    EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrar = (Button) findViewById(R.id.buttonRegistrarNovo);
        logar = (Button)findViewById(R.id.buttonLogar);
        email = (EditText)findViewById(R.id.Login);
        senha = (EditText)findViewById(R.id.Senha);

        mAuth = FirebaseAuth.getInstance();

        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });



    }

 private void logar(){
        String email = this.email.getText().toString();
        String password = this.senha.getText().toString();
     mAuth.signInWithEmailAndPassword(email, password)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {
                         // Sign in success, update UI with the signed-in user's information
                         FirebaseUser user = mAuth.getCurrentUser();
                         Toast.makeText(MainActivity.this, "sucesso",Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(MainActivity.this,ActivityInfo.class ));
                     } else {
                         // If sign in fails, display a message to the user.
                         Toast.makeText(MainActivity.this, "Authentication failed.",
                                 Toast.LENGTH_SHORT).show();

                     }

                     // ...
                 }
             });
 }

}

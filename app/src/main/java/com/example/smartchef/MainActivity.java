package com.example.smartchef;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartchef.Vista.Inicio;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/*
Página de Login (inicio)
 */

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button btnsignin, btnsignup;
    EditText user, pass;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if(mAuth==null) mAuth = FirebaseAuth.getInstance();
            FirebaseUser cUser = mAuth.getCurrentUser();
        }catch (Exception e){
            Log.e("ERROR","Something");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        btnsignin = findViewById(R.id.btSign);
        btnsignup=findViewById(R.id.btCorreo);
        user= findViewById(R.id.edtxUser);
        pass=findViewById(R.id.edtxpass);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us = String.valueOf(user.getText()),pas= String.valueOf(pass.getText());
                if (us.length()==0 || pas.length()==0){
                    Toast.makeText(MainActivity.this, "Ingrese su usuario y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (us.equals(pas)){
                    Intent myIntent = new Intent(MainActivity.this, home_r.class);
                    startActivity(myIntent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Usuario y/o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SignUp.class);
                startActivity(myIntent);
            }
        });
    }
}
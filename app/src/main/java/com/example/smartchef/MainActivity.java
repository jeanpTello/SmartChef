package com.example.smartchef;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mAuth = FirebaseAuth.getInstance();
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

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
    }
}
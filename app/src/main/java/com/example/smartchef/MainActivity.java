package com.example.smartchef;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartchef.Vista.Inicio;
import com.example.smartchef.Vista.home;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
Página de Login (inicio)
 */

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGSC;
    private LoginButton btnFbsign;
    private Button btnLogin, btnCorreo;
    private TextView txUser, txCon;
    private CallbackManager callBackManager;
    private FirebaseAuth auth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        //Declaration
        mAuth = FirebaseAuth.getInstance();
        btnFbsign = (LoginButton) findViewById(R.id.btFbsign);
        btnLogin = findViewById(R.id.btSign);
        btnCorreo = findViewById(R.id.btCorreo);
        txCon = findViewById(R.id.edtxpass);
        txUser = findViewById(R.id.edtxUser);
        auth = FirebaseAuth.getInstance();

        callBackManager = CallbackManager.Factory.create();
        btnFbsign.registerCallback(callBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Success
                goMainScreen();
            }

            @Override
            public void onCancel() {
                //Cancel
            }

            @Override
            public void onError(FacebookException error) {
                //Error
            }
        });

        btnCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Normal", "onClick: Correo");
                startActivity(new Intent(MainActivity.this, Inicio.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = txUser.getText().toString(), p = txCon.getText().toString();
                if(TextUtils.isEmpty(u) || TextUtils.isEmpty(p))
                    Toast.makeText(MainActivity.this, "Debe ingresar tanto correo como usuario", Toast.LENGTH_SHORT).show();
                else{
                    final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                    pd.setMessage("Ingresando a Mercabiz");
                    pd.show();
                    auth.signInWithEmailAndPassword(u, p)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    pd.dismiss();
                                    Intent intent = new Intent(MainActivity.this, Inicio.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "Error al logearse, verificar conexión a internet", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }
                            }
                        });
                }
            }
        });
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGSC = GoogleSignIn.getClient(this, gso);
    }

    private void goMainScreen(){
        Intent i = new Intent(this, home.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
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
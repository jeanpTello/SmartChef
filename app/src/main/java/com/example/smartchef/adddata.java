package com.example.smartchef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class adddata extends AppCompatActivity
{
    EditText name,stock,unidad,img;
    Button submit,back;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);

        name=(EditText)findViewById(R.id.add_name);
        stock=(EditText)findViewById(R.id.stock);
        unidad=(EditText)findViewById(R.id.add_unidad);

        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

    private void processinsert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("user","d");
        map.put("name",name.getText().toString());
        map.put("stock",stock.getText().toString());
        map.put("unidad",unidad.getText().toString());
        map.put("purl","https://img2.freepng.es/20180421/rbe/kisspng-vegetarian-cuisine-ingredient-food-computer-icons-ingredients-5adb227fb36d57.8326866015243106557349.jpg");
        FirebaseDatabase.getInstance().getReference().child("ingredientes").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        name.setText("");
                        stock.setText("");
                        unidad.setText("");
                        Toast.makeText(getApplicationContext(),"Ingrediente Almacenado Correctamente",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Ocurrió un Error",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
package com.example.smartchef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class home_r extends AppCompatActivity {
    ImageView imgInventario, imgRecetas, imgSemana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_r);
        imgInventario= findViewById(R.id.imgInventario);
        imgInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_r.this,Inventario.class);
                startActivity(intent);
            }
        });
        imgRecetas= findViewById(R.id.imgRecetas);
        imgSemana=findViewById(R.id.imgSemana);
        View.OnClickListener notfound= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_r.this,NotFound.class);
                startActivity(intent);
            }
        };
        imgRecetas.setOnClickListener(notfound);
        imgSemana.setOnClickListener(notfound);

    }

}
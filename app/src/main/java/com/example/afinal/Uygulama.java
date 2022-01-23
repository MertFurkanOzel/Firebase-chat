package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Uygulama extends AppCompatActivity {

    Button arkadaslar,profil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uygulama);
        arkadaslar=findViewById(R.id.buttonarkadas);
        profil=findViewById(R.id.buttonprofil);

        arkadaslar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arkadaslargecis=new Intent(Uygulama.this,Arkadaslar.class);
                startActivity(arkadaslargecis);
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profilgecis=new Intent(Uygulama.this,Profil.class);
                startActivity(profilgecis);
            }
        });
    }
}
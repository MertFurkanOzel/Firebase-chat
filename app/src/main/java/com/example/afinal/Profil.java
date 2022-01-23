package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Profil extends AppCompatActivity {
    Button guncelle;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    EditText etisim,ethakkimda,etdogumtarihi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tanimla();
        bilgilerigetir();

        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Kullanicilar kullanicilar=new Kullanicilar(etisim.getText().toString(),ethakkimda.getText().toString(),etdogumtarihi.getText().toString());
                reference.child("Kullanıcılar").child(user.getUid()).setValue(kullanicilar);
                Toast.makeText(getApplicationContext(),"Bilgileriniz Güncellendi",Toast.LENGTH_LONG).show();
            }
        });


    }
    void bilgilerigetir()
    {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               String ad=snapshot.child("Kullanıcılar").child(user.getUid()).child("kullaniciadi").getValue().toString();
               String hakkmda=snapshot.child("Kullanıcılar").child(user.getUid()).child("hakkinda").getValue().toString();
               String dtarihi=snapshot.child("Kullanıcılar").child(user.getUid()).child("dogumyili").getValue().toString();
               etisim.setText(ad);
               ethakkimda.setText(hakkmda);
               etdogumtarihi.setText(dtarihi);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    void tanimla()
    {
        setContentView(R.layout.activity_profil);
        etisim=findViewById(R.id.edittextisim);
        ethakkimda=findViewById(R.id.edittexthakkimda);
        etdogumtarihi=findViewById(R.id.edittextdogum);
        guncelle=findViewById(R.id.buttonguncelle);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
    }

}
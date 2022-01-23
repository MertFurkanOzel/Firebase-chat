package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Arkadaslar extends AppCompatActivity {
    int sayi=0;
    EditText eklenenark,mesajark;
    Button arkekle,chatbutton;
    FirebaseDatabase database;
    DatabaseReference reference,reference2;
    FirebaseAuth auth;
    FirebaseUser user;
    String mesajkisi,mesajkisiid;
    List<String> list=new ArrayList<>();
    List<String> list2=new ArrayList<>();


    ArrayAdapter<String > arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arkadaslar);
        tanimlar();


        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<list.size();i++)
                {
                    if(list.get(i).equals(mesajark.getText().toString()))
                    {
                        mesajkisi=list.get(i);
                        mesajkisiid=list2.get(i);
                    }
                }
                if(mesajkisiid==null || mesajkisiid==null)
                    Toast.makeText(getApplicationContext(), "Öyle Bir Kullanıcı Yok", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Arkadaslar.this,Chat.class);
                intent.putExtra("isim",mesajkisi);
                intent.putExtra("id",mesajkisiid);
                startActivity(intent);
            }
        });


        reference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(!list.contains(snapshot.child("kullaniciadi").getValue().toString()))
                    list.add(snapshot.child("kullaniciadi").getValue().toString());
                if(!list2.contains(snapshot.getKey()))
                    list2.add(snapshot.getKey());




                sayi++;



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        arkekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<list.size(); i++)
                {
                                String listedengelen= list.get(i);
                                String etgelen=eklenenark.getText().toString();

                                if(listedengelen.equals(etgelen))
                                {


                                    int finalI = i;
                                    reference.child("Arkadaslik").child(user.getUid()).child(list2.get(i)).setValue("Gönderdi").addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                reference.child("Arkadaslik").child(list2.get(finalI)).child(user.getUid()).setValue("Aldı");
                                                Toast.makeText(getApplicationContext(), etgelen+" Artık arkadaşınız", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(getApplicationContext(), "Bir hata ile karşılaşıldı", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });




                                }




                }
            }
        });


    }
    void tanimlar()
    {
        eklenenark=findViewById(R.id.maileklenen);
        arkekle=findViewById(R.id.buttonarkekle);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        reference2=database.getReference().child("Kullanıcılar");
        mesajark=findViewById(R.id.chatkadi);
        chatbutton=findViewById(R.id.buttonchat);


    }

}
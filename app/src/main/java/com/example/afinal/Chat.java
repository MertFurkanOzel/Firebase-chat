package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Chat extends AppCompatActivity {

    TextView textisim;
    EditText editText;
    Button gonder;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    String id,isim,Mesaj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tanimla();


         gonder.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Mesaj=editText.getText().toString();

                 mesajigonder(Mesaj);
                 editText.setText("");


             }
         });


    }

    void tanimla()
    {
        Bundle extras=getIntent().getExtras();
        isim=extras.get("isim").toString();
        id=extras.getString("id");
        textisim=findViewById(R.id.textisim);
        textisim.setText(isim);
        editText=findViewById(R.id.mesajicerigi);
        gonder=findViewById(R.id.buttongonder);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
    }
    void mesajigonder(String message)
    {
        HashMap<String,Object> map=new HashMap<>();
        map.put("Kimden",user.getUid());
        map.put("Mesaj içeriği",message);
        map.put("Zaman",date());
        String mesajid=reference.child("Mesajlar").child(user.getUid()).child(id).push().getKey();
        reference.child("Mesajlar").child(user.getUid()).child(id).child(mesajid).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                reference.child("Mesajlar").child(id).child(user.getUid()).child(mesajid).setValue(map);
            }
        });
    }
    public String date()
    {
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today= Calendar.getInstance().getTime();
        String tarih=dateFormat.format(today);
        return tarih;
    }
}
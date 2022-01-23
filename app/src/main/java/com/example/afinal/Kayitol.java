package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Kayitol extends AppCompatActivity {

    EditText mailet,parolaet;
    Button kayitolbutton,hesabimvarbutton;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitol);
        user=FirebaseAuth.getInstance().getCurrentUser();
        parolaet=findViewById(R.id.textinputparola);
        mailet=(EditText) findViewById(R.id.textinputemail);
        kayitolbutton=findViewById(R.id.buttonkayitol);
        auth=FirebaseAuth.getInstance();
        kayitolbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=mailet.getText().toString();
                String parola=parolaet.getText().toString();
                if(mail!="" && parola!="")
                kayit(mail,parola);
                else{
                    Toast.makeText(getApplicationContext(),"Mail ve parola kısmı boş bırakılamaz.",Toast.LENGTH_LONG).show();
                }
            }

        });
        hesabimvarbutton=findViewById(R.id.buttonhesabımvar);
        hesabimvarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainedon=new Intent(Kayitol.this,MainActivity.class);
                startActivity(mainedon);
                finish();
            }
        });
    }
    public void kayit(String mail,String parola)
    {
        auth.createUserWithEmailAndPassword(mail,parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete())
                {
                    database=FirebaseDatabase.getInstance();
                    reference=FirebaseDatabase.getInstance().getReference();
                    //HashMap map=new HashMap();
                   /* map.put("Resim","null");
                    map.put("İsim","null");
                    map.put("Hakkımda","null");
                    map.put("Doğum Tarihi","null"); */
                    Kullanicilar kullanicilar=new Kullanicilar("null","null","null");
                    reference.child("Kullanıcılar").child(user.getUid()).setValue(kullanicilar);
                    Toast.makeText(getApplicationContext(),"BAŞARILI",Toast.LENGTH_LONG).show();
                    Intent intent2=new Intent(Kayitol.this,MainActivity.class);
                    startActivity(intent2);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Kayıt sırasında sorun oluştu.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
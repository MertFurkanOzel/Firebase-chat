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

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Button hesapolustur,girisbutonu;
    String maill,pass;
    EditText mail,parola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();

        hesapolustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hesapolusturmaintent=new Intent(MainActivity.this,Kayitol.class);
                startActivity(hesapolusturmaintent);
                finish();
            }
        });

        girisbutonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maill=mail.getText().toString();
                pass=parola.getText().toString();
                giris(maill,pass);
            }
        });
    }

    public void tanimla()
    {
        girisbutonu=findViewById(R.id.buttongiris);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        hesapolustur=findViewById(R.id.buttonhesapolustur);
        mail=findViewById(R.id.textinputmail2);
        parola=findViewById(R.id.textinputparola2);

    }

    /*public void kontrol()
    {
        if(user==null)
        {
            Intent intent=new Intent(MainActivity.this, Kayitol.class);
            startActivity((intent));
            finish();
        }
    }*/
    public void giris(String email,String password)
    {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Giriş Başarılı",Toast.LENGTH_LONG).show();
                    Intent uygulamaanaekran=new Intent(MainActivity.this,Uygulama.class);
                    startActivity(uygulamaanaekran);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Giriş Başarısız Tekrar Deneyin",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
package com.furkanartik.uniworkproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class kayitOl extends AppCompatActivity {

    public Button kayittanGiris;
    public EditText email, sifre, isim;
    public String txtEmail, txtsifre, txtisim;
    public FirebaseAuth mAuth;
    public DatabaseReference mReference;
    public HashMap<String, Object> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.edtKayitOlMail);
        sifre = findViewById(R.id.edtKayitOlSifre);
        isim = findViewById(R.id.edtisim);

        kayittanGiris = findViewById(R.id.kayittanGiris);

        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();

        kayittanGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(kayitOl.this, girisYap.class));
            }
        });
    }
    public void kayitOl(View v){
        txtisim = isim.getText().toString();
        txtEmail = email.getText().toString();
        txtsifre = sifre.getText().toString();

        if (!TextUtils.isEmpty(txtisim) && !TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtsifre)){
            mAuth.createUserWithEmailAndPassword(txtEmail, txtsifre)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                mData = new HashMap<>();
                                mData.put("kullaniciAdi", txtisim);
                                mData.put("kullaniciEmail", txtEmail);
                                mData.put("kullaniciSifre", txtsifre);

                                mReference.child("Kullanicilar")
                                        .setValue(mData)
                                        .addOnCompleteListener(kayitOl.this, new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                    Toast.makeText(kayitOl.this,"Kayit Basarili", Toast.LENGTH_SHORT).show();
                                                else
                                                    Toast.makeText(kayitOl.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                            else
                                Toast.makeText(kayitOl.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }
        else
            Toast.makeText(this,"Bos Olamaz", Toast.LENGTH_SHORT).show();
    }
}
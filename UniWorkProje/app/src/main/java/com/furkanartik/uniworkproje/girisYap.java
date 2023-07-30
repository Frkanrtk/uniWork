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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class girisYap extends AppCompatActivity {

    public Button giristenKayit;
    public EditText email, sifre;
    public String txtEmail, txtsifre;
    public FirebaseAuth mAuth;
    public FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);
        email = findViewById(R.id.edtGirisMail);
        sifre = findViewById(R.id.edtGirisSifre);

        giristenKayit = findViewById(R.id.giristenKayit);

        mAuth = FirebaseAuth.getInstance();

        giristenKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(girisYap.this, kayitOl.class));
            }
        });
    }
    public void girisYap(View v){
        txtEmail = email.getText().toString();
        txtsifre = sifre.getText().toString();

        if (!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtsifre)) {
            mAuth.signInWithEmailAndPassword(txtEmail, txtsifre)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mUser = mAuth.getCurrentUser();
                            Toast.makeText(girisYap.this,"Hosgeldiniz", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(girisYap.this, islerAnaSayfa.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(girisYap.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else
            Toast.makeText(this,"Bos Olamaz", Toast.LENGTH_SHORT).show();
    }
}

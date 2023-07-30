package com.furkanartik.uniworkproje;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class UygulamaAnaGiris extends AppCompatActivity {
    public Button btnUygGirisCikis, btnUygGirisGiris, btnUygGirisKayit, btnUygGirisIsEkle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uygulama_ana_giris);
        btnUygGirisCikis = findViewById(R.id.uygGirisBtnCikis);
        btnUygGirisGiris = findViewById(R.id.uygGirisBtnGiris);
        btnUygGirisKayit = findViewById(R.id.uygGirisBtnKayit);
        btnUygGirisIsEkle = findViewById(R.id.uygGirisIsEkle);
        btnUygGirisIsEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UygulamaAnaGiris.this, isEkleme.class));
            }
        });
        btnUygGirisGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UygulamaAnaGiris.this, girisYap.class));
            }
        });
        btnUygGirisKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UygulamaAnaGiris.this, kayitOl.class));
            }
        });
        btnUygGirisCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
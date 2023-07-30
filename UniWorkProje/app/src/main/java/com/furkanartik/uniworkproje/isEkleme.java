package com.furkanartik.uniworkproje;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class isEkleme extends AppCompatActivity {
    private EditText firmaIsmiEditText;
    private EditText isTurEditText;
    private EditText tarihEditText;
    private EditText ucretEditText;
    private EditText iletisimEditText;
    private EditText adresEditText;
    private VeriTabani veriTabani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        firmaIsmiEditText = findViewById(R.id.firmaIsmiEditText);
        isTurEditText = findViewById(R.id.isTurEditText);
        tarihEditText = findViewById(R.id.tarihEditText);
        ucretEditText = findViewById(R.id.ucretEditText);
        iletisimEditText = findViewById(R.id.iletisimEditText);
        adresEditText = findViewById(R.id.adresEditText);

        veriTabani = new VeriTabani(this);

        Button gonderButton = findViewById(R.id.gonderButton);
        gonderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firmaIsmi = firmaIsmiEditText.getText().toString();
                String isTur = isTurEditText.getText().toString();
                long tarih = Long.parseLong(tarihEditText.getText().toString());
                double ucret = Double.parseDouble(ucretEditText.getText().toString());
                long iletisim = Long.parseLong(iletisimEditText.getText().toString());
                String adres = adresEditText.getText().toString();

                ekle(firmaIsmi, isTur, tarih, ucret, iletisim, adres);

                mesajYazdirma();
            }
        });
    }

    private void ekle(String firmaIsmi, String isTur, long tarih, double ucret, long iletisim, String adres) {
        SQLiteDatabase db = veriTabani.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VeriTabani.COLUMN_FIRMA_ISMI, firmaIsmi);
        values.put(VeriTabani.COLUMN_IS_TURU, isTur);
        values.put(VeriTabani.COLUMN_TARIH, tarih);
        values.put(VeriTabani.COLUMN_UCRET, ucret);
        values.put(VeriTabani.COLUMN_ILETISIM, iletisim);
        values.put(VeriTabani.COLUMN_ADRES, adres);

        db.insert(VeriTabani.TABLE_NAME, null, values);
        db.close();
    }

    private void mesajYazdirma() {
        Toast.makeText(this,"İş İlani Eklendi", Toast.LENGTH_SHORT).show();
    }
}

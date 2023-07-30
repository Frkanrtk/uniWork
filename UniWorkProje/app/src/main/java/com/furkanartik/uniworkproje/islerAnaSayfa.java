package com.furkanartik.uniworkproje;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class islerAnaSayfa extends AppCompatActivity {
    private TextView sonuclarTextView;

    private VeriTabani veriTabani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        sonuclarTextView = findViewById(R.id.sonuclarTextView);

        veriTabani = new VeriTabani(this);

        isEkle();
    }

    private void isEkle() {
        SQLiteDatabase db = veriTabani.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + VeriTabani.TABLE_NAME, null);

        StringBuilder result = new StringBuilder();

        if (cursor != null && cursor.moveToFirst()) {
            int firmaIsmiIndex = cursor.getColumnIndex(VeriTabani.COLUMN_FIRMA_ISMI);
            int isTurIndex = cursor.getColumnIndex(VeriTabani.COLUMN_IS_TURU);
            int tarihIndex = cursor.getColumnIndex(VeriTabani.COLUMN_TARIH);
            int ucretIndex = cursor.getColumnIndex(VeriTabani.COLUMN_UCRET);
            int iletisimIndex = cursor.getColumnIndex(VeriTabani.COLUMN_ILETISIM);
            int adresIndex = cursor.getColumnIndex(VeriTabani.COLUMN_ADRES);

            do {
                String firmaIsmi = cursor.getString(firmaIsmiIndex);
                String isTur = cursor.getString(isTurIndex);
                String tarih = cursor.getString(tarihIndex);
                String ucret = cursor.getString(ucretIndex);
                String iletisim = cursor.getString(iletisimIndex);
                String adres = cursor.getString(adresIndex);

                result.append("Firma İsmi : ").append(firmaIsmi).append("\n");
                result.append("İş Türü : ").append(isTur).append("\n");
                result.append("Tarih : ").append(tarih).append("\n");
                result.append("Ücret : ").append(ucret).append("\n");
                result.append("İletişim : ").append(iletisim).append("\n");
                result.append("Adres : ").append(adres).append("\n\n");
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        sonuclarTextView.setText(result.toString());
    }
}

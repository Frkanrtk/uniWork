package com.furkanartik.uniworkproje;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VeriTabani extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Kayit2.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Kayit";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRMA_ISMI = "firma_ismi";
    public static final String COLUMN_IS_TURU = "is_turu";
    public static final String COLUMN_TARIH = "tarih";
    public static final String COLUMN_UCRET = "ucret";
    public static final String COLUMN_ILETISIM = "iletisim";
    public static final String COLUMN_ADRES = "adres";

    public VeriTabani(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRMA_ISMI + " TEXT, " +
                COLUMN_IS_TURU + " TEXT, " +
                COLUMN_TARIH + " INTEGER, " +
                COLUMN_UCRET + " REAL, " +
                COLUMN_ILETISIM + " LONG, " +
                COLUMN_ADRES + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
}

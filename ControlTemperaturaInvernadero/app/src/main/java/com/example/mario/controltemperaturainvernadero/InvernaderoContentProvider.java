package com.example.mario.controltemperaturainvernadero;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class InvernaderoContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.mario.temperatura";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/Invernaderos");
    private SQLiteDatabase InvernaderosDB;
    public static final String KEY_ID = "_id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_TEMPERATURA = "temperatura";
    public static final String COLUMN_GRADOS = "grados";
    public static final String COLUMN_VISTANTG = "vistaNTG";
    public static final String COLUMN_KELVIN= "kelvin";
    public static final String COLUMN_FECHA = "fecha";
    public static final String COLUMN_HORA = "hora";
    public static final String COLUMN_VISTAF0 = "vistaFO";
    public static final String TABLE_NOMBRE = "Invernaderos";


    public static final int INVERNADEROS = 1;
    public static final int INVERNADEROS_ID = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "Invernaderos", INVERNADEROS);
        uriMatcher.addURI(AUTHORITY, "Invernaderos/#", INVERNADEROS_ID);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        BDInvernaderos dbHelper = new BDInvernaderos(context);
        InvernaderosDB= dbHelper.getWritableDatabase();
        return (InvernaderosDB==null) ? false : true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case INVERNADEROS:
                return "vnd.android.cursor.dir/vnd.exitae.invernaderos";
            case INVERNADEROS_ID:
                return "vnd.android.cursor.item/vnd.exitae.invernaderos";
            default:
                throw new IllegalArgumentException("URI no soportada"+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowID=InvernaderosDB.insert(TABLE_NOMBRE,"",values);
        if (rowID>0){
            Uri _uri= ContentUris.withAppendedId(CONTENT_URI,rowID);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }
        throw new SQLException("No se pudo Insertar"+uri);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqLiteBuilder = new SQLiteQueryBuilder();
        sqLiteBuilder.setTables(TABLE_NOMBRE);
        if (uriMatcher.match(uri)==INVERNADEROS_ID){
            sqLiteBuilder.appendWhere(KEY_ID + " = "+ uri.getPathSegments().get(1));
        }
        Cursor c =sqLiteBuilder.query(InvernaderosDB,projection,selection,selectionArgs,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

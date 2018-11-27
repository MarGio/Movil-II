package com.example.mario.controltemperaturainvernadero;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListaInvernaderos extends ListActivity {

    final String[] Columnas = new String[]{InvernaderoContentProvider.KEY_ID,
            InvernaderoContentProvider.COLUMN_NOMBRE,InvernaderoContentProvider.COLUMN_TEMPERATURA,
            InvernaderoContentProvider.COLUMN_GRADOS,InvernaderoContentProvider.COLUMN_VISTANTG,InvernaderoContentProvider.COLUMN_KELVIN,
            InvernaderoContentProvider.COLUMN_FECHA,InvernaderoContentProvider.COLUMN_HORA,InvernaderoContentProvider.COLUMN_VISTAF0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_invernaderos);
        cargarDatos();
    }

    public void cargarDatos(){
        Uri uriLista = Uri.parse("content://com.example.mario.temperatura/Invernaderos/*");
    }
}

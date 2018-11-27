package com.example.mario.controltemperaturainvernadero;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

public class MainActivity extends TabActivity implements View.OnClickListener{
    private  int dia,mes,anio,hora,minutos;
    EditText nombre,temperatura,efecha,ehora;
    Spinner spinnerG;
    boolean sdDisponible = false;
    boolean sdAccesoEscritura = false;
    final String[] Columnas = new String[]{InvernaderoContentProvider.KEY_ID,
            InvernaderoContentProvider.COLUMN_NOMBRE,InvernaderoContentProvider.COLUMN_TEMPERATURA,
            InvernaderoContentProvider.COLUMN_GRADOS, InvernaderoContentProvider.COLUMN_FECHA,InvernaderoContentProvider.COLUMN_HORA};
    NotificationCompat.Builder notificacion;
//    IntentFilter filtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("INICIO",null).setContent(R.id.tab1Layout));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("LISTA",null).setContent(new Intent(this,ListaInvernaderos.class)));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("EXPORTAR",null).setContent(R.id.tab3layout));

        nombre=findViewById(R.id.txtNombreInvernadero);
        temperatura=findViewById(R.id.txtTemperaturaInvernadero);
        efecha=findViewById(R.id.txtDia);
        ehora=findViewById(R.id.txthora);
        findViewById(R.id.btnGuardar).setOnClickListener(this);
        findViewById(R.id.btnExportarCSV).setOnClickListener(this);
        findViewById(R.id.btnExportarJSON).setOnClickListener(this);
        ehora.setOnClickListener(this);
        efecha.setOnClickListener(this);

        spinnerG = findViewById(R.id.spinnerGrados);
        String[] letra = {"°C","°F"};
        spinnerG.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));
//
//        filtro = new IntentFilter(ReceptorOperacion.ACTION_RESP);
//        filtro.addCategory(Intent.CATEGORY_DEFAULT);
//        registerReceiver(new ReceptorOperacion(), filtro);
    }
    }

    public void Notificar(String nom) {

            notificacion = new NotificationCompat.Builder(this);
            notificacion.setAutoCancel(true);
            notificacion.setSmallIcon(R.mipmap.ic_launcher);
            notificacion.setTicker("Nueva Notificacion");
            notificacion.setPriority(Notification.PRIORITY_HIGH);
            notificacion.setWhen(System.currentTimeMillis());
            notificacion.setContentTitle("Cambio Temperatura");
            notificacion.setContentText(nom);

            Intent intent1 = new Intent(MainActivity.this,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
            notificacion.setContentIntent(pendingIntent);

            NotificationManager nm =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            nm.notify(51623,notificacion.build());
        }
}


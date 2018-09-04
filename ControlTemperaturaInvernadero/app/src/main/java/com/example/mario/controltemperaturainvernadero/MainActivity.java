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
    public void onClick(View v) {
        if (v == efecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    efecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }
                    , anio, mes, dia);
            datePickerDialog.show();
        }
        if (v == ehora) {
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ehora.setText(hourOfDay + ":" + minute);
                }
            }, hora, minutos, false);
            timePickerDialog.show();
        }
        if (v.getId() == R.id.btnGuardar) {
            ConvercionesTemperatura CambioTemp = new ConvercionesTemperatura();
            if (nombre.getText().toString().isEmpty() == false && temperatura.getText().toString().isEmpty() == false && efecha.getText().toString().isEmpty() == false
                    && ehora.getText().toString().isEmpty() == false) {
                Uri uri = Uri.parse("content://com.example.mario.temperatura/Invernaderos");
                Invernadero inve = new Invernadero(nombre.getText().toString(),Double.parseDouble(temperatura.getText().toString()),
                        spinnerG.getSelectedItem().toString(),CambioTemp.ConvertirCelsios_Kelvin(Double.parseDouble(temperatura.getText().toString())),
                        efecha.getText().toString(),ehora.getText().toString());
                ContentValues values = new ContentValues();
                values.put("nombre", inve.getNombre());
                values.put("temperatura", inve.getTemperatura());
                values.put("grados", inve.getGrados());
                values.put("vistaNTG", (nombre.getText().toString() + "               " + temperatura.getText().toString() + " " + spinnerG.getSelectedItem().toString()));
                if (spinnerG.getSelectedItem().toString().equals("°C")) {
                    values.put("kelvin", inve.getKelvin());
                    values.put("fecha", inve.getFecha());
                    values.put("hora", inve.getHora());
                    values.put("vistaFO", (efecha.getText().toString() + "               " + ehora.getText().toString()));
                    getContentResolver().insert(uri, values);
                    Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(this, ServicioNotificaciones.class);
//                    startService(i);
                    BDInvernaderos obj = new BDInvernaderos(getApplicationContext());
                    obj.getById(inve.getNombre(),inve.getKelvin(),inve.getFecha(),inve.getHora());
                    if (obj.not){
                        Notificar("Invernadero: "+obj.inv.getNombre()+" Temperatura: "+obj.inv.getTemperatura()+obj.inv.getGrados()+" a "+inve.getTemperatura()+inve.getGrados());
                    }


                } else {
                    Invernadero inver = new Invernadero(nombre.getText().toString(),Double.parseDouble(temperatura.getText().toString()),
                            spinnerG.getSelectedItem().toString(),CambioTemp.ConvertirFahrenheit_Kelvin(CambioTemp.ConvertirCelcios(Double.parseDouble(temperatura.getText().toString()))),
                            efecha.getText().toString(),ehora.getText().toString());
                    values.put("kelvin", inver.getKelvin());
                    values.put("fecha", inver.getFecha());
                    values.put("hora", inver.getHora());
                    values.put("vistaFO", (efecha.getText().toString() + "               " + ehora.getText().toString()));
                    getContentResolver().insert(uri, values);
                    Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
                    BDInvernaderos obj = new BDInvernaderos(getApplicationContext());
                    obj.getById(inve.getNombre(),inve.getKelvin(),inve.getFecha(),inve.getHora());
                    if (obj.not){
                        Notificar("Invernadero: "+obj.inv.getNombre()+" Temperatura: "+obj.inv.getTemperatura()+obj.inv.getGrados()+" a "+inver.getTemperatura()+inver.getGrados());
                    }
                }

            } else {
                if (nombre.getText().toString().isEmpty()) {
                    nombre.setError("Compo requerido");
                }
                if (temperatura.getText().toString().isEmpty()) {
                    temperatura.setError("Compo requerido");
                }
                if (efecha.getText().toString().isEmpty()) {
                    efecha.setError("Compo requerido");
                }
                if (ehora.getText().toString().isEmpty()) {
                    ehora.setError("Compo requerido");
                }
            }
        }
        if (v.getId() == R.id.btnExportarCSV) {
            // Código que me comprueba si existe SD y si puedo escribir o no
            String estado = Environment.getExternalStorageState();
            if (estado.equals(Environment.MEDIA_MOUNTED))
            {
                sdDisponible = true;
                sdAccesoEscritura = true;
            }
            else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
            {
                sdDisponible = true;
                sdAccesoEscritura = false;
            }
            else
            {
                sdDisponible = false;
                sdAccesoEscritura = false;
            }
            if(sdAccesoEscritura && sdDisponible){
                try
                {
                    File ruta_sd = Environment.getExternalStorageDirectory();
                    File f = new File(ruta_sd.getAbsolutePath(), "Datosd.txt");
                    Uri uriLista = Uri.parse("content://com.example.mario.temperatura/Invernaderos/*");
                    Cursor cursores = managedQuery(uriLista,Columnas,null,null,null);
                    OutputStreamWriter fout =
                            new OutputStreamWriter(
                                    new FileOutputStream(f));
                    if (cursores.moveToFirst()){
                        do {
                            fout.write(cursores.getInt(0)+", ");
                            fout.write(cursores.getString(1)+", ");
                            fout.write(cursores.getDouble(2)+", ");
                            fout.write(cursores.getString(3)+", ");
                            fout.write(cursores.getString(4)+", ");
                            fout.write(cursores.getString(5)+"\n");
                        }while (cursores.moveToNext());
                    }
                    fout.close();
                    Toast.makeText(this, "Exportado con exito", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero en la tarjeta SD");
                }
            }
        }
        if (v.getId() == R.id.btnExportarJSON) {
            // Código que me comprueba si existe SD y si puedo escribir o no
            String estado = Environment.getExternalStorageState();
            if (estado.equals(Environment.MEDIA_MOUNTED)) {
                sdDisponible = true;
                sdAccesoEscritura = true;
            } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
                sdDisponible = true;
                sdAccesoEscritura = false;
            } else {
                sdDisponible = false;
                sdAccesoEscritura = false;
            }
            if (sdAccesoEscritura && sdDisponible) {
                try {
                    File ruta_sd = Environment.getExternalStorageDirectory();
                    File f = new File(ruta_sd.getAbsolutePath(), "DatosdJSON.txt");
                    Uri uriLista = Uri.parse("content://com.example.mario.temperatura/Invernaderos/*");
                    Cursor cursores = managedQuery(uriLista, Columnas, null, null, null);
                    OutputStreamWriter fout =
                            new OutputStreamWriter(
                                    new FileOutputStream(f));
                    if (cursores.moveToFirst()) {
                        do {
                            fout.write("id:"+cursores.getInt(0) + ", ");
                            fout.write("Nombre: "+cursores.getString(1) + ", ");
                            fout.write("Temperatura: "+cursores.getDouble(2) + ", ");
                            fout.write("Grados: "+cursores.getString(3) + ", ");
                            fout.write("Fecha :"+cursores.getString(4) + ", ");
                            fout.write("Hora: "+cursores.getString(5) + "\n");
                        } while (cursores.moveToNext());
                    }
                    fout.close();
                    Toast.makeText(this, "Exportado con exito", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al escribir fichero en la tarjeta SD");
                }
            }
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


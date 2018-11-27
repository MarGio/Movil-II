package com.example.mario.controltemperaturainvernadero;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class BDInvernaderos extends SQLiteOpenHelper {
    private static final String DB_NAME= "InvernaderosDB";
    private static final int DB_VERSION= 1;
//    public static final String KEY_ID = "_id";
    public static final String COLUMN_NOMBRE = "nombre";
//    public static final String COLUMN_TEMPERATURA = "temperatura";
    public boolean not=false;


//    private static final String DATABASE_CREATE =
//            "CREATE TABLE if not exists " + TABLE_NOMBRE + " (" +
//                    KEY_ID + " INTEGER NOT NULL," +
//                    COLUMN_NOMBRE + "TEXT NOT NULL," +
//                    COLUMN_TEMPERATURA + "DECIMAL NOT NULL,");
    String consultaTabla="CREATE TABLE Invernaderos(_id INTEGER NOT NULL, nombre TEXT NOT NULL," +
            "temperatura DECIMAL NOT NULL, grados TEXT NOT NULL,vistaNTG TEXT NOT NULL,kelvin DECIMAL NOT NULL," +
            "fecha DATE NOT NULL, hora TIME NOT NULL,vistaFO TEXT NOT NULL, PRIMARY KEY(_id))";

    final String[] Columnas = new String[]{InvernaderoContentProvider.KEY_ID,
            InvernaderoContentProvider.COLUMN_NOMBRE,InvernaderoContentProvider.COLUMN_TEMPERATURA,
            InvernaderoContentProvider.COLUMN_GRADOS,InvernaderoContentProvider.COLUMN_VISTANTG,InvernaderoContentProvider.COLUMN_KELVIN,
            InvernaderoContentProvider.COLUMN_FECHA,InvernaderoContentProvider.COLUMN_HORA,InvernaderoContentProvider.COLUMN_VISTAF0};

    public BDInvernaderos(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db.isReadOnly()){
            db=getWritableDatabase();
        }
        db.execSQL(consultaTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NOMBRE);
        db.execSQL(consultaTabla);
    }
    Invernadero inv = new Invernadero();
    public Invernadero getById(String nom, double temp, String fech, String hora){
        SQLiteDatabase db = this.getReadableDatabase();
        int numeroC=0, cambioH=0;
        double cambioT=0;
        String transH1="", transH2="";
        String At[],At1[];
        Cursor cursor =
                db.query(TABLE_NOMBRE, // a. table
                        Columnas, // b. column names
                        COLUMN_NOMBRE+" = ? and "+ COLUMN_FECHA+" = ?", // c. selections
                        new String[] { String.valueOf(nom), String.valueOf(fech)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null) {
            if (cursor.getCount() == 1) {
                numeroC = (cursor.getCount() - 1);
            } else {
                numeroC = (cursor.getCount() - 2);
            }
            cursor.moveToPosition(numeroC);

            inv.setId(Integer.parseInt(cursor.getString(0)));
            inv.setNombre(cursor.getString(1);
//            MainActivity no = new MainActivity();
//            no.Notificar("HOLA");
            Log.d("getBookById", inv.getId() + " " + inv.getNombre() + " " + inv.getTemperatura();        }
        return inv;
    }
}

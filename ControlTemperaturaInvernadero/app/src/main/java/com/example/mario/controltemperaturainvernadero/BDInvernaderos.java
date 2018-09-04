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
//    public static final String COLUMN_GRADOS = "grados";
//    public static final String COLUMN_VISTANTG = "vistaNTG";
    public static final String COLUMN_KELVIN= "kelvin";
    public static final String COLUMN_FECHA = "fecha";
    public static final String COLUMN_HORA = "hora";
//    public static final String COLUMN_VISTAF0 = "vistaFO";
    public static final String TABLE_NOMBRE = "Invernaderos";
    public boolean not=false;


//    private static final String DATABASE_CREATE =
//            "CREATE TABLE if not exists " + TABLE_NOMBRE + " (" +
//                    KEY_ID + " INTEGER NOT NULL," +
//                    COLUMN_NOMBRE + "TEXT NOT NULL," +
//                    COLUMN_TEMPERATURA + "DECIMAL NOT NULL," +
//                    COLUMN_GRADOS + "TEXT NOT NULL," +
//                    COLUMN_KELVIN + "DECIMAL NOT NULL," +
//                    COLUMN_FECHA + "DATE NOT NULL," +
//                    COLUMN_HORA + "TIME NOT NULL," +
//                    " PRIMARY KEY(" + KEY_ID +"));";

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
            inv.setNombre(cursor.getString(1));
            inv.setTemperatura(cursor.getDouble(2));
            inv.setGrados(cursor.getString(3));
            inv.setKelvin(cursor.getDouble(5));
            inv.setFecha(cursor.getString(6));
            inv.setHora(cursor.getString(7));
            cambioT = temp - inv.getKelvin();
            At = hora.split(":");
            At1 = inv.getHora().split(":");
            if (At[1].equals("0") || At[1].equals("1") || At[1].equals("2") || At[1].equals("3") ||
                    At[1].equals("4") || At[1].equals("5") || At[1].equals("6") || At[1].equals("7") ||
                    At[1].equals("8") || At[1].equals("9")){
                At[1]="0"+At[1];
            }
            if (At1[1].equals("0") || At1[1].equals("1") || At1[1].equals("2") || At1[1].equals("3") ||
                    At1[1].equals("4") || At1[1].equals("5") || At1[1].equals("6") || At1[1].equals("7") ||
                    At1[1].equals("8") || At1[1].equals("9")){
                At1[1]="0"+At1[1];
            }
            transH1 = At[0] + At[1];
            transH2 = At1[0] + At1[1];
            cambioH = Integer.parseInt(transH1) - Integer.parseInt(transH2);
            if (cambioT >= 11 && cambioH >= 100) {
                Log.d("getBookById ", "Cambio de: " + cambioT + " hora " + cambioH);
                not=true;
            }else {
                not=false;
            }

//            MainActivity no = new MainActivity();
//            no.Notificar("HOLA");
            Log.d("getBookById", inv.getId() + " " + inv.getNombre() + " " + inv.getTemperatura() + " " +
                    inv.getGrados() + " " + inv.getKelvin() + " " + inv.getFecha() + " " + inv.getHora() + " " + cambioH);
        }
        return inv;
    }
}

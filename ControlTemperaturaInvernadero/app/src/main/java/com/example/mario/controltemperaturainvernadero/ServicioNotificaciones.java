package com.example.mario.controltemperaturainvernadero;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class ServicioNotificaciones extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BDInvernaderos obj = new BDInvernaderos(getApplicationContext());
        Invernadero inv = new Invernadero();
        obj.getById(inv.getNombre(),inv.getKelvin(),inv.getFecha(),inv.getHora());
        return START_STICKY;
    }

    //    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
//
////        Intent i = new Intent();
////        i.setAction(MainActivity.ReceptorOperacion.ACTION_RESP);
////        i.addCategory(Intent.CATEGORY_DEFAULT);
////        i.putExtra("resultado", 8);
////        sendBroadcast(i);
//    }
}

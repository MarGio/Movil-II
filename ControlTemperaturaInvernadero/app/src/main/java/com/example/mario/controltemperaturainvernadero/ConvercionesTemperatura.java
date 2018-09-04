package com.example.mario.controltemperaturainvernadero;

import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

public class ConvercionesTemperatura {
    public double ConvertirCelcios(double temp){
        return (temp*1.8+32);
    }

    public double ConvertirCelsios_Kelvin(double temp){
        return (temp+283.15);
    }

    public double ConvertirFahrenheit_Kelvin(double temp){
        return ((5*(temp-32))/9)+273.15;
    }
}

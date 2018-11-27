package com.example.mario.controltemperaturainvernadero;

public class Invernadero {
    private int id;
    private String nombre;
    private double temperatura;
    private String grados;
    private double kelvin;
    private String fecha;
    private String hora;

    public Invernadero(){}

    public  Invernadero(String nombre, double temperatura,){
        super();
        this.nombre=nombre;
        this.temperatura=temperatura;    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
}

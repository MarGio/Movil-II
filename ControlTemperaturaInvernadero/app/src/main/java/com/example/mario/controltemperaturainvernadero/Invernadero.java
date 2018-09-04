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

    public  Invernadero(String nombre, double temperatura,String grados, double kelvin, String fecha, String hora){
        super();
        this.nombre=nombre;
        this.temperatura=temperatura;
        this.grados=grados;
        this.kelvin=kelvin;
        this.fecha=fecha;
        this.hora=hora;
    }

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

    public String getGrados() {
        return grados;
    }

    public void setGrados(String grados) {
        this.grados = grados;
    }

    public double getKelvin() {
        return kelvin;
    }

    public void setKelvin(double kelvin) {
        this.kelvin = kelvin;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

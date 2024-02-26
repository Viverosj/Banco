package com.example;

import java.util.ArrayList;

public class Usuario {

    // atributos

    private String Nombre;
    private String Dirección;
    private int Número;
    private String Correo;
    private String Contraseña;
    private ArrayList<Cuenta> NúmeroCuenta = new ArrayList<>();

    // constructor vacío

    public Usuario() {
    }

    // constructor lleno

    public Usuario(String Nombre, String Dirección, int Número, String Correo, String Contraseña) {
        this.Nombre = Nombre;
        this.Dirección = Dirección;
        this.Número = Número;
        this.Correo = Correo;
        this.Contraseña = Contraseña;
    }

    // getters y setters para cada atributo

    public ArrayList<Cuenta> getNúmeroCuenta() {
        return NúmeroCuenta;
    }

    public void setAsignarNúmero(Cuenta número) {
        NúmeroCuenta.add(número);
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDirección() {
        return Dirección;
    }

    public void setDirección(String Dirección) {
        this.Dirección = Dirección;
    }

    public int getNúmero() {
        return Número;
    }

    public void setNúmero(int Número) {
        this.Número = Número;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

}

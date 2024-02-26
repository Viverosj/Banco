package com.example;

import java.time.LocalDate;

public class Transacción {

    private double monto;
    private LocalDate fecha;
    private String remitente;
    private String destinatario;
    private Categoria categoria;

    public Transacción() {

    }

    public Transacción(double monto, LocalDate fecha, String remitente, String destinatario, Categoria categoria) {
        this.monto = monto;
        this.fecha = fecha;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return "Transacción{" + "monto=" + monto + ", fecha=" + fecha + ", remitente='" + remitente + '\''
                + ", destinatario='" + destinatario + '\'' + ", categoria=" + categoria + '}';
    }
}

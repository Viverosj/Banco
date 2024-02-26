package com.example;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cuenta {
    private ArrayList<Transacción> transacciones = new ArrayList<>();
    private static final double COSTO_TRANSFERENCIA = 200;
    private double saldo;
    private String numeroCuenta; // 10 números aleatoriamente
    private String propietario;

    public Cuenta() {

    }

    public Cuenta(double saldo, String numeroCuenta, String propietario) {
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.propietario = propietario;
    }

    // getters y setters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public void realizarTransferencia(double monto, Cuenta cuenta, Categoria categoria) {
        double montoTotal = monto + COSTO_TRANSFERENCIA;
        if (saldoSuficiente(montoTotal)) {
            saldo -= montoTotal;
            Transacción transacción = new Transacción(monto, LocalDate.now(), this.propietario, cuenta.propietario, categoria);
            transacciones.add(transacción);
            cuenta.agregarTransacción(transacción);
        } else {
            System.out.println("Saldo insuficiente para realizar la transferencia.");
        }
    }

    public void agregarTransacción(Transacción transacción) {
        transacciones.add(transacción);
    }

    private boolean saldoSuficiente(double monto) {
        return saldo >= monto;
    }

    public ArrayList<Transacción> getTransacciones() {
        return transacciones;
    }

    public double consultarSaldoEnPeriodo(LocalDate inicio, LocalDate fin) {
        double saldoEnPeriodo = saldo;
        for (Transacción transacción : transacciones) {
            LocalDate fechaTransacción = transacción.getFecha();
            if (fechaTransacción.isAfter(inicio) && fechaTransacción.isBefore(fin.plusDays(1))) {
                if (transacción.getCategoria() == Categoria.RETIRO) {
                    saldoEnPeriodo -= transacción.getMonto();
                } else {
                    saldoEnPeriodo += transacción.getMonto();
                }
            }
        }
        return saldoEnPeriodo;
    }
}




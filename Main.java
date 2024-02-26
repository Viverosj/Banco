package com.example;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // Crear una instancia de Banco
        Banco banco = new Banco();
        Cuenta cuenta1 = new Cuenta(200.000, "1234567890", "Propietario1"); // Inicialización correcta de la cuenta
        Cuenta cuenta2 = new Cuenta(2000, "0987654321", "Propietario2"); // Inicialización correcta de la cuenta

        banco.registroDeUsuario("Usuario1", "Dirección1", 123456, "correo1@example.com", "contraseña1");
        banco.registroDeUsuario("Usuario2", "Dirección2", 789012, "correo2@example.com", "contraseña2");
        banco.registroDeUsuario("Usuario3", "Dirección3", 1086921277, "correo3@example.com", "contraseña3");

        banco.actualizarDatos();
        banco.eliminarUsuario();
        banco.crearCuenta("Propietario3"); // Proporcionar un propietario válido
        banco.existeCuenta("1234567890"); // Proporcionar un número de cuenta existente
        banco.consultarSaldo();
        
        // Realizar transferencia entre cuentas existentes
        cuenta1.realizarTransferencia(100.000, cuenta2, Categoria.TRANSFERENCIA);

        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fin = LocalDate.of(2024, 1, 31);
        banco.consultarTransaccionesDeCuentaEnPeriodo("1234567890", inicio, fin);

        // Calcular porcentaje de gastos e ingresos en enero de 2024 y discriminar los gastos por categoría
        banco.calcularPorcentajeGastosIngresos(1, 2024);
    }
}


package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

public class Banco {

    private ArrayList<Cuenta> cuentas = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private Map<Integer, Cuenta> mapaCuentasPorUsuario = new HashMap<>();

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    // Método de registro de usuario

    public void registroDeUsuario(String nombre, String direccion, int numero, String correo, String contraseña) {
            String numeroCuenta = generarNumeroCuenta();
            Cuenta nuevaCuenta = new Cuenta(100.000, numeroCuenta, nombre);
            usuarios.add(new Usuario(nombre, direccion, numero, correo, contraseña));
            cuentas.add(nuevaCuenta);
        }

    // Método actualizar datos

    public void actualizarDatos() {
        int numeroIdentificacion = Integer
                .parseInt(JOptionPane.showInputDialog("Ingrese el número de identificación del usuario a actualizar:"));

        // Solicitar los nuevos datos al usuario
        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
        String direccion = JOptionPane.showInputDialog("Ingrese la nueva dirección:");
        String correo = JOptionPane.showInputDialog("Ingrese el nuevo correo:");
        String contraseña = JOptionPane.showInputDialog("Ingrese la nueva contraseña:");

        // Crear un objeto Usuario con los nuevos datos
        Usuario nuevosDatos = new Usuario(nombre, direccion, numeroIdentificacion, correo, contraseña);

        boolean encontrado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getNúmero() == numeroIdentificacion) {
                usuario.setNombre(nuevosDatos.getNombre());
                usuario.setDirección(nuevosDatos.getDirección());
                usuario.setCorreo(nuevosDatos.getCorreo());
                usuario.setContraseña(nuevosDatos.getContraseña());
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null,
                    "Los datos del usuario con número de identificación " + numeroIdentificacion
                            + " han sido actualizados.");
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    // Método eliminar usuario

    public void eliminarUsuario() {
        int numeroIdentificacion = Integer
                .parseInt(JOptionPane.showInputDialog("Ingrese el número de identificación del usuario a eliminar:"));
        boolean encontrado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getNúmero() == numeroIdentificacion) {
                usuarios.remove(usuario);
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null,
                    "El usuario con número de identificación " + numeroIdentificacion + " ha sido eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    // Método crear cuenta

    public void crearCuenta(String propietario) {
        String numeroCuenta = generarNumeroCuenta();
        Cuenta nuevaCuenta = new Cuenta(0.0, numeroCuenta, propietario);
        cuentas.add(nuevaCuenta);

        System.out.println("Se ha creado una nueva cuenta:");
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Propietario: " + propietario);
    }

    // Número de cuenta aleatorio

    public String generarNumeroCuenta() {
        Random random = new Random();
        StringBuilder numeroCuenta = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            numeroCuenta.append(random.nextInt(10));
        }
        return numeroCuenta.toString();
    }

    // Validacion de cuenta

    public boolean existeCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return true;
            }
        }
        return false;
    }

    // Método para consultar saldo

    public void consultarSaldo() {
        // Solicitar identificación y contraseña al usuario
        int identificacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su identificación:"));
        String contraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");

        // Buscar el usuario en la lista de usuarios
        Usuario usuarioEncontrado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getNúmero() == identificacion && usuario.getContraseña().equals(contraseña)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        // Si se encuentra el usuario, buscar su cuenta asociada y mostrar el saldo
        if (usuarioEncontrado != null) {
            Cuenta cuenta = mapaCuentasPorUsuario.get(identificacion);
            if (cuenta != null) {
                System.out.println("El saldo de la cuenta es: " + cuenta.getSaldo());
            } else {
                System.out.println("No se encontró una cuenta asociada a este usuario.");
            }
        } else {
            System.out.println("Identificación o contraseña incorrecta.");
        }
    }

    // Método para consultar transacciones de una cuenta en un período de tiempo discriminado
    public void consultarTransaccionesDeCuentaEnPeriodo(String numeroCuenta, LocalDate inicio, LocalDate fin) {
        // Buscar la cuenta por su número de cuenta
        Cuenta cuentaSeleccionada = null;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                cuentaSeleccionada = cuenta;
                break;
            }
        }

        if (cuentaSeleccionada == null) {
            System.out.println("La cuenta especificada no existe.");
            return;
        }

        // Filtrar las transacciones de la cuenta en el período especificado
        ArrayList<Transacción> transaccionesEnPeriodo = new ArrayList<>();
        for (Transacción transacción : cuentaSeleccionada.getTransacciones()) {
            LocalDate fechaTransacción = transacción.getFecha();
            if (!fechaTransacción.isBefore(inicio) && !fechaTransacción.isAfter(fin)) {
                transaccionesEnPeriodo.add(transacción);
            }
        }

        // Imprimir las transacciones encontradas
        if (transaccionesEnPeriodo.isEmpty()) {
            System.out.println("No se encontraron transacciones para la cuenta en el período especificado.");
        } else {
            System.out.println("Transacciones de la cuenta " + numeroCuenta + " en el período:");
            for (Transacción transacción : transaccionesEnPeriodo) {
                System.out.println(transacción);
            }
        }
    }

    // Método para calcular porcentaje de gastos e ingresos en un mes y discriminar los gastos por categoría
    public void calcularPorcentajeGastosIngresos(int mes, int año) {
        double totalIngresos = 0;
        double totalGastos = 0;
        Map<Categoria, Double> gastosPorCategoria = new HashMap<>();

        // Iterar sobre todas las transacciones del mes y año especificados
        for (Cuenta cuenta : cuentas) {
            for (Transacción transacción : cuenta.getTransacciones()) {
                if (transacción.getFecha().getMonthValue() == mes && transacción.getFecha().getYear() == año) {
                    if (transacción.getCategoria() == Categoria.DEPÓSITO) {
                        totalIngresos += transacción.getMonto();
                    } else if (transacción.getCategoria() == Categoria.RETIRO) {
                        totalGastos += transacción.getMonto();

                        // Discriminar los gastos por categoría
                        double montoGasto = gastosPorCategoria.getOrDefault(transacción.getCategoria(), 0.0);
                        montoGasto += transacción.getMonto();
                        gastosPorCategoria.put(transacción.getCategoria(), montoGasto);
                    }
                }
            }
        }

        // Calcular porcentaje de gastos e ingresos
        double totalTransacciones = totalIngresos + totalGastos;
        double porcentajeIngresos = (totalIngresos / totalTransacciones) * 100;
        double porcentajeGastos = (totalGastos / totalTransacciones) * 100;

        // Imprimir resultados
        System.out.println("Porcentaje de ingresos en el mes: " + porcentajeIngresos + "%");
        System.out.println("Porcentaje de gastos en el mes: " + porcentajeGastos + "%");

        // Imprimir gastos discriminados por categoría
        System.out.println("Gastos por categoría:");
        for (Map.Entry<Categoria, Double> entry : gastosPorCategoria.entrySet()) {
            Categoria categoria = entry.getKey();
            double montoGasto = entry.getValue();
            double porcentajeCategoria = (montoGasto / totalGastos) * 100;
            System.out.println(categoria + ": " + porcentajeCategoria + "%");
        }
    }
}

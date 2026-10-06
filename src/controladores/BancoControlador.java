package controladores;

import modelos.DatosCuenta;
import servicios.TransaccionServicio;
import vistas.BancoVista;

import servicios.CuentaServicio;
import modelos.TipoCuenta;

public class BancoControlador {

    private BancoVista vista;

    public BancoControlador(BancoVista vista) {
        this.vista = vista;
        this.vista.setGuardarCuentaClick(evento -> guardarCuenta());
        this.vista.setEliminarCuentaClick(evento -> eliminarCuenta());
        this.vista.setGuardarTransaccionClick(evento -> agregarTransaccion());
        mostrarCuentas();
        mostrarTransacciones();
    }

    private void mostrarCuentas() {
        vista.mostrarCuentas(CuentaServicio.getDatos(), CuentaServicio.getEncabezados());
    }

    private void mostrarTransacciones() {
        vista.mostrarTransacciones(TransaccionServicio.getDatos(), TransaccionServicio.getEncabezados());
    }

    private void guardarCuenta() {
        var tipo = vista.getTipoCuentaSeleccionada();
        var numero = vista.getNumero();
        var titular = vista.getTitular();
        var tasa = tipo == TipoCuenta.AHORROS || tipo == TipoCuenta.CREDITO ? vista.getTasaInteres() : 0;
        var sobregiro = tipo == TipoCuenta.CORRIENTE ? vista.getValor() : 0;
        var valorPrestado = tipo == TipoCuenta.CREDITO ? vista.getValor() : 0;
        var plazo = tipo == TipoCuenta.CREDITO ? vista.getPlazo() : 0;

        var cuentaAgregada = CuentaServicio.agregar(tipo,
                new DatosCuenta(titular, numero, tasa, sobregiro, plazo, valorPrestado));
        if (cuentaAgregada != null) {
            vista.setCuentaTransaccion(cuentaAgregada.toString());

            mostrarCuentas();

            vista.ocultarEdicionCuenta();
        } else {
            vista.mostrarMensaje("No se puede agregar la cuenta");
        }
    }

    private void eliminarCuenta() {
        if (vista.getFilaCuentaSeleccionada() >= 0) {
            if (vista.confirmar("¿Está seguro de eliminar la cuenta?")) {
                CuentaServicio.eliminar(vista.getFilaCuentaSeleccionada());
                vista.quitarCuentaTransaccion(vista.getFilaCuentaSeleccionada());
                mostrarCuentas();
            }
        } else {
            vista.mostrarMensaje("Debe seleccionar una cuenta");
        }
    }

    private void agregarTransaccion() {
        var cuenta = CuentaServicio.get(vista.getFilaCuentaTransaccion());
        if (cuenta == null) {
            return;
        }
        var tipo = vista.getTipoTransaccionSeleccionado();
        var valor = vista.getValorTransaccion();

        var transaccionAgregada = TransaccionServicio.agregar(cuenta, tipo, valor);
        if (transaccionAgregada != null) {
            mostrarTransacciones();
            vista.ocultarEdicionTransaccion();
        } else {
            vista.mostrarMensaje("No se puede realizar la transacción");
        }

    }
}

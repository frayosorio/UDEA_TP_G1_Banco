package controladores;

import vistas.BancoVista;

import servicios.CuentaServicio;
import modelos.TipoCuenta;

public class BancoControlador {

    private BancoVista vista;

    public BancoControlador(BancoVista vista) {
        this.vista = vista;
        this.vista.setGuardarCuentaClick(evento -> guardarCuenta());
        mostrarCuentas();
    }

    private void mostrarCuentas() {
        vista.mostrarCuentas(CuentaServicio.getDatos(), CuentaServicio.getEncabezados());
    }

    private void guardarCuenta() {
        var tipo = vista.getTipoCuentaSeleccionada();
        var numero = vista.getNumero();
        var titular = vista.getTitular();
        var tasa = tipo == TipoCuenta.AHORROS || tipo == TipoCuenta.CREDITO ? vista.getTasaInteres() : 0;
        var sobregiro = tipo == TipoCuenta.CORRIENTE ? vista.getValor() : 0;
        var valorPrestado = tipo == TipoCuenta.CREDITO ? vista.getValor() : 0;
        var plazo = tipo == TipoCuenta.CREDITO ? vista.getPlazo() : 0;

        CuentaServicio.agregar(tipo, titular, numero, tasa, sobregiro, plazo, valorPrestado);

        mostrarCuentas();

        vista.ocultarEdicionCuenta();

    }

}

package controladores;

import vistas.BancoVista;

import javax.swing.JOptionPane;

import servicios.CuentaServicio;
import modelos.TipoCuenta;

public class BancoControlador {

    private BancoVista vista;

    public BancoControlador(BancoVista vista) {
        this.vista = vista;

        mostrarCuentas();
    }

    private void mostrarCuentas() {
        vista.mostrarCuentas(CuentaServicio.getDatos(), CuentaServicio.getEncabezados());
    }

    private void guardarCuenta() {
        // leer los datos desde el formulario
        try {
            var tipo = vista.getTipoCuentaSeleccionada();
            var numero = vista.getTxtNumero().getText();
            var titular = vista.getTxtTitular().getText();
            var tasa = vista.getTxtTasaInteres().getText().isEmpty() ? 0
                    : Double.parseDouble(vista.getTxtTasaInteres().getText());
            var valor = vista.getTxtValor().getText().isEmpty() ? 0
                    : Double.parseDouble(vista.getTxtValor().getText());
            var plazo = vista.getTxtPlazo().getText().isEmpty() ? 0 : Integer.parseInt(vista.getTxtPlazo().getText());

            CuentaServicio.agregar(tipo, titular, numero,
                    tipo == TipoCuenta.AHORROS || tipo == TipoCuenta.CREDITO ? tasa : 0,
                    tipo == TipoCuenta.CORRIENTE ? valor : 0,
                    tipo == TipoCuenta.CREDITO ? plazo : 0,
                    tipo == TipoCuenta.CREDITO ? valor : 0);

            mostrarCuentas();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Por favor ingrese valores numéricos válidos.");
        }
    }
}

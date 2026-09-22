package servicios;

import java.util.ArrayList;
import java.util.List;

import modelos.Ahorros;
import modelos.Corriente;
import modelos.Credito;
import modelos.Cuenta;
import modelos.TipoCuenta;

public class CuentaServicio {

    private static final String[] encabezados={"Titular", "Número", "Parámetros Producto", "Saldos"};

    private static List<Cuenta> cuentas = new ArrayList<>();

    public static String[] getEncabezados() {
        return encabezados;
    }

    public static Cuenta agregar(TipoCuenta tipo,
            String titular,
            String numero,
            double tasaInteres,
            double sobregiro,
            int plazo,
            double valorPrestado) {
        Cuenta cuenta = null;

        switch (tipo) {
            case AHORROS:
                cuenta = new Ahorros(numero, titular, tasaInteres);
                break;
            case CORRIENTE:
                cuenta = new Corriente(numero, titular, sobregiro);
                break;
            case CREDITO:
                cuenta = new Credito(numero, titular, valorPrestado, tasaInteres, plazo);
                break;
        }
        if (cuenta != null)
            cuentas.add(cuenta);
        return cuenta;
    }

    public static String[][] getDatos(){
        String[][] datos=new String[cuentas.size()][encabezados.length];

        return datos;
    }

    

}

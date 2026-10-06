package fabricas;

import modelos.Cuenta;
import modelos.DatosCuenta;
import modelos.TipoCuenta;

public abstract class CuentaFactory {

    public abstract Cuenta crearCuenta(DatosCuenta datos);

    public static CuentaFactory getFactory(TipoCuenta tipo) {
        return switch (tipo) {
            case AHORROS -> new AhorrosFactory();
            case CORRIENTE -> new CorrienteFactory();
            case CREDITO -> new CreditoFactory();
        };
    }
}

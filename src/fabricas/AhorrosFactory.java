package fabricas;

import modelos.Ahorros;
import modelos.Cuenta;
import modelos.DatosCuenta;

public class AhorrosFactory extends CuentaFactory {
    @Override
    public Cuenta crearCuenta(DatosCuenta datos) {
        return new Ahorros(datos.numero(), datos.titular(), datos.tasaInteres());
    }
}

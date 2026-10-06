package fabricas;

import modelos.Corriente;
import modelos.Cuenta;
import modelos.DatosCuenta;

public class CorrienteFactory extends CuentaFactory {
    @Override
    public Cuenta crearCuenta(DatosCuenta datos) {
        return new Corriente(datos.numero(), datos.titular(), datos.sobregiro());
    }
}

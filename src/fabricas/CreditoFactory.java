package fabricas;

import modelos.Credito;
import modelos.Cuenta;
import modelos.DatosCuenta;

public class CreditoFactory extends CuentaFactory {
    @Override
    public Cuenta crearCuenta(DatosCuenta datos) {
        return new Credito(datos.numero(), datos.titular(), datos.valorPrestado(), datos.tasaInteres(), datos.plazo());
    }
}

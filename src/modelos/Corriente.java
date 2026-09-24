package modelos;

import java.text.DecimalFormat;

public class Corriente extends Cuenta {

    private double sobregiro;

    public Corriente(String numero, String titular, double sobregiro) {
        super(numero, titular);
        this.sobregiro = sobregiro;
    }

    public double getSobregiro() {
        return sobregiro;
    }

    @Override
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= getSaldo() + sobregiro) {
            setSaldo(getSaldo() - valor);
            return true;
        }
        return false;
    }

    @Override
    public String[] getDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "CORRIENTE",
                getTitular(),
                getNumero(),
                "Sobregiro $ " + df.format(sobregiro),
                "$ " + df.format(getSaldo())
        };
    }
}

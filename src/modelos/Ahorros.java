package modelos;

public class Ahorros extends Cuenta {

    private double tasa;

    public Ahorros(String numero, String titular, double tasa) {
        super(numero, titular);
        this.tasa = tasa;
    }

    public double getTasa() {
        return tasa;
    }

    @Override
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= getSaldo()) {
            setSaldo(getSaldo() - valor);
            return true;
        }
        return false;
    }

    public void abonarIntereses() {
        setSaldo(getSaldo() * (1 + tasa));
    }

}

package modelos;

public class Credito extends Cuenta {

    private double valorPrestado;
    private double tasaInteres;
    private int plazo;
    private double valorRetirado;

    public Credito(String numero, String titular,
            double valorPrestado, double tasaInteres, int plazo) {
        super(numero, titular);
        this.valorPrestado = valorPrestado;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
        valorRetirado = 0;
    }

    public double getValorPrestado() {
        return valorPrestado;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public int getPlazo() {
        return plazo;
    }

    public double getValorRetirado() {
        return valorRetirado;
    }

    public double getSaldoRetiro() {
        return valorPrestado - valorRetirado;
    }

    public double getSaldoDeuda() {
        return valorPrestado - getSaldo();
    }

    public double getCuota() {
        double factor = Math.pow(1 + tasaInteres / 100, plazo);
        return valorPrestado * factor * tasaInteres / (factor - 1);
    }

    @Override
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= getSaldoRetiro()) {
            valorRetirado += valor;
            return true;
        }
        return false;
    }

    public boolean pagar(double valor) {
        if (valor > 0 && getSaldoDeuda() > 0) {
            var intereses = getSaldoDeuda() * tasaInteres / 100;
            var abonoCapital = valor - intereses;
            if (abonoCapital <= getSaldoDeuda()) {
                return depositar(valor);
            }
        }
        return false;
    }

}

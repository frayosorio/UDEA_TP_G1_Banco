package modelos;

public abstract class Cuenta {
    private String numero;
    private String titular;
    private double saldo;

    public Cuenta() {
    }

    public Cuenta(String numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    // Metodo disponible solo para las clases HIJAs
    protected void setSaldo(double valor) {
        saldo = valor;
    }

    public abstract boolean retirar(double valor);

    public boolean depositar(double valor) {
        if (valor > 0) {
            setSaldo(saldo + valor);
            return true;
        }
        return false;
    }

}

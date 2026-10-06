package modelos;

public record DatosCuenta(String titular,
                          String numero,
                          double tasaInteres,
                          double sobregiro,
                          int plazo,
                          double valorPrestado) {

}

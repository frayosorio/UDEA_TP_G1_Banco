import vistas.BancoVista;
import controladores.BancoControlador;

public class App {
    public static void main(String[] args) throws Exception {
        var vista = new BancoVista();

        new BancoControlador(vista);

        vista.setVisible(true);
    }
}

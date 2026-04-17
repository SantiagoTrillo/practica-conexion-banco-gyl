package santiago;

import santiago.modelo.Banco;
import santiago.servicio.InicializadorBanco;
import santiago.ui.Menu;

public class Main {
    static void main(String[] args) {
        Banco banco = Banco.getInstancia();
        InicializadorBanco.inicializarBanco(banco);
        Menu menu = new Menu(banco);

        menu.mostrarMenuBanco();
    }
}
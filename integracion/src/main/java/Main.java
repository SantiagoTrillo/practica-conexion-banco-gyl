import java.util.Scanner;
import dominio.MediadorBancos;
import dominio.leo.modelo.DataBaseAdaptada;
import leo.ServicioDataBase.DataBase;
import leo.ServicioDataBase.DataBaseInjector;
import santi.modelo.Banco;
import santi.servicio.InicializadorBanco;
import santi.ui.Menu;

public class Main {
    private static final Scanner TECLADO = new Scanner(System.in);
    private static final DataBase BANCO_LEO_INIT = new DataBaseInjector();
    private static final Banco BANCO_SANTI_INIT = Banco.getInstancia();
    private static final MediadorBancos MEDIADOR = new MediadorBancos(BANCO_LEO_INIT, BANCO_SANTI_INIT);

    public static void main(String[] args) {
        boolean isRunning = true;

        InicializadorBanco.inicializarBanco(MEDIADOR.getBANCO_SANTI());

        while (isRunning) {
            try{
                System.out.println("""
                ¿A qué banco le gustaría ingresar? Ingrese 0 para salir
                1) Banco Leonardo
                2) Banco Santiago""");

                String input = TECLADO.nextLine();
                if (!input.matches("\\d+")) {
                    throw new IllegalArgumentException("(Main, main) la opción elegida no es un número");
                }
                int opcion = Integer.parseInt(input);
                MEDIADOR.sincronizarBancos();
                switch (opcion) {
                    case 1 -> {
                        new leo.App(MEDIADOR.getBANCO_LEO());
                        MEDIADOR.setBANCO_LEO(new DataBaseAdaptada(MEDIADOR.getBANCO_LEO()));
                    }
                    case 2 -> {
                        new Menu(MEDIADOR.getBANCO_SANTI()).mostrarMenuBanco();
                    }
                    case 0 -> isRunning = false;
                    default -> System.out.println("\nOpción inválida\n");
                }
            } catch (IllegalArgumentException datoInvalido) {
                System.out.println("Error de dato inválido: " + datoInvalido.getMessage());
            }
        }
    }


}

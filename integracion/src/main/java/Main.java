import java.util.ArrayList;
import java.util.Scanner;

import dominio.leo.AdaptadorABancoLeo;
import dominio.santiago.AdaptadorABancoSantiago;
import leo.AppCUI.CUI;
import leo.ServicioDataBase.DataBaseInjector;

import santiago.modelo.Banco;
import santiago.servicio.InicializadorBanco;
import santiago.ui.Menu;

import dominio.*;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        AdaptadorABancoLeo adaptadorLeo = new AdaptadorABancoLeo();
        AdaptadorABancoSantiago adaptadorSanti = new AdaptadorABancoSantiago();

        System.out.println("""
                ¿A qué banco le gustaría ingresar?
                1) Banco Leonardo
                2) Banco Santiago""");
        int opcion = teclado.nextInt();

        switch (opcion) {
            case 1 -> {

                DataBaseInjector objBancoLeo = new DataBaseInjector();
                Banco objBancoSantiago = Banco.getInstancia();

                CUI objMenu = new CUI();



                //objMenu.printDataList(objListaBanco);
            }
            case 2 -> {
                DataBaseInjector bancoLeo = new DataBaseInjector();
                Banco bancoSanti = Banco.getInstancia();
                Menu menu = new Menu(bancoSanti);

                InicializadorBanco.inicializarBanco(bancoSanti);
                ArrayList<santiago.modelo.Sucursal> sucursalesTraducidas = adaptadorSanti.adaptarSucursalesDeLeo(bancoLeo.getSucursalList());

                menu.mostrarMenuBanco();
            }
        }
    }
}
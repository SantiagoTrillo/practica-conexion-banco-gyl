import java.util.ArrayList;
import java.util.Scanner;
import dominio.*;
import leo.AppCUI.CUI;
import leo.AppCUI.UserLogin;
import leo.ServicioDataBase.DataBaseInjector;
import santiago.modelo.Banco;
import santiago.modelo.Cuenta;
import santiago.modelo.Sucursal;
import santiago.servicio.InicializadorBanco;
import santiago.ui.Menu;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        MediadorBancos mediador = new MediadorBancos();

        System.out.println("""
                ¿A qué banco le gustaría ingresar?
                1) Banco Leonardo
                2) Banco Santiago""");
        int opcion = teclado.nextInt();

        switch (opcion) {
            case 1 -> {
                DataBaseInjector objDB = new DataBaseInjector();
                CUI objCUI = new CUI();
                while (true) {
                    UserLogin objUserLogin = new UserLogin(objDB);

                    objCUI.setActiveUser(objUserLogin);
                    objCUI.setSucursalList(objDB.getSucursalList());

                    if (objUserLogin.isAdmin()) {
                        objCUI.mainMenu();
                    } else {
                        System.out.println("Solo administradores por el momento" + System.lineSeparator());
                    }
                }
            }
            case 2 -> {
                DataBaseInjector bancoLeo = new DataBaseInjector();
                Banco bancoSanti = Banco.getInstancia();
                Menu menu = new Menu(bancoSanti);

                InicializadorBanco.inicializarBanco(bancoSanti);
                ArrayList<santiago.modelo.Sucursal> sucursalesTraducidas = mediador.getAdapterSantiago().adaptarSucursalesDeLeo(bancoLeo.getSucursalList());
                for (santiago.modelo.Sucursal sucursalIterada : sucursalesTraducidas) {
                    Sucursal sucursalEspejo = bancoSanti.buscarSucursal(sucursalIterada.getNombre());

                    if (sucursalEspejo == null) {
                        bancoSanti.crearSucursal(sucursalIterada.getNombre());
                    } else {
                        for (Cuenta cuentaIterada : sucursalIterada.getCuentas()) {
                            if (sucursalEspejo.buscarCuentaSucursal(cuentaIterada.getEmail()) == null) {
                                Cuenta cuentaEspejo = sucursalEspejo.crearCuenta(cuentaIterada.getNombre(), cuentaIterada.getEmail(), cuentaIterada.getPin(), cuentaIterada.isAdmin(), cuentaIterada.getTipoCuenta());

                                if (cuentaEspejo != null && cuentaIterada.getSaldo() > 0) {
                                    cuentaEspejo.agregarSaldo(cuentaIterada.getSaldo());
                                }
                            }
                        }
                    }
                }
                menu.mostrarMenuBanco();
            }
        }
    }
}
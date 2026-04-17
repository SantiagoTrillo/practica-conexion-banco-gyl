public class Main {
    public static void main(String[] args) {

<<<<<<< Updated upstream
=======
        System.out.println("""
                ¿A qué banco le gustaría ingresar?
                1) Banco Leonardo
                2) Banco Santiago""");
        int opcion = teclado.nextInt();

        switch (opcion) {
            case 1 -> {
                DataBaseInjector objDB = new DataBaseInjector();
                Banco objBancoSanti = Banco.getInstancia();
                InicializadorBanco.inicializarBanco(objBancoSanti);

                objDB.getSucursalList().addAll(mediador.getAdapterLeo().adaptarSucursalesDeSanti(objBancoSanti.getSucursales()));
                CUI objCUI = new CUI();
                while (true) {
                    UserLogin objUserLogin = new UserLogin(objDB);
                    objCUI.setActiveUser(objUserLogin);
                    objCUI.setSucursalList(objDB.getSucursalList());

                    if (objUserLogin.isAdmin()) {
                        objCUI.mainMenu();
                    } else {
                        //System.out.println("Solo administradores por el momento" + System.lineSeparator());
                        System.out.println("No eres admin pero tienes acceso de prueba");
                        objCUI.mainMenu();
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
                    bancoSanti.crearSucursal(sucursalIterada.getNombre());
                }

                menu.mostrarMenuBanco();
            }
        }
>>>>>>> Stashed changes
    }
}
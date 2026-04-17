package leo;

import leo.AppCUI.CUI;
import leo.AppCUI.UserLogin;
import leo.ServicioDataBase.DataBaseInjector;

public class Main {
    public static void main(String[] args) {
        DataBaseInjector objDB = new DataBaseInjector();
        CUI objCUI = new CUI();
        while (true){
        UserLogin objUserLogin = new UserLogin(objDB);
        objCUI.setActiveUser(objUserLogin);

        if (objUserLogin.isAdmin()) {
            objCUI.mainMenu();
        } else {
            System.out.println("Solo administradores por el momento" + System.lineSeparator());
        }
}
    }
}
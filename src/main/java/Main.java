public class Main {
    public static void main(String[] args) {
        DataBase objDB = new DataBase();
        CUI objCUI = new CUI();
        objCUI.printLogo();
        UserLogin objUserLogin = new UserLogin(); // Se pueden cargar argumento username y password para hacer validaciones
        objCUI.setSucursal(objDB.getCentral());
        objCUI.mainMenu();


    }
}
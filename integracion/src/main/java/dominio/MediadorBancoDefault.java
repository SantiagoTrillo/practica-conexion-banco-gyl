package dominio;

public class MediadorBancoDefault implements MediadorBanco{
    private Banco bancoSanti;
    private DataBase bancoLeo;

    public MediadorBancoDefault(Banco bancoSanti, DataBase bancoLeo) {
        this.bancoSanti = bancoSanti;
        this.bancoLeo = bancoLeo;
    }

    @Override
    public void transferir(String transferente, String transferido, double monto) {

    }

    @Override
    public void buscarCuenta(String nombreBuscado) {

    }
}
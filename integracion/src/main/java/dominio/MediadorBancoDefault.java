package dominio;

import leo.ModeloBanco.Cliente.RegistroClientela;
import santiago.modelo.*;

import java.util.ArrayList;

public class MediadorBancoDefault implements MediadorBanco{
    private final Banco bancoSanti;
    private final ArrayList<leo.ModeloBanco.Sucursal> sucursalesLeo;

    public MediadorBancoDefault(Banco bancoSanti, ArrayList<leo.ModeloBanco.Sucursal> sucursalesLeo) {
        this.bancoSanti = bancoSanti;
        this.sucursalesLeo = sucursalesLeo;
    }

    @Override
    public void transferir(String transferente, String transferido, double monto) {

    }
}
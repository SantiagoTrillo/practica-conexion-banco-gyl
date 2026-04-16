package dominio;

import ModeloBanco.Cliente.RegistroClientela;
import modelo.*;
import ModeloBanco.*;

import java.util.ArrayList;

public class MediadorBancoDefault implements MediadorBanco{
    private final Banco bancoSanti;
    private final ArrayList<ModeloBanco.Sucursal> sucursalesLeo;

    public MediadorBancoDefault(Banco bancoSanti, ArrayList<ModeloBanco.Sucursal> sucursalesLeo) {
        this.bancoSanti = bancoSanti;
        this.sucursalesLeo = sucursalesLeo;
    }

    @Override
    public void transferir(String transferente, String transferido, double monto) {

    }

    @Override
    public Cuenta buscarCuenta(String emailBuscado) {
        Cuenta cuentaBuscada = null;

        if (emailBuscado != null && !emailBuscado.isBlank()) {
            for (modelo.Sucursal sucursalIterada : bancoSanti.getSucursales()) {
                cuentaBuscada = sucursalIterada.buscarCuentaSucursal(emailBuscado);
                if (cuentaBuscada != null) {
                    break;
                }
            }
            for (ModeloBanco.Sucursal sucursalIterada : sucursalesLeo) {
                cuentaBuscada = RegistroClientela.buscarUsername(emailBuscado);
                if (cuentaBuscada != null) {
                    break;
                }
            }
        }

        return cuentaBuscada;
    }
}
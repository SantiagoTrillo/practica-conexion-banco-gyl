package dominio;

import modelo.*;

public class AdaptadorBancoSantiago implements AdaptadorBanco {
    private final Banco banco;

    public AdaptadorBancoSantiago(Banco banco) {
        this.banco = banco;
    }

    @Override
    public Cuenta buscarCuenta(String emailBuscado) {
        Cuenta cuentaBuscada = null;

        for (Sucursal sucursalIterada : banco.getSucursales()) {
            Cuenta cuenta = sucursalIterada.buscarCuentaSucursal(emailBuscado);
            if (cuentaBuscada != null) {
                cuentaBuscada = cuenta;
            }
        }

        return cuentaBuscada;
    }
}
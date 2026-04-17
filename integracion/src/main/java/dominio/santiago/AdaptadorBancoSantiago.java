package dominio.santiago;

import santiago.modelo.*;

public class AdaptadorBancoSantiago implements InterfaceBancoSantiago {
    private final Banco banco;

    public AdaptadorBancoSantiago(Banco banco) {
        this.banco = banco;
    }

    @Override
    public Cuenta buscarCuenta(String busqueda) {
        Cuenta cuentaBuscada = null;

        for (Sucursal sucursalIterada : banco.getSucursales()) {
            Cuenta cuenta = sucursalIterada.buscarCuentaSucursal(busqueda);
            if (cuentaBuscada != null) {
                cuentaBuscada = cuenta;
            }
        }

        return cuentaBuscada;
    }
}
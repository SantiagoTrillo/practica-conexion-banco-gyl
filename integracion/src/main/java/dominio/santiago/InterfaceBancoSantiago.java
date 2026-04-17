package dominio.santiago;

import santiago.modelo.Cuenta;

public interface InterfaceBancoSantiago {
    Cuenta buscarCuenta(String emailBuscado);
}
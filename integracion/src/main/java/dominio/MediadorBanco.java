package dominio;

import santiago.modelo.Cuenta;

public interface MediadorBanco {
    void transferir(String transferente, String transferido, double monto);
    Cuenta buscarCuenta(String nombreBuscado);
}
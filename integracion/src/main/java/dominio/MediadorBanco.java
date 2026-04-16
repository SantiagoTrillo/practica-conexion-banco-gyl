package dominio;

public interface MediadorBanco {
    void transferir(String transferente, String transferido, double monto);
    void buscarCuenta(String nombreBuscado);
}
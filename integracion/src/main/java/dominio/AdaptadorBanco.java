package dominio;

import modelo.Cuenta;

public interface AdaptadorBanco {
    Cuenta buscarCuenta(String emailBuscado);
}
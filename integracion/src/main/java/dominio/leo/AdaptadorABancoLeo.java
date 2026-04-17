package dominio.leo;

import leo.ModeloBanco.Cliente.Cliente;
import leo.ModeloBanco.Transferencia.Transferencia;
import santiago.modelo.Cuenta;
import santiago.modelo.TipoTransaccion;
import santiago.modelo.Transaccion;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AdaptadorABancoLeo implements InterfaceABancoLeo {
    @Override
    public ArrayList<leo.ModeloBanco.Sucursal> traducirSucursalesDeSanti(ArrayList<santiago.modelo.Sucursal> sucursalesSanti) {
        ArrayList<leo.ModeloBanco.Sucursal> sucursalesTraducidas = new ArrayList<>();

        for (santiago.modelo.Sucursal sucursalIterada : sucursalesSanti) {
            leo.ModeloBanco.Sucursal sucursalTraducida = new leo.ModeloBanco.Sucursal(sucursalIterada.getNombre(), sucursalIterada.getNombre(), "Dato desconocido");

            for (Cuenta cuentaIterada : sucursalIterada.getCuentas()) {
                traducirCuentaACliente(cuentaIterada, sucursalTraducida);

                for (Transaccion transaccionIterada : cuentaIterada.getHistorialTransacciones()) {
                    traducirTransaccionATransferencia(transaccionIterada, sucursalTraducida);
                }
            }

            sucursalesTraducidas.add(sucursalTraducida);
        }
        return sucursalesTraducidas;
    }

    private Cliente traducirCuentaACliente(Cuenta cuentaATraducir, leo.ModeloBanco.Sucursal sucursalPortadora) {
        String usuarioTraducido = cuentaATraducir.getEmail();
        String contraseñaTraducida = String.valueOf(cuentaATraducir.getPin());
        String nombreTraducido = cuentaATraducir.getNombre();
        String tipoCuentaTraducido = switch (cuentaATraducir.getTipoCuenta()) {
            case CAJA_AHORRO -> "Ahorro";
            case CUENTA_CORRIENTE -> "Corriente";
            case BANCO_EXTERNO -> "Externa";
        };

        return new Cliente.Builder(usuarioTraducido, contraseñaTraducida, nombreTraducido, "", "Dato desconocido").tipoCuenta(tipoCuentaTraducido).permisos("").build(sucursalPortadora.registro);
    }

    private void traducirTransaccionATransferencia(Transaccion transaccionATraducir, leo.ModeloBanco.Sucursal sucursalPortadora) {
        Boolean tipoTranferenciaTraducido = null;
        Transferencia transferenciaTraducida;

        if (transaccionATraducir.getTipoTransaccion() == TipoTransaccion.DEPOSITO) {
            tipoTranferenciaTraducido = true;
        } else if (transaccionATraducir.getTipoTransaccion() == TipoTransaccion.RETIRO) {
            tipoTranferenciaTraducido = false;
        }

        Cliente origenTraducido = traducirCuentaACliente(transaccionATraducir.getOrigen(), sucursalPortadora);
        Cliente destinoTraducido = traducirCuentaACliente(transaccionATraducir.getDestino(), sucursalPortadora);

        if (tipoTranferenciaTraducido != null) {
            transferenciaTraducida = new Transferencia.Builder(tipoTranferenciaTraducido, destinoTraducido, BigDecimal.valueOf(transaccionATraducir.getMonto())).fecha("Dato desconocido").acreditar(sucursalPortadora.auditor);
        } else {
            transferenciaTraducida = new Transferencia.Builder(origenTraducido, destinoTraducido, BigDecimal.valueOf(transaccionATraducir.getMonto())).fecha("Dato desconocido").acreditar(sucursalPortadora.auditor);
        }
    }
}
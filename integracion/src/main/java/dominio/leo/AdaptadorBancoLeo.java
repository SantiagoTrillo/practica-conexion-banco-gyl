package dominio.leo;

import leo.ModeloBanco.Cliente.Cliente;
import santiago.modelo.Cuenta;
import santiago.modelo.Sucursal;
import santiago.modelo.TipoCuenta;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AdaptadorBancoLeo implements InterfaceBancoLeo {
    @Override
    public ArrayList<santiago.modelo.Sucursal> adaptarSucursalesDeLeo(ArrayList<leo.ModeloBanco.Sucursal> bancoLeo) {
        ArrayList<santiago.modelo.Sucursal> listaWrapper = new ArrayList<>();

        for (leo.ModeloBanco.Sucursal indexSucursal: bancoLeo){
            santiago.modelo.Sucursal wrapperSucursal
                    = new Sucursal(indexSucursal.getNombre());

            for (Cliente indexCliente: indexSucursal.registro.getClientelaMap().values()){

                Cuenta wrapperCliente = wrapperSucursal.crearCuenta(indexCliente.getNombre(),
                        indexCliente.getUsername(),
                        4040,
                        false,
                        TipoCuenta.BANCO_EXTERNO);

                if (indexCliente.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
                    wrapperCliente.agregarSaldo(
                            indexCliente.getSaldo().doubleValue()
                    );
                }
                //Logica de Historial Transacciones
                //No disponible entre bancos aún
            }

        }
        return listaWrapper;
    }

    }

package dominio;

import dominio.leo.AdaptadorABancoLeo;
import dominio.santi.AdaptadorABancoSantiago;
import leo.ServicioDataBase.DataBase;
import santi.modelo.Banco;
import santi.modelo.Cuenta;
import santi.modelo.Sucursal;

import java.util.ArrayList;

public class MediadorBancos {
    private static final AdaptadorABancoLeo adapterLeo = new AdaptadorABancoLeo();
    private static final AdaptadorABancoSantiago adapterSantiago = new AdaptadorABancoSantiago();
    private static final String PREFIJO_SUCURSAL_LEO_EN_SANTI = "[Banco Leo] ";
    private static final String PREFIJO_SUCURSAL_SANTI_EN_LEO = "[Banco Santi] ";
    private DataBase BANCO_LEO;
    private Banco BANCO_SANTI;

    public MediadorBancos(DataBase bancoLeo, Banco bancoSanti){
        this.BANCO_LEO = bancoLeo;
        this.BANCO_SANTI = bancoSanti;
    }

    public DataBase getBANCO_LEO() {
        return BANCO_LEO;
    }

    public Banco getBANCO_SANTI() {
        return BANCO_SANTI;
    }

    public void setBANCO_LEO(DataBase nuevoBancoLeo) {
        this.BANCO_LEO = nuevoBancoLeo;
    }

    public void setBANCO_SANTI(Banco nuevoBancoSanti) {
        this.BANCO_SANTI = nuevoBancoSanti;
    }

    public AdaptadorABancoLeo getAdapterLeo() {
        return adapterLeo;
    }

    public AdaptadorABancoSantiago getAdapterSantiago() {
        return adapterSantiago;
    }

    public void sincronizarBancos() {

        ArrayList<Sucursal> sucursalesAdaptadas = getAdapterSantiago().adaptarSucursalesDeLeo(BANCO_LEO.getSucursalList());
        agregarSucursalesAdaptadas(sucursalesAdaptadas);

        BANCO_LEO.getSucursalList().addAll(getAdapterLeo().adaptarSucursalesDeSanti(BANCO_SANTI.getSucursales()));
    }

    private void agregarSucursalesAdaptadas (ArrayList<santi.modelo.Sucursal> sucursalesAdaptadas) {
        for (santi.modelo.Sucursal sucursalIterada : sucursalesAdaptadas) {
            if (BANCO_SANTI.buscarSucursal(sucursalIterada.getNombre()) == null) {
                BANCO_SANTI.crearSucursal(sucursalIterada.getNombre());
            }

            Sucursal sucursalEspejo = BANCO_SANTI.buscarSucursal(sucursalIterada.getNombre());

            for (Cuenta cuentaIterada : sucursalIterada.getCuentas()) {
                if (sucursalEspejo.buscarCuentaSucursal(cuentaIterada.getEmail()) == null) {
                    Cuenta cuentaEspejo = sucursalEspejo.crearCuenta(cuentaIterada.getNombre(), cuentaIterada.getEmail(), cuentaIterada.getPin(), cuentaIterada.isAdmin(), cuentaIterada.getTipoCuenta());

                    if (cuentaEspejo != null && cuentaIterada.getSaldo() > 0) {
                        cuentaEspejo.agregarSaldo(cuentaIterada.getSaldo());
                    }
                }
            }
        }
    }
}
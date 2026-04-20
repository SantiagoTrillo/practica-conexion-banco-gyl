package dominio.leo.modelo;

import leo.ModeloBanco.Sucursal;
import leo.ModeloBanco.Transferencia.InterfaceTransferencia;
import leo.ModeloBanco.Transferencia.RegistroTransferencia;
import leo.ModeloBanco.Transferencia.Transferencia;
import leo.ServicioDataBase.DataBase;

import java.util.ArrayList;

public class DataBaseAdaptada extends DataBase {
    private ArrayList<Sucursal> sucursalesExternas = new ArrayList<>();
    private ArrayList<Sucursal> sucursalesCompleta;
    private InterfaceTransferencia transferenciasExternas = new RegistroTransferencia();
    private static final String PREFIJO_SUCURSAL_LEO_EN_SANTI = "[Banco Leo] ";
    private static final String PREFIJO_SUCURSAL_SANTI_EN_LEO = "[Banco Santi] ";

    public DataBaseAdaptada(DataBase dbAnterior){
        this.sucursalesCompleta=dbAnterior.getSucursalList();
        filterSucursal();
    }

    private void filterSucursal(){
        for (Sucursal iSucursal : sucursalesCompleta) {
            if (! iSucursal.getNombre().startsWith(PREFIJO_SUCURSAL_SANTI_EN_LEO)) {
                cargarSucursal(iSucursal);
            } else {
                sucursalesExternas.add(iSucursal);
                if (iSucursal.auditor.getLogSize() > 0) {
                    for (Transferencia iTransferencia : iSucursal.auditor.getAuditoria()){
                        transferenciasExternas.cargar(iTransferencia);
                    }
                }
            }
        }
    }

    public boolean checkTransferenciasExternas(){
        return transferenciasExternas.getLogSize() > 0;
    }

    public ArrayList<Sucursal> getSucursalesExternas() {
        return sucursalesExternas;
    }

    public ArrayList<Sucursal> getSucursalesCompleta() {
        return sucursalesCompleta;
    }

    public InterfaceTransferencia getTransferenciasExternas() {
        return transferenciasExternas;
    }
}

package AppCUI;

import ModeloBanco.Transferencia.InterfaceTransferencia;
import ModeloBanco.Transferencia.Transferencia;
import ModeloBanco.Cliente.Cliente;

import java.util.ArrayList;

public class AuditReader {
    InterfaceTransferencia source;
    public AuditReader(InterfaceTransferencia source){
        this.source = source;
    }

    public String logToString(Transferencia source){
        switch sourc
        return source.getEmisor().getNombreCompleto() + " -> "
                + source.getReceptor().getNombreCompleto() + "| $"
                + source.getMonto().toString() + " | "
                + source.getFecha();
    }

    public ArrayList<String> auditToListString(){
        ArrayList<Transferencia> auditList = source.getAuditoria();
        ArrayList<String> lista = new ArrayList<>();
        auditList.forEach(c -> lista.add(logToString(c)));
        return lista;
    }

    public int getDataMapSize(){
        return source.getLogSize();
    }
}

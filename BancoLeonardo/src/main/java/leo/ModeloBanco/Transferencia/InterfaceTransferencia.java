package leo.ModeloBanco.Transferencia;

import java.util.ArrayList;

public interface InterfaceTransferencia {
    boolean cargar(Transferencia log);

    ArrayList<Transferencia> getAuditoria();

    int getLogSize();
}

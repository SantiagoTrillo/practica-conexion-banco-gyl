package leo.AppCUI;

import leo.ModeloBanco.Cliente.Cliente;
import leo.ModeloBanco.Cliente.InterfaceClientela;
import leo.ModeloBanco.Sucursal;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SucursalReader {
    ArrayList<Sucursal> source;
    public SucursalReader(ArrayList<Sucursal> source){
        this.source = source;
    }

    public String sucursalToString(Sucursal source){
        return "Sucursal: " + source.getNombre() + " | " + source.getDireccion() + " | Inaugurado: " + source.getInauguracion();
    }

    public ArrayList<String> SucursalesToListString(){
        ArrayList<String> lista = new ArrayList<>();
        source.forEach(s -> lista.add(sucursalToString(s)));
        return lista;
    }

    public int getListSize(){
        return source.size();
    }
}

package leo.ServicioDataBase;

import leo.ModeloBanco.Sucursal;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<Sucursal> sucursalList = new ArrayList<>();

    public ArrayList<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public Sucursal getSucursalList(int index){
        return sucursalList.get(index);
    }

    public void setSucursalList(ArrayList<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }

    public void cargarSucursal(Sucursal sucursal) {sucursalList.add(sucursal);}
}

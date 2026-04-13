

public class Sucursal {
    private String nombre;
    private String direccion;
    private String inauguracion;

    public InterfaceClientela registro = new RegistroClientela();

    public Sucursal(String nombre, String direccion, String inauguracion, InterfaceClientela registro) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.inauguracion = inauguracion;
        this.registro = registro;
    }

}

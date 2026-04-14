import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//Esta clase simula datos persistentes históricos a la fecha de inicializar el proyecto
//Todas estas inyecciones son hard-codeadas a modo de ejemplo

public class DataBase {
    private List<Sucursal> sucursalList;
    public DataBase(){
        sucursalList = new ArrayList<>();
        sucursalList.add(cargarCentral());
        //cargarSucursales();
        //cargarAdmin();
        cargarClientes(sucursalList.get(0).registro);
    }

    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public Sucursal getCentral(){
        return sucursalList.get(0);
    }

    private Sucursal cargarCentral(){
        return new Sucursal("Central","Calle Central 5","14-04-1998", new RegistroClientela());
    }
    private void cargarSucursales(){
        sucursalList.add(cargarCentral());
    }

    private void cargarClientes(InterfaceClientela registro) {
        new Cliente.Builder("juanperez27", "juanelmaskpo","Juan","Perez","Calle Falsa 123")
                .build(registro);
        new Cliente.Builder("ahorrista", "colchon","Joni","Gasto", "Calle Barata 123")
                .tipoCuenta("Ahorro")
                .build(registro);
        new Cliente.Builder("cashdollar", "$$$$$$$$","Ricky", "Ricon", "Puerto Madero 1234")
                .tipoCuenta("Black")
                .saldo(BigDecimal.valueOf(999999999))
                .build(registro);
        new Cliente.Builder("islascaimansa", "islascaimansa","Islas Caiman", "S.A.", "Islas Caiman, Islas Caiman")
                .tipoCuenta("Empresa")
                .saldo(BigDecimal.valueOf(1000))
                .build(registro);
    }
}



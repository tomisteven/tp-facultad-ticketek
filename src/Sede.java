import java.util.Collections;
import java.util.List;

public abstract class Sede {
    protected String nombre;
    protected String direccion;
    protected int capacidadMaxima;

    public Sede(String nombre, String direccion, int capacidadMaxima) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidadMaxima = capacidadMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    /** Lista de sectores; para sedes no numeradas, vacía */
    public List<Sector> getSectores() {
        return Collections.emptyList();
    }



    public abstract boolean esNumerada();

    public double calcularPrecio(double precioBase, String tipoSector) {

        return 0;
    }
}

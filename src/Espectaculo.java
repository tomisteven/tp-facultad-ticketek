import java.util.ArrayList;
import java.util.List;

/**
 * Espect�culo, contiene lista de funciones.
 */
public class Espectaculo {
    private String nombre;
    private List<Funcion> funciones;

    public Espectaculo(String nombre) {
        this.nombre = nombre;
        this.funciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarFuncion(Funcion f) {
        this.funciones.add(f);
    }

    public Number ofFunciones() {
        return funciones.size();
    }
}


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Una funci�n de un espect�culo en una sede, fecha y precio base.
 */
public class Funcion {
    private int id;
    private Sede sede;
    private Date fecha;
    private double precioBase;
    private List<Entrada> entradas;

    public Funcion(int id, Sede sede, Date fecha, double precioBase) {
        this.id = id;
        this.sede = sede;
        this.fecha = fecha;
        this.precioBase = precioBase;
        this.entradas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Sede getSede() {
        return sede;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public void agregarEntrada(Entrada e) {
        if (e == null) {
            throw new IllegalArgumentException("Entrada nula");
        }
        entradas.add(e);
    }
}

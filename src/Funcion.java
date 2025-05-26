

import java.util.Date;

/**
 * Una función de un espectáculo en una sede, fecha y precio base.
 */
public class Funcion {
    private int id;
    private Sede sede;
    private Date fecha;
    private double precioBase;

    public Funcion(int id, Sede sede, Date fecha, double precioBase) {
        this.id = id;
        this.sede = sede;
        this.fecha = fecha;
        this.precioBase = precioBase;
    }

    public int getId() { return id; }
    public Sede getSede() { return sede; }
    public Date getFecha() { return fecha; }
    public double getPrecioBase() { return precioBase; }

    public void setSede(Sede sede) { this.sede = sede; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }
}

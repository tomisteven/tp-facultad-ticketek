
import java.util.List;

public class Teatro extends Sede {
    private int asientosPorFila;
    private List<Sector> sectores;
    private int[] porcentajeAdicional;

    public Teatro(String nombre, String direccion, int capacidadMaxima,
            int asientosPorFila, List<Sector> sectores, int[] porcentajeAdicional) {
        super(nombre, direccion, capacidadMaxima);
        this.asientosPorFila = asientosPorFila;
        this.sectores = sectores;
        this.porcentajeAdicional = porcentajeAdicional;
    }

    @Override
    public boolean esNumerada() {
        return true;
    }

     @Override
    public List<Sector> getSectores() {
        return sectores;
    }

    @Override
    public double calcularPrecio(double precioBase, String tipoSector) {
        // l�gica de recargos seg�n el sector...
        return 0;
    }

    // getters/setters�
}

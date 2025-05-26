


import java.util.List;

public class MiniEstadio extends Sede {
    private int asientosPorFila;
    private int puestosMerchandising;
    private int puestosComida;
    private double cargoConsumoLibre;
    private List<Sector> sectores;
    private int[] porcentajeAdicional;

    public MiniEstadio(String nombre, String direccion, int capacidadMaxima,
                       int asientosPorFila, int puestosMerchandising, double cargoConsumoLibre,
                       List<Sector> sectores, int[] porcentajeAdicional) {
        super(nombre, direccion, capacidadMaxima);
        this.asientosPorFila = asientosPorFila;
        this.puestosMerchandising = puestosMerchandising;
        this.cargoConsumoLibre = cargoConsumoLibre;
        this.sectores = sectores;
        this.porcentajeAdicional = porcentajeAdicional;
    }

    @Override
    public boolean esNumerada() { return true; }

    @Override
    public double calcularPrecio(double precioBase, String tipoSector) {
        // recargo + consumición libre…
        return 0;
    }

    // getters/setters…
}

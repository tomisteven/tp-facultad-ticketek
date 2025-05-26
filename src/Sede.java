


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

    public abstract boolean esNumerada();

	public double calcularPrecio(double precioBase, String tipoSector) {
		// TODO Auto-generated method stub
		return 0;
	}
}

public class Entrada implements IEntrada
{
    private String sector;
    private int fila;
    private int asiento;
    private double precio;
    private boolean vendida;

    public Entrada( String sector, int fila, int asiento, double precio) {
        
        this.sector = sector;
        this.fila = fila;
        this.asiento = asiento;
        this.precio = precio;
        this.vendida = false;
    }
    @Override public String getCodigo() {
    	
		return sector;  }
    @Override public double getPrecioFinal() {
		return asiento; }
    
    public boolean estaVendida() {
        return vendida;
    }

    public void marcarComoVendida() {
        this.vendida = true;
    }

    // Getters y setters...
}

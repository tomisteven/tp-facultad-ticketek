// paquete: ar.edu.ungs.prog2.ticketek.model

/**
 * Sector numerado dentro de un Teatro o MiniEstadio.
 */
public class Sector {
    private String tipo;
    private int fila;
    private int asientos;

    public Sector(String tipo, int fila, int asientos) {
        this.tipo = tipo;
        this.fila = fila;
        this.asientos = asientos;
    }

    public String getTipo() { return tipo; }
    public int getFila() { return fila; }
    public int getAsientos() { return asientos; }

    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setFila(int fila) { this.fila = fila; }
    public void setAsientos(int asientos) { this.asientos = asientos; }
}

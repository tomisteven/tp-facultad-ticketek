
public class Ubicacion {
    private Sector sector;
    private int numeroSector;
    private int fila;
    private int asiento;

    public Ubicacion(Sector sector, int numeroSector, int fila, int asiento) {
        this.sector = sector;
        this.numeroSector = numeroSector;
        this.fila = fila;
        this.asiento = asiento;
    }

    public Sector getSector() { return sector; }
    public int getNumeroSector() { return numeroSector; }
    public int getFila() { return fila; }
    public int getAsiento() { return asiento; }

    public void setSector(Sector sector) { this.sector = sector; }
    public void setNumeroSector(int numeroSector) { this.numeroSector = numeroSector; }
    public void setFila(int fila) { this.fila = fila; }
    public void setAsiento(int asiento) { this.asiento = asiento; }
}

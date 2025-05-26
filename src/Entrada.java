public class Entrada implements IEntrada {
  private String sector;
  private int fila;
  private int asiento;
  private double precio;
  private boolean vendida;

  public Entrada(String sector, int fila, int asiento, double precio) {
    this.sector = sector;
    this.fila = fila;
    this.asiento = asiento;
    this.precio = precio;
    this.vendida = false;
  }

  @Override
  public String getCodigo() {
    return String.format("%s-F%d-A%d", sector, fila, asiento);
  }

  @Override
  public double getPrecioFinal() {
    return precio;
  }

  public String getSector() {
    return sector;
  }

  public int getFila() {
    return fila;
  }

  public int getAsiento() {
    return asiento;
  }

  public double getPrecio() {
    return precio;
  }

  @Override
  public double precio() {
    return precio;
  }

  public boolean estaVendida() {
    return vendida;
  }

  public void marcarComoVendida() {
    this.vendida = true;
  }
}
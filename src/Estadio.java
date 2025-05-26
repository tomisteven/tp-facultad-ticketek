public class Estadio extends Sede {

    public Estadio(String nombre, String direccion, int capacidadMaxima) {
        super(nombre, direccion, capacidadMaxima);
    }

    @Override
    public boolean esNumerada() {
        return false;
    }
}



import java.util.ArrayList;
import java.util.List;

/**
 * Usuario del sistema (equivalente a tu Cliente, 
 * pero alineado con la interfaz ITicketek).
 */
public class Usuario {
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private List<Entrada> entradas;

    public Usuario(String email, String nombre, String apellido, String contrasenia) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.entradas = new ArrayList<>();
    }

    public String getEmail() { return email; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getContrasenia() { return contrasenia; }
    public List<Entrada> getEntradas() { return entradas; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public void agregarEntrada(Entrada e) {
        this.entradas.add(e);
    }
}

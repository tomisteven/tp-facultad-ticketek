
import java.util.*;

/**
 * Implementaci�n esquel�tica de ITicketek.
 * Aqu� definimos las colecciones internas y todos los m�todos
 * con stubs que luego iremos completando uno a uno.
 */
public class Ticketek implements ITicketek {

    private final Map<String, Sede> sedes;
    private final Map<String, Usuario> usuarios;
    private final Map<String, Espectaculo> espectaculos;

    public Ticketek() {
        this.sedes = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.espectaculos = new HashMap<>();
    }

    // 1) Registro de sedes no numeradas (Estadios)
    @Override
    public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
        if (nombre == null || nombre.isBlank() || capacidadMaxima <= 0)
            // throw new DatosInvalidosException("Datos inv�lidos para la sede");
            if (sedes.containsKey(nombre))
                // throw new EntidadYaRegistradaException("Sede ya registrada: " + nombre);
                sedes.put(nombre, new Estadio(nombre, direccion, capacidadMaxima));
    }

    // 1) Registro de sedes numeradas sin puestos de venta (Teatros)
    @Override
    public void registrarSede(String nombre,
            String direccion,
            int capacidadMaxima,
            int asientosPorFila,
            String[] sectores,
            int[] capacidad,
            int[] porcentajeAdicional) {
        if (nombre == null || nombre.isBlank() || capacidadMaxima <= 0)
            // throw new DatosInvalidosException("Datos inv�lidos para la sede");
            if (sedes.containsKey(nombre))
                // throw new EntidadYaRegistradaException("Sede ya registrada: " + nombre);
                sedes.put(
                        nombre,
                        new Teatro(
                                nombre,
                                direccion,
                                capacidadMaxima,
                                asientosPorFila,
                                Arrays.asList(sectores).stream()
                                        .map(sec -> new Sector(sec, asientosPorFila, 0))
                                        .toList(),
                                porcentajeAdicional));
    }

    // 1) Registro de mini-estadios (numeradas + puestos de venta)
    @Override
    public void registrarSede(String nombre,
            String direccion,
            int capacidadMaxima,
            int asientosPorFila,
            int cantidadPuestos,
            double precioConsumicion,
            String[] sectores,
            int[] capacidad,
            int[] porcentajeAdicional) {
        if (nombre == null || nombre.isBlank() || capacidadMaxima <= 0)
            // throw new DatosInvalidosException("Datos inv�lidos para la sede");
            if (sedes.containsKey(nombre))
                // throw new EntidadYaRegistradaException("Sede ya registrada: " + nombre);
                sedes.put(
                        nombre,
                        new MiniEstadio(
                                nombre,
                                direccion,
                                capacidadMaxima,
                                asientosPorFila,
                                cantidadPuestos,
                                precioConsumicion,
                                Arrays.asList(sectores).stream()
                                        .map(sec -> new Sector(sec, asientosPorFila, 0))
                                        .toList(),
                                porcentajeAdicional));
    }

    // 2) Registro de usuario
    @Override
    public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {

    }

    // 3) Registro de espect�culo
    @Override
    public void registrarEspectaculo(String nombre) {

    }

    // 14) Agregar funci�n a un espect�culo
    @Override
    public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
        //  Implementar validaciones y l�gica
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 4) Venta de entradas en sedes no numeradas
    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo,
            String fecha,
            String email,
            String contrasenia,
            int cantidadEntradas) {
        //  Implementar validaciones y l�gica
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 4) Venta de entradas en sedes numeradas
    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo,
            String fecha,
            String email,
            String contrasenia,
            String sector,
            int[] asientos) {
        //  Implementar validaciones y l�gica
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 5) Listar funciones de un espect�culo
    @Override
    public String listarFunciones(String nombreEspectaculo) {
        //  Implementar l�gica de listado
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 15) Listar todas las entradas vendidas para un espect�culo
    @Override
    public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
        //Implementar logica
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 6) Listar entradas futuras de un usuario
    @Override
    public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
        // implementar l�gica
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 7) Listar todas las entradas de un usuario
    @Override
    public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
        // Implementar l�gica
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 8) Anular entrada
    @Override
    public boolean anularEntrada(IEntrada entrada, String contrasenia) {
        //  Implementar l�gica
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 9) Cambiar entrada (con sector y asiento)
    @Override
    public IEntrada cambiarEntrada(IEntrada entrada,
            String contrasenia,
            String fecha,
            String sector,
            int asiento) {
        //  Implementar l�gica
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 9) Cambiar entrada (solo fecha)
    @Override
    public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
        // Implementar l�gica
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 11) Costo de entrada en estadio
    @Override
    public double costoEntrada(String nombreEspectaculo, String fecha) {
        // Implementar l�gica de c�lculo
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 11) Costo de entrada en sede numerada
    @Override
    public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
        //  Implementar l�gica de c�lculo
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 12) Total recaudado por espect�culo
    @Override
    public double totalRecaudado(String nombreEspectaculo) {
        return 0;
    }

    @Override
    public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {

        return 0;
    }

}
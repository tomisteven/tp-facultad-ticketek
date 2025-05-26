
import java.util.List;

/**
 * Interfaz del sistema Ticketek seg�n el enunciado.
 */
public interface ITicketek {

    // 1) Registra las sedes que no tienen asientos (Estadios)
    void registrarSede(String nombre, String direccion, int capacidadMaxima);

    void getSedes();

    // 1) Registra las sedes con asientos y sin puestos de venta (Teatros)
    void registrarSede(String nombre,
            String direccion,
            int capacidadMaxima,
            int asientosPorFila,
            String[] sectores,
            int[] capacidad,
            int[] porcentajeAdicional);

    // 1) Registra las sedes con asientos y puestos de venta (MiniEstadios)
    void registrarSede(String nombre,
            String direccion,
            int capacidadMaxima,
            int asientosPorFila,
            int cantidadPuestos,
            double precioConsumicion,
            String[] sectores,
            int[] capacidad,
            int[] porcentajeAdicional);

    // 2) Registrar un nuevo usuario en el sistema
    void registrarUsuario(String email, String nombre, String apellido, String contrasenia);

    // 3) Registrar un nuevo espect�culo en el sistema
    void registrarEspectaculo(String nombre);

    // 14) Agregar una funci�n nueva a un espect�culo ya registrado
    void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase);

    // 4) Vende entradas para sedes no numeradas
    List<IEntrada> venderEntrada(String nombreEspectaculo,
            String fecha,
            String email,
            String contrasenia,
            int cantidadEntradas);

    // 4) Vende entradas para sedes numeradas
    List<IEntrada> venderEntrada(String nombreEspectaculo,
            String fecha,
            String email,
            String contrasenia,
            String sector,
            int[] asientos);

    // 5) Listar las funciones de un espect�culo
    String listarFunciones(String nombreEspectaculo);

    // 15) Listar todas las entradas vendidas de un espect�culo
    List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo);

    // 6) Listar todas las entradas futuras de un usuario
    List<IEntrada> listarEntradasFuturas(String email, String contrasenia);

    // 7) Listar todas las entradas (pasadas y futuras) de un usuario
    List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia);

    // 8) Anular una entrada comprada por el usuario
    boolean anularEntrada(IEntrada entrada, String contrasenia);

    // 9) Cambiar una entrada (cambia fecha y ubicaci�n)
    IEntrada cambiarEntrada(IEntrada entrada,
            String contrasenia,
            String fecha,
            String sector,
            int asiento);

    // 9) Cambiar una entrada (solo fecha, misma ubicaci�n)
    IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha);

    // 11) Costo de una entrada en un estadio (no numerada)
    double costoEntrada(String nombreEspectaculo, String fecha);

    // 11) Costo de una entrada en una sede numerada
    double costoEntrada(String nombreEspectaculo, String fecha, String sector);

    // 12) Total recaudado hasta el momento por un espect�culo
    double totalRecaudado(String nombreEspectaculo);

    // 13) Total recaudado por una sede en un espect�culo
    double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede);
}

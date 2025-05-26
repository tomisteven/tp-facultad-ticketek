
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
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

    public void getSedes() {
        // Muestra las sedes registradas
        for (String nombre : sedes.keySet()) {
            System.out.println("Sede: " + nombre + " - " + sedes.get(nombre));
        }
    }

    // 1) Registro de sedes no numeradas (Estadios)
@Override
public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
    if (nombre == null || nombre.isBlank() ||
        direccion == null || direccion.isBlank() ||
        capacidadMaxima <= 0) {
        throw new DatosInvalidosException("Datos inválidos para la sede.");
    }
    if (sedes.containsKey(nombre)) {
        throw new EntidadYaRegistradaException("Sede ya registrada: " + nombre);
    }
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
    if (nombre == null || nombre.isBlank() ||
        direccion == null || direccion.isBlank() ||
        capacidadMaxima <= 0 ||
        asientosPorFila <= 0 ||
        sectores == null ||
        capacidad == null ||
        porcentajeAdicional == null ||
        sectores.length != capacidad.length ||
        sectores.length != porcentajeAdicional.length) {
        throw new DatosInvalidosException("Datos inválidos para la sede.");
    }
    if (sedes.containsKey(nombre)) {
        throw new EntidadYaRegistradaException("Sede ya registrada: " + nombre);
    }

    List<Sector> listaSectores = new ArrayList<>();
    for (int i = 0; i < sectores.length; i++) {
        // tipo = sectores[i], filas = capacidad[i], porcentaje = porcentajeAdicional[i]
        listaSectores.add(new Sector(sectores[i], asientosPorFila, capacidad[i]));
    }
    Teatro teatro = new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila, listaSectores, porcentajeAdicional);
    sedes.put(nombre, teatro);
}

// 1) Registro de sedes numeradas con puestos de venta (MiniEstadios)
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
    if (nombre == null || nombre.isBlank() ||
        direccion == null || direccion.isBlank() ||
        capacidadMaxima <= 0 ||
        asientosPorFila <= 0 ||
        cantidadPuestos < 0 ||
        precioConsumicion < 0 ||
        sectores == null ||
        capacidad == null ||
        porcentajeAdicional == null ||
        sectores.length != capacidad.length ||
        sectores.length != porcentajeAdicional.length) {
        throw new DatosInvalidosException("Datos inválidos para la sede mini: " + nombre);
    }
    if (sedes.containsKey(nombre)) {
        throw new EntidadYaRegistradaException("Sede ya registrada: " + nombre);
    }

    List<Sector> listaSectores = new ArrayList<>();
    for (int i = 0; i < sectores.length; i++) {
        listaSectores.add(new Sector(sectores[i], asientosPorFila, capacidad[i]));
    }
    MiniEstadio mini = new MiniEstadio(
        nombre,
        direccion,
        capacidadMaxima,
        asientosPorFila,
        cantidadPuestos,
        precioConsumicion,
        listaSectores,
        porcentajeAdicional
    );
    sedes.put(nombre, mini);
}


    // 2) Registro de usuario
    @Override
    public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {
        // 1) Validaciones básicas
        if (email == null || email.isBlank()
                || nombre == null || nombre.isBlank()
                || apellido == null || apellido.isBlank()
                || contrasenia == null || contrasenia.isBlank()) {
            throw new DatosInvalidosException("Datos inválidos para el usuario.");
        }

        // 2) Verificar no duplicado
        if (usuarios.containsKey(email)) {
            throw new EntidadYaRegistradaException("Usuario ya registrado: " + email);
        }

        // 3) Crear y registrar
        Usuario usuario = new Usuario(email, nombre, apellido, contrasenia);
        usuarios.put(email, usuario);
        System.out.println("Usuario registrado exitosamente: " + email);
    }

    // 3) Registro de espectaculo
    @Override
    public void registrarEspectaculo(String nombre) {
        if (nombre == null || nombre.isBlank() || nombre.length() < 3) {
            throw new DatosInvalidosException("Datos inválidos para el espectáculo.");
        }
        if (espectaculos.containsKey(nombre)) {
            throw new IllegalArgumentException("El espectáculo ya está registrado");
        }
        espectaculos.put(nombre, new Espectaculo(nombre));
    }

    // 14) Agregar funciion a espectaculo
    public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
        // 1) Validaciones básicas…
        if (nombreEspectaculo == null || nombreEspectaculo.isBlank()
                || fecha == null || fecha.isBlank()
                || sede == null || sede.isBlank()
                || precioBase <= 0) {
            throw new DatosInvalidosException("Datos inválidos para la función.");
        }
        if (!espectaculos.containsKey(nombreEspectaculo)) {
            throw new IllegalArgumentException("El espectáculo no está registrado: " + nombreEspectaculo);
        }
        if (!sedes.containsKey(sede)) {
            throw new IllegalArgumentException("La sede no está registrada: " + sede);
        }

        // 2) Parsear fecha dd/MM/yy → LocalDate
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/")
                .appendValueReduced(ChronoField.YEAR, 2, 2, 2000)
                .toFormatter();
        LocalDate nuevoDia;
        try {
            nuevoDia = LocalDate.parse(fecha, formatter);
        } catch (DateTimeParseException ex) {
            throw new DatosInvalidosException("Fecha inválida: " + fecha);
        }

        Espectaculo espec = espectaculos.get(nombreEspectaculo);

        // 3) Validar que no haya otra función en la misma fecha
        boolean yaExiste = espec.getFunciones().stream()
                .anyMatch(f -> {
                    LocalDate diaExistente = f.getFecha()
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    return diaExistente.equals(nuevoDia);
                });
        if (yaExiste) {
            System.err.println("Ya existe una función para esa fecha: " + fecha);
            throw new IllegalArgumentException("Ya existe una función para esa fecha: " + fecha);
        }

        // 4) Construir Date y agregar función
        Date fechaObj = Date.from(nuevoDia.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Sede sedeObj = sedes.get(sede);
        int nuevoId = espec.getFunciones().size() + 1;
        Funcion funcion = new Funcion(nuevoId, sedeObj, fechaObj, precioBase);
        espec.agregarFuncion(funcion);
    }

    // 4) Venta de entradas en sedes no numeradas
    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo,
            String fecha,
            String email,
            String contrasenia,
            int cantidadEntradas) {
        // --- Validaciones básicas ---
        if (nombreEspectaculo == null || nombreEspectaculo.isBlank()
                || fecha == null || fecha.isBlank()
                || email == null || email.isBlank()
                || contrasenia == null || contrasenia.isBlank()
                || cantidadEntradas <= 0) {
            throw new DatosInvalidosException("Parámetros inválidos para venta de entradas.");
        }
        // --- Autenticación ---
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            throw new EntidadNoEncontradaException("Usuario no registrado: " + email);
        }
        if (!usuario.getContrasenia().equals(contrasenia)) {
            System.err.println("Contraseña incorrecta para: " + email);
            throw new CredencialesInvalidasException("Contraseña incorrecta para: " + email);
        }
        // --- Espectáculo ---
        Espectaculo esp = espectaculos.get(nombreEspectaculo);
        if (esp == null) {
            throw new EntidadNoEncontradaException("Espectáculo no registrado: " + nombreEspectaculo);
        }
        // --- Parseo de fecha dd/MM/yy ---
        var fmt = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/")
                .appendValueReduced(ChronoField.YEAR, 2, 2, 2000)
                .toFormatter();
        LocalDate ld;
        try {
            ld = LocalDate.parse(fecha, fmt);
        } catch (DateTimeParseException ex) {
            throw new DatosInvalidosException("Fecha inválida: " + fecha);
        }
        // --- Buscar función en esa fecha ---
        Funcion funcion = esp.getFunciones().stream()
                .filter(f -> f.getFecha()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                        .equals(ld))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "No hay función de " + nombreEspectaculo + " en fecha " + fecha));
        // --- Verificar que sea no numerada ---
        if (funcion.getSede().esNumerada()) {
            throw new OperacionInvalidaException("La función es en sede numerada.");
        }
        // --- Crear entradas ---
        List<IEntrada> resultado = new ArrayList<>();
        for (int i = 0; i < cantidadEntradas; i++) {
            Entrada e = new Entrada(contrasenia, i, i, funcion.getPrecioBase()); // usa el nuevo constructor
            e.marcarComoVendida();
            usuario.agregarEntrada(e);
            System.out.println("Entrada vendida: " + e);
            funcion.agregarEntrada(e);
            resultado.add(e);
        }
        return resultado;
    }

    // 4) Venta de entradas en sedes numeradas
    // Dentro de Ticketek.java

    // 4) Venta de entradas en sedes numeradas
    @Override
    public List<IEntrada> venderEntrada(String nombreEspectaculo,
            String fecha,
            String email,
            String contrasenia,
            String sector,
            int[] asientos) {
        // 1) Validaciones
        if (nombreEspectaculo == null || nombreEspectaculo.isBlank()
                || fecha == null || fecha.isBlank()
                || email == null || email.isBlank()
                || contrasenia == null || contrasenia.isBlank()
                || sector == null || sector.isBlank()
                || asientos == null || asientos.length == 0) {
            throw new DatosInvalidosException("Parámetros inválidos para venta numerada.");
        }

        // 2) Autenticación
        Usuario usuario = usuarios.get(email);
        if (usuario == null)
            throw new EntidadNoEncontradaException("Usuario no registrado: " + email);
        if (!usuario.getContrasenia().equals(contrasenia))
            throw new CredencialesInvalidasException("Contraseña incorrecta: " + email);

        // 3) Espectáculo
        Espectaculo esp = espectaculos.get(nombreEspectaculo);
        if (esp == null)
            throw new EntidadNoEncontradaException("Espectáculo no registrado: " + nombreEspectaculo);

        // 4) Parseo de fecha dd/MM/yy
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/")
                .appendValueReduced(ChronoField.YEAR, 2, 2, 2000)
                .toFormatter();
        LocalDate ld;
        try {
            ld = LocalDate.parse(fecha, fmt);
        } catch (DateTimeParseException ex) {
            throw new DatosInvalidosException("Fecha inválida: " + fecha);
        }

        // 5) Encuentro de la función
        Funcion funcion = esp.getFunciones().stream()
                .filter(f -> f.getFecha()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                        .equals(ld))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException(
                        "No hay función de " + nombreEspectaculo + " en fecha " + fecha));

        // 6) Verificar numerada
        if (!funcion.getSede().esNumerada())
            throw new OperacionInvalidaException("La función es en sede no numerada.");

        // 7) Crear y asociar entradas
        List<IEntrada> resultado = new ArrayList<>();
        for (int asientoNum : asientos) {
            // Construyo la entrada con sector y asiento
            Entrada e = new Entrada(sector, 0, asientoNum, funcion.getPrecioBase());
            e.marcarComoVendida();
            usuario.agregarEntrada(e);
            funcion.agregarEntrada(e); // <--- Crucial: la guardo en la función
            resultado.add(e);
        }
        return resultado;
    }

    // 5) Listar funciones de un espect�culo
    @Override
    public String listarFunciones(String nombreEspectaculo) {
        if (!espectaculos.containsKey(nombreEspectaculo)) {
            throw new EntidadNoEncontradaException("Espectáculo no registrado: " + nombreEspectaculo);
        }
        Espectaculo esp = espectaculos.get(nombreEspectaculo);
        // Ordenar por fecha
        List<Funcion> funcs = new ArrayList<>(esp.getFunciones());
        funcs.sort(Comparator.comparing(Funcion::getFecha));

        StringBuilder sb = new StringBuilder();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yy");
        for (Funcion f : funcs) {
            // Formatear fecha
            LocalDate ld = f.getFecha().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            String fechaStr = fmt.format(ld);
            sb.append(" - (").append(fechaStr).append(") ")
                    .append(f.getSede().getNombre()).append(" - ");

            // Si es numerada: listar por sector
            if (f.getSede().esNumerada()) {
                List<Sector> sectores = f.getSede().getSectores();
                for (int i = 0; i < sectores.size(); i++) {
                    Sector sec = sectores.get(i);
                    long vendidas = f.getEntradas().stream()
                            .filter(e -> sec.getTipo().equals(e.getCodigo()))
                            .count();
                    int capacidadSector = sec.getAsientos(); // asumimos asientos = capacidad
                    sb.append(sec.getTipo()).append(": ")
                            .append(vendidas).append("/").append(capacidadSector);
                    if (i < sectores.size() - 1)
                        sb.append(" | ");
                }
            } else {
                // Estadio: un único “campo”
                long vendidas = f.getEntradas().size();
                int capacidad = f.getSede().getCapacidadMaxima();
                sb.append(vendidas).append("/").append(capacidad);
            }
            sb.append("\n");
            System.err.println(" - ("
                    + fechaStr + ") "
                    + f.getSede().getNombre() + " - "
                    + (f.getSede().esNumerada() ? "Numerada" : "No numerada")
                    + " - Entradas: " + f.getEntradas().size()
                    + "/" + f.getSede().getCapacidadMaxima());
        }
        return sb.toString();
    }

    // 15) Listar todas las entradas vendidas para un espect�culo
    @Override
    public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
        // Implementar logica
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
        // Implementar l�gica
        throw new UnsupportedOperationException("Pendiente de implementaci�n");
    }

    // 9) Cambiar entrada (con sector y asiento)
    @Override
    public IEntrada cambiarEntrada(IEntrada entrada,
            String contrasenia,
            String fecha,
            String sector,
            int asiento) {
        // Implementar l�gica
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
        // Implementar l�gica de c�lculo
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
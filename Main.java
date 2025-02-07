import models.*;
import services.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciar servicios
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        GestionClientes gestionClientes = new GestionClientes();
        GestionReservas gestionReservas = new GestionReservas();

        // Agregar habitaciones iniciales
        gestionHabitaciones.agregarHabitacion(new Habitacion(101, "Sencilla", 50.0));
        gestionHabitaciones.agregarHabitacion(new Habitacion(102, "Doble", 80.0));
        gestionHabitaciones.agregarHabitacion(new Habitacion(201, "Suite", 150.0));

        // Menú principal
        while (true) {
            System.out.println("\n🌟 Sistema de Reservas del Hotel 🌟");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Realizar Reserva");
            System.out.println("3. Ver Reservas");
            System.out.println("4. Cancelar Reserva");
            System.out.println("5. Filtrar Habitaciones");
            System.out.println("6. Generar Informe de Ingresos");
            System.out.println("7. Salir");
            System.out.print("➡️ Seleccione una opción: ");

            // Validar entrada numérica
            int opcion = leerOpcionValida(scanner);

            switch (opcion) {
                case 1:
                    registrarCliente(scanner, gestionClientes);
                    break;

                case 2:
                    realizarReserva(scanner, gestionClientes, gestionHabitaciones, gestionReservas);
                    break;

                case 3:
                    mostrarReservas(gestionReservas);
                    break;

                case 4:
                    cancelarReserva(scanner, gestionReservas);
                    break;

                case 5:
                    filtrarHabitaciones(scanner, gestionHabitaciones);
                    break;

                case 6:
                    generarInformeIngresos(scanner, gestionReservas);
                    break;

                case 7:
                    System.out.println("👋 ¡Hasta luego!");
                    return;

                default:
                    System.out.println("❌ Opción inválida.");
            }
        }
    }

    // Método para validar que se ingrese un número válido
    private static int leerOpcionValida(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Por favor, ingresa un número de opción válido: ");
            }
        }
    }

    // Método para registrar cliente
    private static void registrarCliente(Scanner scanner, GestionClientes gestionClientes) {
        String nombre = leerNombreValido(scanner, "Ingrese nombre del cliente: ");
        String documento = leerCampoRequerido(scanner, "Ingrese documento del cliente: ", "⚠️ El documento es requerido.");
        String email = leerEmailValido(scanner, "Ingrese email del cliente: ");
        gestionClientes.registrarCliente(new Cliente(nombre, documento, email));
        System.out.println("✅ Cliente registrado.");
    }

    // Método para leer un nombre válido (solo letras y espacios)
    private static String leerNombreValido(Scanner scanner, String mensaje) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$"); // Solo letras y espacios
        while (true) {
            System.out.print(mensaje);
            String nombre = scanner.nextLine().trim();
            if (pattern.matcher(nombre).matches()) {
                return nombre;
            } else {
                System.out.println("⚠️ El nombre debe contener solo letras y espacios.");
            }
        }
    }

    // Método para leer un campo requerido
    private static String leerCampoRequerido(Scanner scanner, String mensaje, String mensajeError) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            } else {
                System.out.println(mensajeError);
            }
        }
    }

    // Método para leer un correo electrónico válido
    private static String leerEmailValido(Scanner scanner, String mensaje) {
        Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w+$"); // Formato básico de correo
        while (true) {
            System.out.print(mensaje);
            String email = scanner.nextLine().trim();
            if (pattern.matcher(email).matches()) {
                return email;
            } else {
                System.out.println("⚠️ El correo electrónico debe ser válido y contener '@'.");
            }
        }
    }

    // Método para realizar reserva
    private static void realizarReserva(Scanner scanner, GestionClientes gestionClientes, GestionHabitaciones gestionHabitaciones, GestionReservas gestionReservas) {
        System.out.print("Ingrese documento del cliente: ");
        String doc = scanner.nextLine();
        Cliente cliente = gestionClientes.buscarClientePorDocumento(doc);

        if (cliente == null) {
            System.out.println("❌ Cliente no encontrado.");
            return;
        }

        System.out.println(" Habitaciones Disponibles:");
        for (Habitacion habitacion : gestionHabitaciones.obtenerHabitacionesDisponibles(LocalDate.now(), LocalDate.now().plusDays(7))) {
            System.out.println(habitacion);
        }

        System.out.print("Seleccione número de habitación: ");
        int numHabitacion = leerNumeroValido(scanner);

        Habitacion habitacion = gestionHabitaciones.buscarHabitacionPorNumero(numHabitacion);
        if (habitacion == null || habitacion.isOcupada()) {
            System.out.println("❌ Habitación no disponible.");
            return;
        }

        LocalDate fechaInicio = leerFechaValida(scanner, "Ingrese fecha de inicio (YYYY-MM-DD): ");
        LocalDate fechaFin = leerFechaValida(scanner, "Ingrese fecha de fin (YYYY-MM-DD): ");

        if (fechaInicio.isAfter(fechaFin)) {
            System.out.println("❌ La fecha de inicio no puede ser posterior a la fecha de fin.");
            return;
        }

        Reserva reserva = new Reserva(cliente, habitacion, fechaInicio, fechaFin);
        gestionReservas.realizarReserva(reserva);
        habitacion.setOcupada(true);
        System.out.println("✅ Reserva realizada.");
    }

    // Método para validar y leer un número entero
    private static int leerNumeroValido(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Por favor, ingresa un número válido: ");
            }
        }
    }

    // Método para validar y leer una fecha válida
    private static LocalDate leerFechaValida(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String fechaStr = scanner.nextLine().trim();
            try {
                LocalDate fecha = LocalDate.parse(fechaStr);
                if (fecha.isBefore(LocalDate.now())) {
                    System.out.println("⚠️ La fecha no puede ser anterior a la fecha actual.");
                } else {
                    return fecha;
                }
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Formato de fecha incorrecto. Usa YYYY-MM-DD.");
            }
        }
    }

    // Método para mostrar reservas
    private static void mostrarReservas(GestionReservas gestionReservas) {
        List<Reserva> reservas = gestionReservas.obtenerReservas();
        if (reservas.isEmpty()) {
            System.out.println("📋 No hay reservas registradas.");
        } else {
            System.out.println("📋 Lista de Reservas:");
            for (Reserva r : reservas) {
                System.out.println(r);
            }
        }
    }

    // Método para cancelar reserva
    private static void cancelarReserva(Scanner scanner, GestionReservas gestionReservas) {
        System.out.print("Ingrese documento del cliente para cancelar la reserva: ");
        String documento = scanner.nextLine();
        gestionReservas.cancelarReserva(documento);
    }

    // Método para filtrar habitaciones
    private static void filtrarHabitaciones(Scanner scanner, GestionHabitaciones gestionHabitaciones) {
        System.out.println("🌟 Filtrar Habitaciones 🌟");
        System.out.println("1. Filtrar por tipo");
        System.out.println("2. Filtrar por precio máximo");
        int filtroOpcion = leerOpcionValida(scanner);

        switch (filtroOpcion) {
            case 1:
                System.out.print("Ingrese el tipo de habitación (Sencilla, Doble, Suite): ");
                String tipo = scanner.nextLine();
                List<Habitacion> habitacionesPorTipo = gestionHabitaciones.filtrarHabitacionesPorTipo(tipo);
                if (habitacionesPorTipo.isEmpty()) {
                    System.out.println("📋 No hay habitaciones disponibles del tipo seleccionado.");
                } else {
                    System.out.println("📋 Habitaciones disponibles:");
                    for (Habitacion h : habitacionesPorTipo) {
                        System.out.println(h);
                    }
                }
                break;

            case 2:
                System.out.print("Ingrese el precio máximo: ");
                double precioMaximo = leerNumeroValido(scanner);
                List<Habitacion> habitacionesPorPrecio = gestionHabitaciones.filtrarHabitacionesPorPrecio(precioMaximo);
                if (habitacionesPorPrecio.isEmpty()) {
                    System.out.println("📋 No hay habitaciones disponibles dentro del rango de precio.");
                } else {
                    System.out.println("📋 Habitaciones disponibles:");
                    for (Habitacion h : habitacionesPorPrecio) {
                        System.out.println(h);
                    }
                }
                break;

            default:
                System.out.println("❌ Opción inválida.");
        }
    }

    // Método para generar informe de ingresos
    private static void generarInformeIngresos(Scanner scanner, GestionReservas gestionReservas) {
        System.out.println("🌟 Generar Informe de Ingresos 🌟");
        LocalDate fechaInicioInforme = leerFechaValida(scanner, "Ingrese fecha de inicio del informe (YYYY-MM-DD): ");
        LocalDate fechaFinInforme = leerFechaValida(scanner, "Ingrese fecha de fin del informe (YYYY-MM-DD): ");

        double ingresos = gestionReservas.calcularIngresosPorPeriodo(fechaInicioInforme, fechaFinInforme);
        System.out.println("📋 Total de ingresos entre " + fechaInicioInforme + " y " + fechaFinInforme + ": $" + ingresos);
    }
}
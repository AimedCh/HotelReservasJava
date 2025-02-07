package services;

import models.Habitacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionHabitaciones {
    private List<Habitacion> habitaciones;

    public GestionHabitaciones() {
        this.habitaciones = new ArrayList<>();
    }

    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public List<Habitacion> obtenerHabitacionesDisponibles(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (!habitacion.isOcupada()) {
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }

    public Habitacion buscarHabitacionPorNumero(int numero) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }
        return null;
    }

    // En GestionHabitaciones.java
    public List<Habitacion> filtrarHabitacionesPorTipo(String tipo) {
        List<Habitacion> resultado = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo().equalsIgnoreCase(tipo) && !habitacion.isOcupada()) {
                resultado.add(habitacion);
            }
        }
        return resultado;
    }

    public List<Habitacion> filtrarHabitacionesPorPrecio(double precioMaximo) {
        List<Habitacion> resultado = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getPrecioPorNoche() <= precioMaximo && !habitacion.isOcupada()) {
                resultado.add(habitacion);
            }
        }
        return resultado;
    }
}
package models;

import java.time.LocalDate;

public class Reserva {
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Reserva(Cliente cliente, Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public double calcularCostoTotal() {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        return dias * habitacion.getPrecioPorNoche();
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "Cliente=" + cliente.getNombre() +
                ", Habitación=" + habitacion.getNumero() +
                ", Fecha Inicio=" + fechaInicio +
                ", Fecha Fin=" + fechaFin +
                ", Costo Total=" + calcularCostoTotal()+"€" +
                '}';
    }
}
package services;

import models.Reserva;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionReservas {
    private List<Reserva> reservas;

    public GestionReservas() {
        this.reservas = new ArrayList<>();
    }

    public void realizarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> obtenerReservas() {
        return reservas;
    }

    public Reserva buscarReservaPorCliente(String documento) {
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getDocumento().equals(documento)) {
                return reserva;
            }
        }
        return null;
    }
    public void cancelarReserva(String documento) {
        Reserva reserva = buscarReservaPorCliente(documento);
        if (reserva != null) {
            reservas.remove(reserva);
            reserva.getHabitacion().setOcupada(false); // Liberar la habitación
            System.out.println("✅ Reserva cancelada exitosamente.");
        } else {
            System.out.println("❌ No se encontró ninguna reserva para el cliente con documento: " + documento);
        }
    }
    public double calcularIngresosPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        double totalIngresos = 0.0;
        for (Reserva reserva : reservas) {
            if (!reserva.getFechaInicio().isAfter(fechaFin) && !reserva.getFechaFin().isBefore(fechaInicio)) {
                totalIngresos += reserva.calcularCostoTotal();
            }
        }
        return totalIngresos;
    }
}
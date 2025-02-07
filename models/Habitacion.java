package models;

import java.time.LocalDate;

public class Habitacion {
    private int numero;
    private String tipo; // Ejemplo: "Sencilla", "Doble", "Suite"
    private double precioPorNoche;
    private boolean ocupada;

    public Habitacion(int numero, String tipo, double precioPorNoche) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.ocupada = false;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Habitación{" +
                "Número=" + numero +
                ", Tipo='" + tipo + '\'' +
                ", Precio por noche=" + precioPorNoche+"€" +
                ", Ocupada=" + ocupada +
                '}';
    }
}
package models;

public class Cliente {
    private String nombre;
    private String documento;
    private String email;

    public Cliente(String nombre, String documento, String email) {
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "Nombre='" + nombre + '\'' +
                ", Documento='" + documento + '\'' +
                ", Email='" + email + '\'' +
                '}';
    }
}
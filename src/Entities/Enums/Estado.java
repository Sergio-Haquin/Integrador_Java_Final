package Entities.Enums;

public enum Estado {
    PREPARACION("En preparación"),
    PENDIENTE("Pendiente de aprobación"),
    CANCELADO("Cancelado"),
    RECHAZADO("Rechazado"),
    ENTREGADO("Entregado");

    private final String descripcion;

    Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
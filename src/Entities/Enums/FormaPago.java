package Entities.Enums;

public enum FormaPago {
    EFECTIVO("Efectivo en mano"),
    MERCADO_PAGO("Pago con Mercado Pago");

    private final String descripcion;

    FormaPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
package Entities.Enums;

public enum TipoEnvio {
    DELIVERY("Envío a domicilio"),
    TAKE_AWAY("Retiro en sucursal");

    private final String descripcion;

    TipoEnvio(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
package Entities.Enums;

public enum TipoPromocion {
    HAPPY_HOUR("Promoción de hora feliz"),
    PROMOCION_1("Promoción general tipo 1"); // O un nombre más descriptivo

    private final String descripcion;

    TipoPromocion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
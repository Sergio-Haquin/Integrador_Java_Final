package Entities.Enums;

public enum Rol {
    ADMIN("Administrador del sistema"),
    EMPLEADO("Empleado de la sucursal"),
    CLIENTE("Cliente registrado");

    private final String descripcion;

    Rol(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
package Entities;

// Importaciones de Lombok
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public abstract class Articulo extends Base {

    @Column(name = "denominacion", nullable = false, length = 255)
    protected String denominacion;

    @Column(name = "precio_venta", precision = 10, scale = 2)
    protected Double precioVenta;

    @ManyToOne
    @JoinColumn(name = "imagen_id")
    protected Imagen imagen;

    @ManyToOne
    @JoinColumn(name = "unidad_medida_id")
    protected UnidadMedida unidadMedida;

}
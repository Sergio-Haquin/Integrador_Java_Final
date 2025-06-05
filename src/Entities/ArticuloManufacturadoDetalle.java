package Entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticuloManufacturadoDetalle extends Base {

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "unidad_medida_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UnidadMedida unidadMedida;

    @ManyToOne
    @JoinColumn(name = "articulo_insumo_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ArticuloInsumo articuloInsumo;

    @ManyToOne
    @JoinColumn(name = "articulo_manufacturado_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ArticuloManufacturado articuloManufacturado;
}
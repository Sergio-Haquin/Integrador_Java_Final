package Entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticuloManufacturado extends Articulo {

    @Column(name = "descripcion")
    private String descripcion;

   // @Column(name = "precio_venta", precision = 10, scale = 2)
   // private Double precioVenta;

    @Column(name = "tiempo_estimado_minutos")
    private Integer tiempoEstimadoMinutos;

    @Column(name = "preparacion")
    private String preparacion;

//    @OneToOne
//    @JoinColumn(name = "imagen_id", unique = true)
//    @ToString.Exclude
//    private Imagen imagen;

    @OneToMany(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ArticuloManufacturadoDetalle> detalles = new HashSet<>();


    public void addDetalle(ArticuloManufacturadoDetalle detalle) {
        if (this.detalles == null) {
            this.detalles = new HashSet<>();
        }
        this.detalles.add(detalle);
        detalle.setArticuloManufacturado(this);
    }
}
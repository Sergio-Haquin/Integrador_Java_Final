package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

// Importaciones de JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DetallePedido extends Base {

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "sub_total", precision = 10, scale = 2, nullable = false)
    private Double subTotal;

    @ManyToOne
    @JoinColumn(name = "articulo_id", nullable = false)
    @ToString.Exclude
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    @ToString.Exclude
    private Pedido pedido;

    public Double getSubTotal() {
        if (this.articulo != null && this.cantidad != null) {
            return this.cantidad * this.subTotal;
        }
        return 0.0;
    }
}
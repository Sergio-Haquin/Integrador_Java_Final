package Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import Entities.Enums.Estado;
import Entities.Enums.FormaPago;
import Entities.Enums.TipoEnvio;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Pedido extends Base {

    @Column(name = "hora_estimada_finalizacion")
    private LocalTime horaEstimadaFinalizacion;

    @Column(name = "total_pedido", precision = 12, scale = 2)
    private Double total;

    @Column(name = "total_costo",  precision = 12, scale = 2)
    private Double totalCosto;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_envio", nullable = false)
    private TipoEnvio tipoEnvio;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pago", nullable = false)
    private FormaPago formaPago;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id", unique = true)
    @ToString.Exclude
    private Factura factura;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private Set<DetallePedido> detallePedidos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @ToString.Exclude
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    @ToString.Exclude
    private Sucursal sucursal;

    public void addDetallePedido(DetallePedido detalle) {
        if (this.detallePedidos == null) {
            this.detallePedidos = new HashSet<>();
        }
        this.detallePedidos.add(detalle);
        detalle.setPedido(this);
    }

    public void removeDetallePedido(DetallePedido detalle) {
        if (this.detallePedidos != null) {
            this.detallePedidos.remove(detalle);
            detalle.setPedido(null);
        }
    }

    public Double calculateTotal() {
        if (this.detallePedidos == null || this.detallePedidos.isEmpty()) {
            return 0.0;
        }
        return this.detallePedidos.stream()
                .mapToDouble(DetallePedido::getSubTotal)
                .sum();
    }
}
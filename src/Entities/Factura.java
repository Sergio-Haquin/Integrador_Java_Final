package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString; // Importar para @ToString.Exclude
import lombok.experimental.SuperBuilder;

// Importaciones de JPA
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne; // Para la relación con Pedido

import java.time.LocalDate;

// Importaciones de tus Enums
import Entities.Enums.FormaPago;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Factura extends Base {

    @Column(name = "fecha_facturacion", nullable = false)
    private LocalDate fechaFacturacion;

    @Column(name = "mp_payment_id")
    private Integer mpPaymentId;

    @Column(name = "mp_merchant_order_id")
    private Integer mpMerchantOrderId;

    @Column(name = "mp_preference_id", length = 255)
    private String mpPreferenceId;

    @Column(name = "mp_payment_type", length = 50)
    private String mpPaymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pago", nullable = false)
    private FormaPago formaPago;

    @Column(name = "total_venta", precision = 12, scale = 2, nullable = false)
    private Double totalVenta;

    @OneToOne(mappedBy = "factura")
    @ToString.Exclude
    private Pedido pedido;
}
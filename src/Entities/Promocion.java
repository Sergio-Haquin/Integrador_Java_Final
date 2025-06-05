package Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Entities.Enums.TipoPromocion;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Promocion extends Base {

    @Column(name = "denominacion", length = 100, nullable = false)
    private String denominacion;

    @Column(name = "fecha_desde", nullable = false)
    private LocalDate fechaDesde;

    @Column(name = "fecha_hasta", nullable = false)
    private LocalDate fechaHasta;

    @Column(name = "hora_desde", nullable = false)
    private LocalTime horaDesde;

    @Column(name = "hora_hasta", nullable = false)
    private LocalTime horaHasta;

    @Column(name = "descripcion_descuento", length = 255)
    private String descripcionDescuento;

    @Column(name = "precio_promocional", precision = 12, scale = 2)
    private Double precioPromocional;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_promocion", nullable = false)
    private TipoPromocion tipoPromocion;

    @ManyToMany(mappedBy = "promociones")
    @Builder.Default
    @ToString.Exclude
    private List<Imagen> imagenes = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Builder.Default
    @ToString.Exclude
    @JoinTable(
            name = "promocion_articulo_manufacturado",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "articulo_manufacturado_id")
    )
    private Set<ArticuloManufacturado> articulosManufacturados = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Builder.Default
    @ToString.Exclude
    @JoinTable(
            name = "promocion_articulo_insumo",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "articulo_insumo_id")
    )
    private Set<ArticuloInsumo> articulosInsumos = new HashSet<>();

    @ManyToMany(mappedBy = "promociones")
    @Builder.Default
    @ToString.Exclude
    private Set<Sucursal> sucursales = new HashSet<>();

    public void addArticuloManufacturado(ArticuloManufacturado articulo) {
        if (this.articulosManufacturados == null) this.articulosManufacturados = new HashSet<>();
        this.articulosManufacturados.add(articulo);
    }
    public void removeArticuloManufacturado(ArticuloManufacturado articulo) {
        if (this.articulosManufacturados != null) this.articulosManufacturados.remove(articulo);

    }

    public void addArticuloInsumo(ArticuloInsumo articulo) {
        if (this.articulosInsumos == null) this.articulosInsumos = new HashSet<>();
        this.articulosInsumos.add(articulo);
    }
    public void removeArticuloInsumo(ArticuloInsumo articulo) {
        if (this.articulosInsumos != null) this.articulosInsumos.remove(articulo);
    }

    public void addSucursal(Sucursal sucursal) {
        if (this.sucursales == null) this.sucursales = new HashSet<>();
        this.sucursales.add(sucursal);

    }
    public void removeSucursal(Sucursal sucursal) {
        if (this.sucursales != null) this.sucursales.remove(sucursal);

    }
}
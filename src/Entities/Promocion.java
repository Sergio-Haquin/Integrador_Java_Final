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
            name = "promocion_articulo",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "articulo_id")
    )
    private Set<Articulo> articulos = new HashSet<>();

    @ManyToMany(mappedBy = "promociones")
    @Builder.Default
    @ToString.Exclude
    private Set<Sucursal> sucursales = new HashSet<>();

    public void addArticulo(Articulo articulo) {
        if (this.articulos == null) this.articulos = new HashSet<>();
        this.articulos.add(articulo);
    }
    public void removeArticulo(Articulo articulo) {
        if (this.articulos != null) this.articulos.remove(articulo);
    }

    public void addSucursal(Sucursal sucursal) {
        if (this.sucursales == null) this.sucursales = new HashSet<>();
        this.sucursales.add(sucursal);

    }
    public void removeSucursal(Sucursal sucursal) {
        if (this.sucursales != null) this.sucursales.remove(sucursal);

    }
}
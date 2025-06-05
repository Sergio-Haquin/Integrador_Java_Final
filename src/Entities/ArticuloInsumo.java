package Entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ArticuloInsumo extends Articulo {

    @Column(name = "precio_compra", precision = 10, scale = 2)
    private Double precioCompra;

    @Column(name = "stock_actual")
    private Integer stockActual;

    @Column(name = "stock_minimo")
    private Integer stockMinimo;

    @Column(name = "es_para_elaborar")
    private Boolean esParaElaborar;

//    @ManyToOne
//    @JoinColumn(name = "unidad_medida_id")
//    @ToString.Exclude
//    private UnidadMedida unidadMedida;

//    @OneToOne
//    @JoinColumn(name = "imagen_id", unique = true)
//    @ToString.Exclude
//    private Imagen imagen;

}
package Entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Sucursal extends Base {

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "horario_apertura", nullable = false)
    private LocalTime horarioApertura;

    @Column(name = "horario_cierre", nullable = false)
    private LocalTime horarioCierre;

    // @Column(name = "es_casa_matriz", nullable = false)
    // private Boolean esCasaMatriz;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", unique = true, nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Domicilio domicilio;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Empresa empresa;

    @ManyToMany(mappedBy = "sucursales")
    @Builder.Default
    @ToString.Exclude
    private Set<Promocion> promociones = new HashSet<>();

    @ManyToMany(mappedBy = "sucursales")
    @Builder.Default
    @ToString.Exclude
    private Set<Categoria> categorias = new HashSet<>();
}
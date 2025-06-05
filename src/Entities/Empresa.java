package Entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Empresa extends Base {

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "cuil")
    private Integer cuil;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Sucursal> sucursales = new HashSet<>();


    public void addSucursal(Sucursal sucursal) {
        if (this.sucursales == null) {
            this.sucursales = new HashSet<>();
        }
        this.sucursales.add(sucursal);
        sucursal.setEmpresa(this);
    }

    public void removeSucursal(Sucursal sucursal) {
        if (this.sucursales != null) {
            this.sucursales.remove(sucursal);
            sucursal.setEmpresa(null);
        }
    }
}
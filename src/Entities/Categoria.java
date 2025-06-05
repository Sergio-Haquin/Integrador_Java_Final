package Entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Categoria extends Base {

    @Column(name = "denominacion", nullable = false, length = 100)
    private String denominacion;

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    @ToString.Exclude
    private Categoria categoriaPadre;


    @OneToMany(mappedBy = "categoriaPadre", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private Set<Categoria> subCategorias = new HashSet<>();


    public void addSubCategoria(Categoria subCategoria) {
        if (this.subCategorias == null) {
            this.subCategorias = new HashSet<>();
        }
        this.subCategorias.add(subCategoria);
        subCategoria.setCategoriaPadre(this);
    }

    public void removeSubCategoria(Categoria subCategoria) {
        if (this.subCategorias != null) {
            this.subCategorias.remove(subCategoria);
            subCategoria.setCategoriaPadre(null);
        }
    }

}
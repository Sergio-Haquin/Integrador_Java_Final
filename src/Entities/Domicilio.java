package Entities;

// Importaciones de Lombok
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
public class Domicilio extends Base {

    @Column(name = "calle", length = 100, nullable = false)
    private String calle;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "codigo_postal", length = 10, nullable = false)
    private Integer cp;

    @ManyToOne
    @JoinColumn(name = "localidad_id", nullable = false)
    @ToString.Exclude
    private Localidad localidad;

}
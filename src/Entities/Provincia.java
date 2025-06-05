package Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
public class Provincia extends Base {

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = false)
    @ToString.Exclude
    private Pais pais;

}
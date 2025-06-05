package Entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

// Importaciones de JPA
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; // Para Domicilio
import jakarta.persistence.OneToMany; // Para Pedidos
import jakarta.persistence.OneToOne;  // Para Usuario

import java.time.LocalDate;
import java.util.ArrayList; // Usaremos ArrayList para las colecciones por defecto
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Cliente extends Base {

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 255, unique = true, nullable = false)
    private String email;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", unique = true)
    @ToString.Exclude
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "domicilio_id")
    @ToString.Exclude
    private Domicilio domicilio;

    @OneToOne
    @JoinColumn(name = "imagen_id")
    private Imagen imagen;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Pedido> pedidos = new ArrayList<>();

    public void addPedido(Pedido pedido) {
        if (this.pedidos == null) {
            this.pedidos = new ArrayList<>();
        }
        this.pedidos.add(pedido);
        pedido.setCliente(this);
    }

    public void removePedido(Pedido pedido) {
        if (this.pedidos != null) {
            this.pedidos.remove(pedido);
            pedido.setCliente(null);
        }
    }

}
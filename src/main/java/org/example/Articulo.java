package org.example;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name="articulo")
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Cantidad")
    private int cantidad;
    @Column(name="Denominacion")
    private String denominacion;
    @Column(name="Precio")
    private int precio;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST) //bidireccionalidad con el objeto de detalle factura
   @Builder.Default
    private Set<DetalleFactura> detallesfacturas = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //Merge actualiza
    @JoinTable(name="articulo_categoria", joinColumns = @JoinColumn(name = "articulo_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
   @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();
}

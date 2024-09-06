package org.example;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="factura")
public class Factura implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="Fecha")
    private String fecha;
    @Column(name="Numero")
    private int numero;
    @Column(name="Total")
    private int total;

    @ManyToOne(cascade = CascadeType.PERSIST) //si borro o modifico una factura no quiero que el cliente sea eliminado o modificado
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
    private Set<DetalleFactura> detalles = new HashSet<>();   //bidireccional con detalle factura, tiene el objeto de detalle factura



}

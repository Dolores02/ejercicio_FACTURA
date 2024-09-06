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
@Table(name="cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "DNI", unique = true) //todos son distintos
    private int dni;

    @OneToOne(cascade = CascadeType.ALL)//cualquier cambio que realice en cliente se va a ver reflejado en el domicilio del mismo.
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente" ) //bidireccionalidad con factura
    @Builder.Default
    private Set<Factura> facturas = new HashSet<>(); //bidireccional, tiene el objeto de factura


}

package org.example;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name="domicilio")
public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Nombre_Calle")
    private String nombrecalle;
    @Column(name="Numero")
    private int numero;

    @OneToOne(mappedBy = "domicilio") //bidireccionalidad con cliente
    private Cliente cliente;

}

package com.bionaturista.inicioSesion.entidades;

import com.bionaturista.inicioSesion.enums.RolNombre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RolNombre rolNombre;
}

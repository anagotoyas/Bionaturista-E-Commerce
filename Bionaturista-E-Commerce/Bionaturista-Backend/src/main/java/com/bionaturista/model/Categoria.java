package com.bionaturista.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="categorias")
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idCategoria;

    @NotNull
    @Size(min = 5, max = 50, message = "La categoria tiene como maximo 50 caracteres")
    @Column(name = "nombre_categoria", nullable = false, length = 50)
    private String nombreCategoria;
}

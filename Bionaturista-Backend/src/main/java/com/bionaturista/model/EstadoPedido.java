package com.bionaturista.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "estados")
public class EstadoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstado;

    @NotNull
    @Size(min=3,max=20, message="El estado debe ser mínimo 3 caracteres y máximo 20.")
    @Column(name = "nombreEstado", nullable = false)
    private String nombreEstado;

    @OneToMany(mappedBy ="estadoPedido")
    private List<Pedido> pedido;

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}

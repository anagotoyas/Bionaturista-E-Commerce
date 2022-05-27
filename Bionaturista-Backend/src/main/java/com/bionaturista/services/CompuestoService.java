package com.bionaturista.services;

import com.bionaturista.model.Compuesto;

import java.util.List;

public interface CompuestoService {

    Compuesto crearCompuesto(Compuesto compuesto);
    Compuesto modificarCompuesto(Compuesto compuesto);
    void eliminarCompuesto(Integer idCompuesto);

    List<Compuesto> listarCompuesto();
    Compuesto obtenerCompuestoPorIdCompuesto(Integer idCompuesto);
}

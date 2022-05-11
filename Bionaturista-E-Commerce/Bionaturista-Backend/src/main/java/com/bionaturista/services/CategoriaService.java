package com.bionaturista.services;

import com.bionaturista.model.Categoria;
import com.bionaturista.model.Compuesto;

import java.util.List;

public interface CategoriaService {

    Categoria crearCategoria(Categoria categoria);
    Categoria modificarCategoria(Categoria categoria);
    void eliminarCategoria(Integer idCategoria);
    List<Categoria> listarCategoria();
    Categoria obtenerCategoriaPorIdCategoria(Integer idCategoria);

}

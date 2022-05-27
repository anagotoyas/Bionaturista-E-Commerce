package com.bionaturista.services;

import com.bionaturista.model.Categoria;
import com.bionaturista.model.Compuesto;
import com.bionaturista.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto crearProducto(Producto producto);
    List<Producto> listarProducto();
    Producto obtenerProductoPorIdProducto(Integer idProducto);

    Long countProductos();
    interface RolService {
    }
}

package com.bionaturista.services.impl;

import com.bionaturista.model.Categoria;
import com.bionaturista.model.Producto;
import com.bionaturista.repositories.ProductoRepository;
import com.bionaturista.services.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository){

        this.productoRepository = productoRepository;
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProducto() {

        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorIdProducto(Integer idProducto) {
        return productoRepository.findById(idProducto).orElse(new Producto());
    }

    @Override
    public Long countProductos() {
        return productoRepository.contarProductos();
    }

}

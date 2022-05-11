package com.bionaturista.services.impl;

import com.bionaturista.model.Producto;
import com.bionaturista.repositories.ProductoRepository;
import com.bionaturista.services.ProductoService;
import org.springframework.stereotype.Service;

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
}

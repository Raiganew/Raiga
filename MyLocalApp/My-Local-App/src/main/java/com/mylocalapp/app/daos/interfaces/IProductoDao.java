package com.mylocalapp.app.daos.interfaces;

import com.mylocalapp.app.models.Producto;

public interface IProductoDao {
    public Producto insert(Producto producto);

    public java.util.List<Producto> findAll();

    public void delete(Long id);
}

package com.mylocalapp.app.daos.interfaces;

import com.mylocalapp.app.models.TipoProducto;

import java.util.List;

public interface ITipoProductoDao
{
    public List<TipoProducto> findAll();

    public void save(TipoProducto tipoProducto);
}

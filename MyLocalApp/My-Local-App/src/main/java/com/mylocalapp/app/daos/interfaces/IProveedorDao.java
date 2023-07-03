package com.mylocalapp.app.daos.interfaces;

import com.mylocalapp.app.models.Proveedor;

import java.util.List;

public interface IProveedorDao
{
    public List<Proveedor> findALl();

    public void save(Proveedor proveedor);
}

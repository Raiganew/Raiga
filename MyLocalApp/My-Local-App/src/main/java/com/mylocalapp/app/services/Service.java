package com.mylocalapp.app.services;

import java.util.Date;
import java.util.List;

import com.mylocalapp.app.daos.interfaces.IProductoDao;
import com.mylocalapp.app.daos.interfaces.IProveedorDao;
import com.mylocalapp.app.daos.interfaces.ITipoProductoDao;
import com.mylocalapp.app.models.Proveedor;
import com.mylocalapp.app.models.TipoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import com.mylocalapp.app.models.Producto;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private IProductoDao productoDao;
    @Autowired
    private ITipoProductoDao tipoPrdocutoDao;
    @Autowired
    private IProveedorDao proveedorDao;

    public Producto insertProducto(Producto producto) {
        producto.setFechaIngreso(new Date());
        return productoDao.insert(producto);
    }

    public List<Producto> getAllProductos() {
        return productoDao.findAll();
    }

    public void insertProveedor(Proveedor proveedor) {
        proveedor.setFechaRegistro(new Date());
        proveedorDao.save(proveedor);
    }

    public List<Proveedor> getAllProveedors() {
        return proveedorDao.findALl();
    }

    public void insertTipoProducto(TipoProducto tipo) {
        tipoPrdocutoDao.save(tipo);
    }

    public List<TipoProducto> getAllTipo() {
        return tipoPrdocutoDao.findAll();
    }

    public void eliminarProducto(Long id) {
        productoDao.delete(id);
    }
}

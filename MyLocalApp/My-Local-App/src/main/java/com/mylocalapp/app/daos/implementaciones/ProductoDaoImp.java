package com.mylocalapp.app.daos.implementaciones;

import java.util.List;

import com.mylocalapp.app.daos.interfaces.IProductoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.mylocalapp.app.daos.repository.IProductoRepository;
import com.mylocalapp.app.models.Producto;

@Repository
@Primary
public class ProductoDaoImp implements IProductoDao
{
    @Autowired
    private IProductoRepository productoDao;

    public Producto insert(Producto producto)
    {
        return productoDao.save(producto);
    }

    @Override
    public List<Producto> findAll()
    {
        return productoDao.findAll();
    }

    @Override
    public void delete(Long id) {
        productoDao.deleteById(id);;
    }
}

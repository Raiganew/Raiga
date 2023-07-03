package com.mylocalapp.app.daos.implementaciones;

import com.mylocalapp.app.daos.interfaces.IProveedorDao;
import com.mylocalapp.app.daos.repository.IProveedorRepository;
import com.mylocalapp.app.models.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProveedorDaoImp implements IProveedorDao
{
    @Autowired
    private IProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> findALl()
    {
        return proveedorRepository.findAll();
    }

    @Override
    public void save(Proveedor proveedor)
    {
        proveedorRepository.save(proveedor);
    }
}

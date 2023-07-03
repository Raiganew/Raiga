package com.mylocalapp.app.daos.implementaciones;

import com.mylocalapp.app.daos.interfaces.ITipoProductoDao;
import com.mylocalapp.app.daos.repository.ITipoProductoRepository;
import com.mylocalapp.app.models.TipoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoProductoDaoImp implements ITipoProductoDao
{
    @Autowired
    private ITipoProductoRepository tipoProductoRepository;

    @Override
    public List<TipoProducto> findAll()
    {
        return tipoProductoRepository.findAll();
    }

    @Override
    public void save(TipoProducto tipoProducto)
    {
        tipoProductoRepository.save(tipoProducto);
    }
}

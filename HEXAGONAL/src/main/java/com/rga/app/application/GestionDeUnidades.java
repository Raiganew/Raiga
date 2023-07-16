package com.rga.app.application;

import com.rga.app.domain.Ship;
import com.rga.app.infraestructure.inputPort.AzurInputPort;
import com.rga.app.infraestructure.outputPort.AzurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionDeUnidades implements AzurInputPort
{
    @Autowired
    private AzurRepository repository;
    private Logger log = LoggerFactory.getLogger(GestionDeUnidades.class);

    @Override
    public Ship registrar(Ship ship)
    {
        if (repository.register(ship) == 1)
        {
            log.info("!REGISTRO DE: ".concat(ship.getName()).concat(" EXITOSO¡"));
        }
        return listar().stream().filter(s -> s.getName().equals(ship.getName()))
                .filter(f -> f.getFaction().equals(ship.getFaction()))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Ship> listar()
    {
        return repository.findAll();
    }

    @Override
    public Ship actualizarInformacion(Ship ship)
    {
        if (repository.update(ship) == 1)
        {
            log.info("!ACTUALIZACION DE: ".concat(ship.getName()).concat(" EXITOSA¡"));
        }
        return listar().stream().filter(s -> s.getName().equals(ship.getName()))
                .filter(f -> f.getFaction().equals(ship.getFaction()))
                .findAny()
                .orElse(null);
    }

    @Override
    public Ship buscarPorId(int id)
    {
        return repository.findById(id);
    }
}

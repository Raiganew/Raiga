package com.rga.app.infraestructure.inputAdapter;

import com.rga.app.domain.Ship;
import com.rga.app.infraestructure.inputPort.AzurInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/azur")
public class RestAdapter
{
    @Autowired
    private AzurInputPort azur;

    @PostMapping("/crear")
    public Ship crear(@RequestBody Ship ship)
    {
        return azur.registrar(ship);
    }

    @GetMapping("/listar")
    public List<Ship> listar()
    {
        return azur.listar();
    }

    @GetMapping("/id/{id}")
    public Ship listar(@PathVariable int id)
    {
        return azur.buscarPorId(id);
    }

    @PutMapping("/actualizar")
    public Ship actualizar(@RequestBody Ship ship)
    {
        return azur.actualizarInformacion(ship);
    }
}

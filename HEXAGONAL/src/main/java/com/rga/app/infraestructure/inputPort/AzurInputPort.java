package com.rga.app.infraestructure.inputPort;

import com.rga.app.domain.Ship;

import java.util.List;

public interface AzurInputPort
{
    Ship registrar(Ship ship);

    List<Ship> listar();

    Ship actualizarInformacion(Ship ship);

    Ship buscarPorId(int id);
}

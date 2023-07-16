package com.rga.app.infraestructure.outputPort;

import com.rga.app.domain.Ship;

import java.util.List;

public interface AzurRepository
{
    int register(Ship ship);

    List<Ship> findAll();

    Ship findById(int id);

    int update(Ship ship);
}

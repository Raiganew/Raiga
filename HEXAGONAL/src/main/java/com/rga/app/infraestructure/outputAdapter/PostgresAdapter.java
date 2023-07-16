package com.rga.app.infraestructure.outputAdapter;

import com.rga.app.domain.Ship;
import com.rga.app.infraestructure.outputPort.AzurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresAdapter implements AzurRepository
{
    @Autowired
    JdbcTemplate template;

    @Override
    public int register(Ship ship)
    {
        return template.update("INSERT INTO azur (name, type, faction) VALUES(?,?,?)",
                ship.getName(), ship.getType(), ship.getFaction());
    }

    @Override
    public List<Ship> findAll()
    {
        return template.query("SELECT * from azur", BeanPropertyRowMapper.newInstance(Ship.class));
    }

    @Override
    public Ship findById(int id)
    {
        try
        {
            return template.queryForObject("SELECT * FROM azur WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Ship.class), id);
        } catch (IncorrectResultSizeDataAccessException e)
        {
            return new Ship();
        }
    }

    @Override
    public int update(Ship ship)
    {
        return template.update("UPDATE azur SET name=?, type=?, faction=? WHERE id=?",
                ship.getName(),
                ship.getType(),
                ship.getFaction(),
                Integer.parseInt(ship.getId()));
    }
}

package com.mylocalapp.app.daos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mylocalapp.app.models.TipoProducto;

public interface ITipoProductoRepository extends JpaRepository<TipoProducto, Long> {

}

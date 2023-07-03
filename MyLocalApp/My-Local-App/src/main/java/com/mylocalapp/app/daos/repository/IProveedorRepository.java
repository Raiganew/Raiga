package com.mylocalapp.app.daos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mylocalapp.app.models.Proveedor;

public interface IProveedorRepository extends JpaRepository<Proveedor, Long> {

}

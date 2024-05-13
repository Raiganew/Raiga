package com.fabian.curso.springboot.app.repositories;

import com.fabian.curso.springboot.app.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long>
{
}

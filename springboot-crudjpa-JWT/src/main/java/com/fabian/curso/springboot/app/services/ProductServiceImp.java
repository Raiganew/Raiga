package com.fabian.curso.springboot.app.services;

import com.fabian.curso.springboot.app.entities.Product;
import com.fabian.curso.springboot.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService
{
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll()
    {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product)
    {
        return repository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id)
    {
        Optional<Product> productOptional = repository.findById(id);
        productOptional.ifPresent(productDb -> {
            repository.delete(productDb);
        });
        return productOptional;
    }
}

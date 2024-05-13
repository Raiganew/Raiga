package com.fabian.curso.springboot.app.controllers;

import com.fabian.curso.springboot.app.entities.Product;
import com.fabian.curso.springboot.app.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController
{
    @Autowired
    private ProductService service;

    @GetMapping("/listar")
    public List<Product> listar()
    {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id)
    {
        Optional<Product> productOptional = service.findById(id);
        if (productOptional.isPresent())
        {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Importante BindingResult debe esta justo a la derecha del objeto a validar
     * valid funciona con la libreria de spring started valid
     *
     * @param producto
     * @param result
     * @return
     */
    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@Valid @RequestBody Product producto, BindingResult result)
    {
        if (result.hasFieldErrors())
        {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(producto));
    }

    /**
     * Importante BindingResult debe esta justo a la derecha del objeto a validar
     * valid funciona con la libreria de spring started valid
     *
     * @param producto
     * @param result
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product producto, BindingResult result, @PathVariable Long id)
    {
        if (result.hasFieldErrors())
        {
            return validation(result);
        }
        producto.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        Optional<Product> productOptional = service.delete(id);
        if (productOptional.isPresent())
        {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result)
    {
        Map<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e ->{
            errors.put(e.getField(),"El campo " + e.getField() + " " + e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}

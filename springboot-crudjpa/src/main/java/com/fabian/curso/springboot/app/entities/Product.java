package com.fabian.curso.springboot.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A diferencia de NotEmpty aparte de validar el vacio valdia que no tenga espacion es blanco
    @NotBlank(message = "{NotBlank.product.name}")
    @Size(min = 3,max = 20)
    private String name;
    @Min(100)
    @NotNull
    private Integer price;
    @NotEmpty(message = "{NotEmpty.product.description}")
    private String description;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}

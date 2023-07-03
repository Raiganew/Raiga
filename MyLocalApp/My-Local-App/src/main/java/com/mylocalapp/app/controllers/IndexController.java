package com.mylocalapp.app.controllers;

import com.mylocalapp.app.models.Proveedor;
import com.mylocalapp.app.models.TipoProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.mylocalapp.app.models.Producto;
import com.mylocalapp.app.services.Service;

@Controller
public class IndexController {
    @Autowired
    private Service service;

    @GetMapping({ "", "/" })
    public String index() {
        return "index";
    }

    @GetMapping("productos")
    public String index2(Model model) {
        model.addAttribute("productos", service.getAllProductos());
        model.addAttribute("proveedores", service.getAllProveedors());
        model.addAttribute("tiposOrden", service.getAllTipo());
        return "components/productos";
    }

    @PostMapping("/saveProducto")
    public String guardar(Producto producto) {
        service.insertProducto(producto);
        return "redirect:/productos";
    }

    @PostMapping("/saveTipo")
    public String guardarTipo(TipoProducto tipo) {
        service.insertTipoProducto(tipo);
        return "redirect:/tipoProducto";
    }

    @PostMapping("/saveProveedor")
    public String guardarProveedor(Proveedor proveedor) {
        service.insertProveedor(proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("proveedores")
    public String proveedors(Model model) {
        model.addAttribute("proveedores", service.getAllProveedors());
        return "components/proveedores";
    }

    @GetMapping("tipoProducto")
    public String tipoProducto(Model model) {
        model.addAttribute("tipoProductos", service.getAllTipo());
        return "components/tipoProducto";
    }

    @PostMapping("/eliminarProducto")
    public String update(Producto producto) {
        service.eliminarProducto(producto.getId());
        return "redirect:/productos";
    }
}

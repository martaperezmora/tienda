package com.mpm.springprojects.tienda.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mpm.springprojects.tienda.model.Producto;
import com.mpm.springprojects.tienda.services.ProductosService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    ProductosService productosService;

    @RequestMapping(value="/list")
    public ModelAndView list(){
        List<Producto> productos = productosService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("productos", productos);
        modelAndView.setViewName("productos/list");

        return modelAndView;
    }

    @RequestMapping(path="/edit")
    public ModelAndView edit(@RequestParam(name="codigo", required=true) int codigo){
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("producto", getProducto(codigo));
        modelAndView.setViewName("productos/edit");

        return modelAndView;

    }

    // private Producto getProducto(int codigo){
    //     List<Producto> productos = getProductos();
    //     int indexOf = productos.indexOf(new Producto(codigo));
        
    //     return productos.get(indexOf);
    // }

    // private List<Producto> getProductos() {
    //     ArrayList<Producto> productos = new ArrayList<Producto>();
    //     productos.add(new Producto(12332, "Coca-Cola", "/img/cola.png"));
    //     productos.add(new Producto(15452, "Pepsi", "/img/pepsi.png"));
    //     productos.add(new Producto(12354, "Fanta", "/img/fanta.jpg"));
    //     productos.add(new Producto(434343, "Sprite", ""));

    //     return productos;
    // }
}

package com.mpm.springprojects.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mpm.springprojects.tienda.model.Producto;

@Controller
@RequestMapping("/saludar")
public class SaludarController {

    @GetMapping(value = { "/hola", "/buenas" })
    public String hola() {
        return "hola";
    }

    @GetMapping(value = { "/adios", "/hastaluego" })
    public String adios() {
        return "adios";
    }

    @GetMapping(value = { "/producto" })
    public String producto(Model model, @RequestParam(required = true) int codigo) {
        Producto encontrado = new Producto();

        for (Producto producto : getProductos()) {
            if (producto.getCodigo() == codigo) {
                encontrado = producto;
            }
        }

        model.addAttribute("producto", encontrado);
        return "producto";
    }

    @GetMapping(value = "/inicio")
    public String inicio(Model model) {

        model.addAttribute("productos", getProductos());
        return "indice";
    }

    private List<Producto> getProductos() {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(new Producto(12332, "Coca-Cola", "/img/cola.png"));
        productos.add(new Producto(15452, "Pepsi", "/img/pepsi.png"));
        productos.add(new Producto(12354, "Fanta", "/img/fanta.jpg"));
        productos.add(new Producto(434343, "Sprite", ""));

        return productos;
    }

/*
 * @GetMapping(path = {"/productos"})
 * public ModelAndView productos(@RequestParam(name = "codigo", required = true) int codigo){
 * 
 *      ModelAndView modelAndView = new ModelAndView();
 *      modelAndView.addObject("producto", getProducto(codigo));
 *      modelAndView.setViewName("producto");
 * 
 *      return modelAndView;
 * }
 * 
 * otra forma mejor:
 * */

  @GetMapping(path = {"/product/{codigo}"})
  public ModelAndView product(@PathVariable(name = "codigo", required = true) int codigo){
  
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("producto", getProducto(codigo));
       modelAndView.setViewName("producto");
  
       return modelAndView;
  }
 
  private Producto getProducto(int codigo){
       List<Producto> productos = getProductos();
       int indexOf = productos.indexOf(new Producto(codigo));
       return productos.get(indexOf);
  }


}

package com.mpm.springprojects.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mpm.springprojects.tienda.model.Proveedor;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {
    
    @RequestMapping(value= {"/lista"})
    public ModelAndView lista(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedores", getProveedores());
        modelAndView.setViewName("proveedores/lista");

        return modelAndView;
    }

    @RequestMapping(value= {"/nuevo"})
    public ModelAndView nuevo(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("proveedores/nuevo");

        return modelAndView;
    }

    @PostMapping(path = {"/guardar"})
    public ModelAndView guardar(Proveedor proveedor){
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("proveedores", addProveedor(proveedor));
        modelAndView.setViewName("proveedores/lista");

        return modelAndView;
    }

    @GetMapping(path = {"/editar/{codigo}"})
    public ModelAndView editar(@PathVariable(name="codigo", required=true) int codigo){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("proveedor", getProveedor(codigo));
        modelAndView.setViewName("proveedores/editar");

        return modelAndView;

    }

    @PostMapping(path = {"/modificar"})
    public ModelAndView modificar(Proveedor proveedor){
        ModelAndView modelAndView = new ModelAndView();

        List<Proveedor> proveedores = getProveedores();
        int indexOf = proveedores.indexOf(proveedor);
        proveedores.set(indexOf, proveedor);

        modelAndView.addObject("proveedores", proveedores);
        modelAndView.setViewName("proveedores/lista");

        return modelAndView;
    }

    @GetMapping(path = {"/borrar/{codigo}"})
    public ModelAndView borrar(@PathVariable(name="codigo", required=true) int codigo){
        ModelAndView modelAndView = new ModelAndView();
        List<Proveedor> proveedores = getProveedores();
        int indexOf = proveedores.indexOf(new Proveedor(codigo));
        proveedores.remove(indexOf);
        modelAndView.addObject("proveedores", proveedores);
        modelAndView.setViewName("proveedores/lista");
        
        return modelAndView;
    }

    private List<Proveedor> addProveedor(Proveedor proveedor){
        List<Proveedor> proveedores = getProveedores();
        proveedores.add(proveedor);
        return proveedores;
    }

    private Proveedor getProveedor(int codigo){
        List<Proveedor> Proveedores = getProveedores();
        int indexOf = Proveedores.indexOf(new Proveedor(codigo));
        
        return Proveedores.get(indexOf);
    }

    private List<Proveedor> getProveedores() {
        ArrayList<Proveedor> Proveedores = new ArrayList<Proveedor>();
        Proveedores.add(new Proveedor(1, "nombre1", "apellidos1"));
        Proveedores.add(new Proveedor(2, "nombre2", "apellidos2"));

        return Proveedores;
    }

}

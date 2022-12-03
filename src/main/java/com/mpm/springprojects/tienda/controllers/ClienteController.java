package com.mpm.springprojects.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mpm.springprojects.tienda.model.Cliente;
import com.mpm.springprojects.tienda.services.ClientesService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    ClientesService clientesService;

    @RequestMapping(value= {"/lista"})
    public ModelAndView lista(){
        List<Cliente> clientes = clientesService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("clientes", clientes);
        modelAndView.setViewName("clientes/lista");

        return modelAndView;
    }

    @RequestMapping(value= {"/nuevo"})
    public ModelAndView nuevo(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clientes/nuevo");

        return modelAndView;
    }

    @PostMapping(path = {"/guardar"})
    public ModelAndView guardar(Cliente cliente){
        ModelAndView modelAndView = new ModelAndView();
        
        //modelAndView.addObject("clientes", addCliente(cliente));
        clientesService.insert(cliente);

        List<Cliente> clientes = clientesService.findAll();
        modelAndView.addObject("clientes", clientes);

        modelAndView.setViewName("clientes/lista");

        return modelAndView;
    }

    @GetMapping(path = {"/editar/{codigo}"})
    public ModelAndView editar(@PathVariable(name="codigo", required=true) int codigo){
        ModelAndView modelAndView = new ModelAndView();
        Cliente cliente = clientesService.findById(codigo);
        modelAndView.addObject("cliente", cliente);
        modelAndView.setViewName("clientes/editar");

        return modelAndView;

    }

    @PostMapping(path = {"/modificar"})
    public ModelAndView modificar(Cliente cliente){

        ModelAndView modelAndView = new ModelAndView();
        
        /*
        List<Cliente> clientes = getClientes();
        int indexOf = clientes.indexOf(cliente);
        clientes.set(indexOf, cliente);

        modelAndView.addObject("clientes", clientes);
        */
        clientesService.update(cliente);
        
        List<Cliente> clientes = clientesService.findAll();
        modelAndView.addObject("clientes", clientes);

        modelAndView.setViewName("clientes/lista");

        return modelAndView;
    }

    @GetMapping(path = {"/borrar/{codigo}"})
    public ModelAndView borrar(@PathVariable(name="codigo", required=true) int codigo){
        ModelAndView modelAndView = new ModelAndView();
        
        // List<Cliente> clientes = getClientes();
        // int indexOf = clientes.indexOf(new Cliente(codigo));
        // clientes.remove(indexOf);

        clientesService.delete(codigo);
        List<Cliente> clientes = clientesService.findAll();
        modelAndView.addObject("clientes", clientes);
        modelAndView.setViewName("clientes/lista");
        
        return modelAndView;
    }

    private List<Cliente> addCliente(Cliente cliente){
        List<Cliente> clientes = getClientes();
        clientes.add(cliente);
        return clientes;
    }

    private Cliente getCliente(int codigo){
        List<Cliente> Clientes = getClientes();
        int indexOf = Clientes.indexOf(new Cliente(codigo));
        
        return Clientes.get(indexOf);
    }

    private List<Cliente> getClientes() {
        ArrayList<Cliente> Clientes = new ArrayList<Cliente>();
        Clientes.add(new Cliente(1, "nombre1", "apellidos1", "corrreo1@email.com","212121A", "123456789", "direccion1", true));
        Clientes.add(new Cliente(2, "nombre2", "apellidos2", "corrreo2@email.com","313131A", "123456789", "direccion2", false));

        return Clientes;
    }

}

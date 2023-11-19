package com.sistemainventario.marca;


import com.sistemainventario.categoria.Categoria;
import com.sistemainventario.categoria.CategoriaRepository;
import com.sistemainventario.producto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/marcas/nueva")
    public String mostrarFormularioDeCrearNuevaMarca(Model modelo){
        List<Categoria> listaCategorias = categoriaRepository.findAll();

        modelo.addAttribute("marca", new Marca());
        modelo.addAttribute("listaCategorias", listaCategorias); //ésto se ha añadido para que en marca_formulario pueda poner la categoría de la marca ??
        return "marca_formulario";
    }

    @PostMapping("/marcas/guardar")
    public String guardarMarca(Marca marca){
        marcaRepository.save(marca);
        return "redirect:/";
    }

    @GetMapping("/marcas")
    public String listarMarcas(Model modelo){
        List<Marca> listaMarcas = marcaRepository.findAll();
        modelo.addAttribute("listaMarcas", listaMarcas);   //listaProductos es el atributo que mandas a categorias.html
        return "marcas";
    }

    @GetMapping("/marcas/eliminar/{id}")
    public String eliminarMarca(@PathVariable("id") Integer id, Model modelo){
        marcaRepository.deleteById(id);
        return "redirect:/marcas";

    }

    @GetMapping("/marcas/editar/{id}")
    public String mostrarFormularioDeModificarMarca(@PathVariable ("id") Integer id, Model modelo){
        Marca marca= marcaRepository.findById(id).get();
        List<Categoria> listaCategorias = categoriaRepository.findAll();

        modelo.addAttribute("marca", marca);
        modelo.addAttribute("listaCategorias", listaCategorias); //ésto se ha añadido para que en marca_formulario pueda poner la categoría de la marca ??
        return "marca_formulario";
    }



}

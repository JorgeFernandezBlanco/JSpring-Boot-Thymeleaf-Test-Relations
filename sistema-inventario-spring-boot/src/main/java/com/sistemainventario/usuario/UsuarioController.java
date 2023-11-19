package com.sistemainventario.usuario;

import com.sistemainventario.categoria.Categoria;
import com.sistemainventario.producto.Producto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model modelo){
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        modelo.addAttribute("listaUsuarios", listaUsuarios);
        return "usuarios";
    }

    @GetMapping("usuarios/nuevo")
    public String mostrarFormularioDeRegistroDeUsuario(Model modelo){
        List<Rol> listaRoles = rolRepository.findAll();

        modelo.addAttribute("usuario",new Usuario());
        modelo.addAttribute("listaRoles",listaRoles); //ésto se ha añadido para que en usuario_formulario pueda poner la categoría del nuevo producto
        return "usuario_formulario";
    }


    @PostMapping("/usuarios/guardar")
    public String guardarUsuarios(Usuario usuario, HttpServletRequest request){
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioDeModificarUsuario(@PathVariable("id") Integer id, Model modelo){
        Usuario usuario= usuarioRepository.findById(id).get();
        modelo.addAttribute("usuario",usuario);

        List<Rol> listaRoles = rolRepository.findAll();
        modelo.addAttribute("listaRoles",listaRoles); //ésto se ha añadido para que en product_formulario pueda poner la categoría del nuevo producto
        return "usuario_formulario";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id, Model modelo){
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";

    }

}

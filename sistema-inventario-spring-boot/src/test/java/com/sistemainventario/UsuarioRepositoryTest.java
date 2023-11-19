package com.sistemainventario;


import com.sistemainventario.categoria.Categoria;
import com.sistemainventario.categoria.CategoriaRepository;
import com.sistemainventario.usuario.Rol;
import com.sistemainventario.usuario.Usuario;
import com.sistemainventario.usuario.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCrearRoles(){
        Rol rolAdmin = new Rol("Administrador");
        Rol rolEditor = new Rol("Editor");
        Rol rolVisitante = new Rol ("Visitante");

        entityManager.persist(rolAdmin);
        entityManager.persist(rolEditor);
        entityManager.persist(rolVisitante);
    }

    @Test
    public void testCrearNuevoUsuarioConUnRol(){
        Rol rolAdmin = entityManager.find(Rol.class, 1);
        Usuario usuario= new Usuario("christian90@gmail.com", "12345");

        usuario.añadirRol(rolAdmin);
        usuarioRepository.save(usuario);


    }

    @Test
    public void testCrearNuevoUsuarioConDosRoles(){
        Rol rolEditor = entityManager.find(Rol.class, 2);
        Rol rolVisitante = entityManager.find(Rol.class, 3);
        Usuario usuario= new Usuario("biaggio@gmail.com", "1232245");

        usuario.añadirRol(rolEditor);
        usuario.añadirRol(rolVisitante);

        usuarioRepository.save(usuario);


    }

    @Test
    public void testAsignarRolAUsuarioExistente(){
        Usuario usuario= usuarioRepository.findById(1).get();
        Rol rolEditor = entityManager.find(Rol.class, 2);

        usuario.añadirRol(rolEditor);

    }


    @Test
    public void testEliminarRolDeUnUsuarioExistente(){
        Usuario usuario= usuarioRepository.findById(1).get();
        Rol rol = new Rol(1); //le pasamos el ID del rol a eliminar

        usuario.eliminarRol(rol);
//para poder eliminarlo tengo que crear el equals y hashCode del Rol(id)
    }


    @Test
    public void testCrearNuevoUsuarioConNuevoRol(){
        Rol rolVendedor = new Rol("Vendedor");
        Usuario usuario= new Usuario("gabrielram22@gmail.com", "12332225");

        usuarioRepository.save(usuario);
        usuario.añadirRol(rolVendedor);
    }


    @Test
    public void testObtenerUsuario(){
        Rol rolVendedor = new Rol("Vendedor");
        Usuario usuario= new Usuario("gabrielram22@gmail.com", "12332225");

        usuarioRepository.save(usuario);
        usuario.añadirRol(rolVendedor);
    }

    @Test
    public void testObetenerUsuario(){
        Usuario usuario = usuarioRepository.findById(1).get();
                entityManager.detach(usuario);

        System.out.println(usuario.getEmail());
        System.out.println(usuario.getRoles());

    }

    @Test
    public void testEliminarUsuario(){
        usuarioRepository.deleteById(2);

    }


}

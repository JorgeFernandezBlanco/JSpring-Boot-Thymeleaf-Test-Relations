package com.sistemainventario;


import com.sistemainventario.carrito.compras.ArticuloCarrito;
import com.sistemainventario.carrito.compras.ArticuloCarritoRepository;
import com.sistemainventario.producto.Producto;
import com.sistemainventario.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ArticuloCarritoTests {
    @Autowired
    private ArticuloCarritoRepository repositorio;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAñadirArticulo(){
        Producto producto = entityManager.find(Producto.class, 2);
        Usuario usuario= entityManager.find(Usuario.class,1);

        ArticuloCarrito articulo = new ArticuloCarrito(1,producto, usuario);
        repositorio.save(articulo);
    }

    @Test
    public void testAñadirMultiplesArticulos(){
        Usuario usuario= new Usuario(1);
        Producto producto1 = new Producto(1);
        Producto producto2 = new Producto(2);


        ArticuloCarrito articulo1 = new ArticuloCarrito(3,producto1, usuario);
        ArticuloCarrito articulo2 = new ArticuloCarrito(5,producto2, usuario);
        repositorio.saveAll(List.of(articulo1,articulo2));
    }

    @Test
    public void testListarArticulos(){
        List<ArticuloCarrito> articulos= repositorio.findAll();
        articulos.forEach(System.out::println);   //para q imprima tengo q tener un toString en ArticuloCarrito
    }
    @Test
    public void testActualizarArticulo(){
        ArticuloCarrito articulo= repositorio.findById(1).get();
        articulo.setCantidad(11);
        articulo.setProducto(new Producto(6));
    }

    @Test
    public void testEliminarArticulo(){
        repositorio.deleteById(1);
    }



}

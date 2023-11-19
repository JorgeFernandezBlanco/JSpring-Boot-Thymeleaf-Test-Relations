package com.sistemainventario.usuario;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false, unique = true)
    private String password;

    @ManyToMany//(cascade = CascadeType.PERSIST, fetch= FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns =@JoinColumn(name = "rol_id" ))
    private Set<Rol> roles =new HashSet<>();   //Set para garantizar que cada Rol sea único para un Usuario

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public void añadirRol(Rol rol){
        this.roles.add(rol);
    }

    public void eliminarRol(Rol rol){
        this.roles.remove(rol);
    }

    public Usuario(Integer id, String email, String password, Set<Rol> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Usuario() {
    }

    public Usuario(String email, String password, Set<Rol> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                '}';
    }
}
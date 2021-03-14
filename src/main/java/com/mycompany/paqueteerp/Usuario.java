/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.paqueteerp;

/**
 *
 * @author Josetexe
 */
public class Usuario {
    private String nombre;
    private int id_usuario;

    public Usuario(String nombre, int id_usuario) {
        this.nombre = nombre;
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", id_usuario=" + id_usuario + '}';
    }
    
    
}

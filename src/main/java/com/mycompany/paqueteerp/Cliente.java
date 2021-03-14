/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.paqueteerp;

/**
 *
 * @author Jose
 */
public class Cliente {
    private String NIFCliente, DomicilioCliente, NombreCliente;

    public Cliente(String NIFCliente, String DomicilioCliente, String NombreCliente) {
        this.NIFCliente = NIFCliente;
        this.DomicilioCliente = DomicilioCliente;
        this.NombreCliente = NombreCliente;
    }

    public String getNIFCliente() {
        return NIFCliente;
    }

    public void setNIFCliente(String NIFCliente) {
        this.NIFCliente = NIFCliente;
    }

    public String getDomicilioCliente() {
        return DomicilioCliente;
    }

    public void setDomicilioCliente(String DomicilioCliente) {
        this.DomicilioCliente = DomicilioCliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" + "NIFCliente=" + NIFCliente + ", DomicilioCliente=" + DomicilioCliente + ", NombreCliente=" + NombreCliente + '}';
    }
    
    
}

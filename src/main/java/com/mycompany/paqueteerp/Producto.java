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
public class Producto {
    
    private String codigo_art;
    private String descri_art;
    private float costo_art;
    private int impuesto_art;
    private float margen;
    private float pvp;

    public Producto(String codigo_art, String descri_art, float costo_art, int impuesto_art, float margen, float pvp) {
        this.codigo_art = codigo_art;
        this.descri_art = descri_art;
        this.costo_art = costo_art;
        this.impuesto_art = impuesto_art;
        this.margen = margen;
        this.pvp = pvp;
    }

    public String getCodigo_art() {
        return codigo_art;
    }

    public void setCodigo_art(String codigo_art) {
        this.codigo_art = codigo_art;
    }

    public String getDescri_art() {
        return descri_art;
    }

    public void setDescri_art(String descri_art) {
        this.descri_art = descri_art;
    }

    public float getCosto_art() {
        return costo_art;
    }

    public void setCosto_art(float costo_art) {
        this.costo_art = costo_art;
    }

    public int getImpuesto_art() {
        return impuesto_art;
    }

    public void setImpuesto_art(int impuesto_art) {
        this.impuesto_art = impuesto_art;
    }

    public float getMargen() {
        return margen;
    }

    public void setMargen(float margen) {
        this.margen = margen;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo_art=" + codigo_art + ", descri_art=" + descri_art + ", costo_art=" + costo_art + ", impuesto_art=" + impuesto_art + ", margen=" + margen + ", pvp=" + pvp + '}';
    }
    
    
    
}

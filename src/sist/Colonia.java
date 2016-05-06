/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sist;

import java.util.ArrayList;

/**
 *
 * @author xaovs
 */
public class Colonia {
    private String nombre;
    private String tipo;
    private String clave;

    public Colonia(String nombre, String tipo, String clave) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getClave() {
        return clave;
    }
    
    public Colonia buscaColonia(ArrayList<Colonia> listaColonias,String cadena,String tipo){
        
        for(Colonia c: listaColonias){
            if(c.getNombre().equalsIgnoreCase(nombre) && c.getTipo().equalsIgnoreCase(tipo))
                return c;
        }
        
        return null;
    }
    
    public ArrayList<Colonia> buscaColonias(ArrayList<Colonia> listaColonias, String nombre){
        ArrayList<Colonia> lista = new ArrayList<>();
        //si hay errores cambiar esto a un foreach normal;
        System.out.println("Buscando:"+nombre+" en la lista");
        listaColonias.stream().filter((c) -> (c.getNombre().contains(nombre))).forEach((c) -> {
            lista.add(c);
            
        });
        
        return lista;
    }
}

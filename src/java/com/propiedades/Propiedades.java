
package com.propiedades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrique Ochoa
 */
public class Propiedades {
    
    private static String CONFIGURACION = "configuracion.properties";
//    private static String RUTA = "pagarplanilla.properties";
//    private static String RUTAPLA = "fechaplanilla.properties";
    private static String RUTA = System.getProperty("jboss.server.temp.dir") + "/pagarplanilla.properties";
    private static String RUTAPLA = System.getProperty("jboss.server.temp.dir") + "/fechaplanilla.properties";
    
    public InputStream getResourcesInputAsStream(String configuracion){
        return Propiedades.class.getResourceAsStream(configuracion);
    }
    
    //Método para cargar el archivo de propiedades
    public Properties cargarPropiedades(){
        
        Properties propiedades = new Properties();
        try {
            propiedades.load(getResourcesInputAsStream(CONFIGURACION));
        } catch (IOException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return propiedades;
    }
    
    //Insertar true o false
    public void insertarPagar(String key, String value){
        Properties propiedades = new Properties();
        OutputStream out;
        try {
            out = new FileOutputStream(RUTA);
            propiedades.setProperty(key, value);
            propiedades.store(out, "");
            System.out.println("Se mete a propiedades");
        } catch (IOException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Cargar fecha de pago de planilla
    public Properties cargarFechaPla(){
        
        Properties propiedades = new Properties();
        InputStream in;
        try {
            in = new FileInputStream(RUTAPLA);
            propiedades.load(in);
        } catch (IOException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return propiedades;
    }
    
    //Cargar si es el pago de planilla es true o false
    public Properties cargarPagarPla(){
        
        Properties propiedades = new Properties();
        InputStream in;
        try {
            in = new FileInputStream(RUTA);
            propiedades.load(in);
        } catch (IOException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return propiedades;
    }
    //Método para modificar propiedades
    public void insertarPropiedades(String key, String value){
        Properties propiedades = new Properties();
        OutputStream out;
        
        try {
            out = new FileOutputStream(RUTA);
            
            propiedades.setProperty(key, value);
            propiedades.store(out, "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Insertar nuevo día de pago de planilla
    public void insertarPla(String key, String value){
        Properties propiedades = new Properties();
        OutputStream out;
        
        try {
            out = new FileOutputStream(RUTAPLA);
            
            propiedades.setProperty(key, value);
            propiedades.store(out, "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

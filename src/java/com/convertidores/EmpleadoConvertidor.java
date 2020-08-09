/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.convertidores;

import com.controladores.EmpleadoControlador;
import com.entidades.AdmEmpEmpleado;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Enrique Ochoa
 */
@FacesConverter(forClass = AdmEmpEmpleado.class)
public class EmpleadoConvertidor implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AdmEmpEmpleado empleado = new AdmEmpEmpleado();
        try {
            EmpleadoControlador estadoControlador = new EmpleadoControlador(empleado);
            return estadoControlador.encontrarPorIdEmpleado(Integer.parseInt(string));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        return ((AdmEmpEmpleado) t).getEmpId().toString();
    }
    
}

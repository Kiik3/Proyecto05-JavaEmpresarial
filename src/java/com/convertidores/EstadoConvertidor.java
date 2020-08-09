
package com.convertidores;

import com.controladores.EstadoControlador;
import com.entidades.AdmEstEstado;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Enrique Ochoa
 */
@FacesConverter(forClass = AdmEstEstado.class)
public class EstadoConvertidor implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AdmEstEstado estado = new AdmEstEstado();
        try {
            EstadoControlador estadoControlador = new EstadoControlador(estado);
            return estadoControlador.encontrarPorId(Integer.parseInt(string));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        return ((AdmEstEstado) t).getEstId().toString();
    }
    
}

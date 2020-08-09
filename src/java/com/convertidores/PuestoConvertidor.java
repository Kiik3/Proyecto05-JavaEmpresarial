
package com.convertidores;

import com.controladores.PuestoControlador;
import com.entidades.AdmPuePuestoTrabajo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Enrique Ochoa
 */
@FacesConverter(forClass = AdmPuePuestoTrabajo.class)
public class PuestoConvertidor implements Converter{
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AdmPuePuestoTrabajo pue = new AdmPuePuestoTrabajo();
        try {
            PuestoControlador depControlador = new PuestoControlador(pue);
            return depControlador.encontrarPorIdPuestos(Integer.parseInt(string));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        return Integer.toString(((AdmPuePuestoTrabajo) t).getAdmPuePuestoTrabajoPK().getPueId());
    }
}

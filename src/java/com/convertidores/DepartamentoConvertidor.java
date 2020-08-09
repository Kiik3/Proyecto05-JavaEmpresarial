
package com.convertidores;

import com.controladores.DepartamentoControlador;
import com.entidades.AdmDepDepartamento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Enrique Ochoa
 */
@FacesConverter(forClass = AdmDepDepartamento.class)
public class DepartamentoConvertidor implements Converter{
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AdmDepDepartamento dep = new AdmDepDepartamento();
        try {
            DepartamentoControlador depControlador = new DepartamentoControlador(dep);
            return depControlador.encontrarPorId(Integer.parseInt(string));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        return ((AdmDepDepartamento) t).getDepId().toString();
    }
}

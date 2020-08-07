
package com.manejadores;

import com.controladores.RolControlador;
import com.entidades.AdmRolRol;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@ViewScoped
public class RolManejador extends AbastractoManejador<AdmRolRol>{
    
    private List<AdmRolRol> roles = new ArrayList<AdmRolRol>();
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmRolRol();
        claseEntidadControlador = new RolControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
        roles = claseEntidadControlador.encontrarEntidades();

    }
    
    @Override
    public void nuevaEntidad(){
        claseEntidad = new AdmRolRol();
        claseEntidadControlador = new RolControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
    }

    public List<AdmRolRol> getRoles() {
        return roles;
    }

    public void setRoles(List<AdmRolRol> roles) {
        this.roles = roles;
    }

}

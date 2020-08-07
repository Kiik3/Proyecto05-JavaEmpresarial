
package com.manejadores;

import com.controladores.PuestoControlador;
import com.entidades.AdmDepDepartamento;
import com.entidades.AdmPuePuestoTrabajo;
import com.entidades.AdmPuePuestoTrabajoPK;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utilidades.Utilidades;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@ViewScoped
public class PuestoManejador extends AbastractoManejador<AdmPuePuestoTrabajo>{
    
    private List<AdmPuePuestoTrabajo> puestos = new ArrayList<AdmPuePuestoTrabajo>();
    private List<AdmPuePuestoTrabajo> puestosSegunDepartamento = new ArrayList<AdmPuePuestoTrabajo>();
    
    @PostConstruct
    @Override
    public void inicializar(){
        claseEntidad = new AdmPuePuestoTrabajo();
        claseEntidadControlador = new PuestoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
        puestos = claseEntidadControlador.encontrarEntidades();
    }
    
    @Override
    public void nuevaEntidad(){
        claseEntidad = new AdmPuePuestoTrabajo();
        claseEntidadControlador = new PuestoControlador(claseEntidad);
        claseEntidadControlador.getEntityManager();
    }

    public void insertar(int id){   
        claseEntidad.setAdmDepDepartamento((AdmDepDepartamento) claseEntidadControlador.encontrarPorId(id));
        int idPuesto = puestos.get(puestos.size()-1).getAdmPuePuestoTrabajoPK().getPueId(); //Se obtiene el ultimo elemento (id puesto) ingresar el siguiente
        AdmPuePuestoTrabajoPK puePK = new AdmPuePuestoTrabajoPK(idPuesto + 1, id); //Se crea la entidad con id de puesto y departamento
        claseEntidad.setAdmPuePuestoTrabajoPK(puePK); 
        
        //Se valida que el salario minimo no sea mayor o igual al mayor
        if(claseEntidad.getPueSalarioMinimo() < claseEntidad.getPueSalarioMaximo()){
            claseEntidadControlador.insertarEntidad();
            Utilidades.mensajeExito("Ingresado correctamente");
            inicializar();
        }
        else{
            Utilidades.mensajeError("Salario mínimo debe ser menor a salario máximo");
        }
        
    }
    
    public void actualizar(int id, AdmPuePuestoTrabajo puesto){  
        //Se ingresa el departamento al que pertenece el puesto
        puesto.setAdmDepDepartamento((AdmDepDepartamento) claseEntidadControlador.encontrarPorId(id));
        
        //Se valida que el salario minimo no sea mayor o igual al mayor
        if(puesto.getPueSalarioMinimo() < puesto.getPueSalarioMaximo()){
            claseEntidadControlador.actualizarEntidad(puesto);
            Utilidades.mensajeExito("Actualizado correctamente");
            inicializar();
        }
        else{
            Utilidades.mensajeError("Salario mínimo debe ser menor a salario máximo");
        }
        
    }
    
    public void puestosSegunDepartamento(int idDep){
        puestosSegunDepartamento = claseEntidadControlador.encontrarPuestos(idDep);
    }

    public List<AdmPuePuestoTrabajo> getPuestos() {
        return puestos;
    }

    public void setPuestos(List<AdmPuePuestoTrabajo> puestos) {
        this.puestos = puestos;
    }

    public List<AdmPuePuestoTrabajo> getPuestosSegunDepartamento() {
        return puestosSegunDepartamento;
    }

    public void setPuestosSegunDepartamento(List<AdmPuePuestoTrabajo> puestosSegunDepartamento) {
        this.puestosSegunDepartamento = puestosSegunDepartamento;
    }

}

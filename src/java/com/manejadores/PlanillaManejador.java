
package com.manejadores;

import com.controladores.HistorialControlador;
import com.controladores.PlanillaControlador;
import com.entidades.AdmDesDescuentoLey;
import com.entidades.AdmEmpEmpleado;
import com.entidades.AdmHisHistorialPago;
import com.entidades.AdmPlaPlanilla;
import com.entidades.AdmRenRenta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import utilidades.Utilidades;

/**
 *
 * @author Enrique Ochoa
 */

@ManagedBean
@ViewScoped
public class PlanillaManejador {
    
    private AdmPlaPlanilla planilla;
    private PlanillaControlador planillaControlador;
    
    private AdmHisHistorialPago historial;
    private HistorialControlador historialControlador;
    
    @ManagedProperty(value = "#{descuentoManejador}")
    private DescuentoManejador descuento;
    
    @ManagedProperty(value = "#{rentaManejador}")
    private RentaManejador renta;
    
    private List<AdmPlaPlanilla> planillas = new ArrayList<AdmPlaPlanilla>();
    private List<AdmHisHistorialPago> elementos;
    
    @PostConstruct
    public void inicializar(){
        planilla = new AdmPlaPlanilla();
        elementos = new ArrayList<AdmHisHistorialPago>();
        planillaControlador = new PlanillaControlador(planilla);
        planillaControlador.getEntityManager();
        elementos = planillaControlador.calcularPlanilla(descuento.getDescuentos(), renta.getRentas());
        planilla.setPlaFecha(new Date());
        planilla.setPlaTotalSalario(planillaControlador.totalSalario);
        planilla.setPlaTotalDescuento(planillaControlador.totalDescuento);
        planilla.setPlaTotalPago(planillaControlador.totalPago);
        planillas = planillaControlador.encontrarEntidades();
        
        historial = new AdmHisHistorialPago();
        

    }
    
    public void pagar(){
        planillaControlador.insertarEntidad();
        System.out.println(planilla.getPlaId());
        System.out.println(elementos);
        for(AdmHisHistorialPago h:elementos){
            System.out.println(h.getHisIdEmpleado());
            h.setPlaId(planilla);
            historialControlador = new HistorialControlador(h);
            historialControlador.getEntityManager();
            historialControlador.insertarEntidad();
        }
        Utilidades.mensajeExito("Planilla pagada correctamente");
    }
    
    public void verDetalles(int id) throws IOException{
        elementos = planillaControlador.verDetallesPlanilla(id);
        System.out.println(id);
        System.out.println(elementos);
        planilla = (AdmPlaPlanilla) planillaControlador.encontrarPorId(id);
//        Utilidades.redireccion("catalogos/historialPagos");
    }

    public AdmPlaPlanilla getPlanilla() {
        return planilla;
    }

    public void setPlanilla(AdmPlaPlanilla planilla) {
        this.planilla = planilla;
    }

    public PlanillaControlador getPlanillaControlador() {
        return planillaControlador;
    }

    public void setPlanillaControlador(PlanillaControlador planillaControlador) {
        this.planillaControlador = planillaControlador;
    }

    public List<AdmPlaPlanilla> getPlanillas() {
        return planillas;
    }

    public void setPlanillas(List<AdmPlaPlanilla> planillas) {
        this.planillas = planillas;
    }

    public List<AdmHisHistorialPago> getElementos() {
        return elementos;
    }

    public void setElementos(List<AdmHisHistorialPago> elementos) {
        this.elementos = elementos;
    }

    public DescuentoManejador getDescuento() {
        return descuento;
    }

    public void setDescuento(DescuentoManejador descuento) {
        this.descuento = descuento;
    }

    public RentaManejador getRenta() {
        return renta;
    }

    public void setRenta(RentaManejador renta) {
        this.renta = renta;
    }
    
}
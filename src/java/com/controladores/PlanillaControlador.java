
package com.controladores;

import com.conexionBD.ConexionBD;
import com.entidades.AdmDesDescuentoLey;
import com.entidades.AdmEmpEmpleado;
import com.entidades.AdmHisHistorialPago;
import com.entidades.AdmPlaPlanilla;
import com.entidades.AdmRenRenta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Enrique Ochoa
 */
public class PlanillaControlador extends AbstractoControlador<AdmPlaPlanilla>{
    
    AdmPlaPlanilla planilla = new AdmPlaPlanilla ();
    public double totalIsss = 0, totalAfp = 0, totalRenta = 0;
    public double totalSalario= 0, totalDescuento = 0, totalPago = 0;
    
    public PlanillaControlador(AdmPlaPlanilla planilla){
        super.ClaseEntidad = planilla;
    }
    
    public List<AdmHisHistorialPago> calcularPlanilla(List<AdmDesDescuentoLey> listaDescuentos, List<AdmRenRenta> listaTramos) {
        List<AdmEmpEmpleado> listaEmpleados = new ArrayList<AdmEmpEmpleado>();
        List<AdmHisHistorialPago> listaHistorial = new ArrayList<AdmHisHistorialPago>();
        
        double isss, afp, renta = 0, pago;
        double salarioRenta;
        
        listaEmpleados = empleadosActivos();
        
        for(AdmEmpEmpleado e: listaEmpleados){
            
            isss = e.getEmpSalario()*(listaDescuentos.get(0).getDesPorcentaje()/100); //Calculo de isss
            isss = Math.round(isss * 100) / 100d;
            afp = e.getEmpSalario()*(listaDescuentos.get(1).getDesPorcentaje()/100); //Calculo de afp
            afp = Math.round(afp * 100) / 100d;
            salarioRenta = e.getEmpSalario() - (isss + afp);

            for(AdmRenRenta t : listaTramos){
                if(salarioRenta >= t.getRenDesde() && salarioRenta <= t.getRenHasta()){
                    //Calculo renta segun tramo
                    renta = ((salarioRenta-t.getRenSobreExceso())*(t.getRenPorcentaje()/100))+t.getRenCuotaFija();
                    renta = Math.round(renta * 100) / 100d;
                }
            }

            pago = e.getEmpSalario() - (isss + afp + renta); //Calculo de pago total a empleado
            pago = Math.round(pago * 100) / 100d;

            AdmHisHistorialPago h = new AdmHisHistorialPago();
            h.setHisIdEmpleado(e.getEmpId());
            h.setHisNombreEmpleado(e.getEmpNombre());
            h.setHisApellidoEmpleado(e.getEmpApellido());
            h.setHisSalario(e.getEmpSalario());
            h.setHisIsss(isss);
            h.setHisAfp(afp);
            h.setHisRenta(renta);
            h.setHisPago(pago);
            
            listaHistorial.add(h); //Agregando un nuevo empleado con sus pagos a la planilla
            
            totalSalario = totalSalario + e.getEmpSalario();
            totalIsss = totalIsss + isss;
            totalAfp = totalAfp + afp;
            totalRenta = totalRenta + renta;
        }
        totalDescuento = totalIsss + totalAfp + totalRenta;
        totalPago = totalSalario - totalDescuento;
        
        
        planilla.setPlaTotalSalario(totalSalario);
        planilla.setPlaTotalDescuento(totalDescuento);
        planilla.setPlaTotalPago(totalPago);
        
        return listaHistorial;
    }
    
    public List<AdmEmpEmpleado> empleadosActivos(){
        em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM AdmEmpEmpleado u WHERE u.estId.estId = :id");
            q.setParameter("id", 1);

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
    public List<AdmHisHistorialPago> verDetallesPlanilla(int id){
        em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM AdmHisHistorialPago u WHERE u.plaId.plaId = :id");
            q.setParameter("id", id);

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
    @Override
    public EntityManager getEntityManager() {
        return ConexionBD.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public Object encontrarPorId(int id) {
        em = getEntityManager();
        AdmPlaPlanilla pueEncontrado = new AdmPlaPlanilla();
        try {
            Query q = em.createQuery("SELECT u FROM AdmPlaPlanilla u WHERE u.plaId = :id");
            q.setParameter("id", id);
            pueEncontrado = (AdmPlaPlanilla) q.getSingleResult();
            
            return pueEncontrado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            em.close();
        }
    }
    
    
}

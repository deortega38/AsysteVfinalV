/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.Login;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Daniel
 */
@Stateless
public class AsistenciaFacade extends AbstractFacade<Asistencia> {

    @PersistenceContext(unitName = "Asyste6PU")
    private EntityManager em;
    String id;
    Asistencia d;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsistenciaFacade() {
        super(Asistencia.class);
    }

    public List<Asistencia> obtenerasistencia(String cadena) {
        Query q = em.createNativeQuery("SELECT * FROM asyste.asistencia where Persona_IdPersona Like " + cadena + ";", Asistencia.class);
        List<Asistencia> lts = q.getResultList();
        return lts;
    }
    
    //Metodo nuevo
    public List<Asistencia> getAsistenciasAprendiz(Integer IdPersona){
        List<Asistencia> asistenciasAprendiz = new ArrayList<>();
        asistenciasAprendiz = getEntityManager().createNamedQuery("Asistencia.getAsistenciasAprendiz", Asistencia.class).setParameter("IdPersona", IdPersona).getResultList();
        return asistenciasAprendiz;
    }
    
    public List<Asistencia> obtenerasitencia(int cadena) {
        List<Asistencia> asistencias = new ArrayList<>();
        Query q = em.createNativeQuery("SELECT * FROM asyste.asistencia where  Eventos_idEventos =" + cadena + ";", Asistencia.class);
        asistencias = q.getResultList();
        return asistencias;
    }

}

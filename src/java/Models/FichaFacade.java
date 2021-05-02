/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

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
public class FichaFacade extends AbstractFacade<Ficha> {

    @PersistenceContext(unitName = "Asyste6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FichaFacade() {
        super(Ficha.class);
    }

    public List<Ficha> obtenerfichas(int cadena) {
        List<Ficha> asistenciasAprendiz = new ArrayList<>();
        Query q = em.createNativeQuery("SELECT * FROM asyste.ficha where  Programa_idPrograma =" + cadena + ";", Ficha.class);
        asistenciasAprendiz = q.getResultList();
        return asistenciasAprendiz;
    }

}

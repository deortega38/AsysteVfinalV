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
public class EventosFacade extends AbstractFacade<Eventos> {

    @PersistenceContext(unitName = "Asyste6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventosFacade() {
        super(Eventos.class);
    }
    public List<Eventos> obtenerfichas(int cadena) {
        List<Eventos> eventoslist = new ArrayList<>();
        Query q = em.createNativeQuery("SELECT * FROM asyste.eventos where  Ficha_idFicha =" + cadena + ";", Eventos.class);
        eventoslist = q.getResultList();
        return eventoslist;
    }
    
}

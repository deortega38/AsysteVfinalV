/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel
 */
@Stateless
public class TipodedocumentoFacade extends AbstractFacade<Tipodedocumento> {

    @PersistenceContext(unitName = "Asyste6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipodedocumentoFacade() {
        super(Tipodedocumento.class);
    }
    
}

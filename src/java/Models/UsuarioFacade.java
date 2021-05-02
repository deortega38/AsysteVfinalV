/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "Asyste6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public Usuario encontrarUsuarioxLogin(String name) {
        Query q = em.createNamedQuery("Usuario.findByUsuario", Usuario.class).setParameter("usuario", name);
        
        List<Usuario> listado = q.getResultList();
        if (!listado.isEmpty()) {
            return listado.get(0);
        }
        
        return null;
    }
    
}

package Controllers;

import Models.Eventos;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Models.EventosFacade;
import Models.Ficha;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("eventosController")
@SessionScoped
public class EventosController implements Serializable {

    @EJB
    private Models.EventosFacade ejbFacade;
    private List<Eventos> items = null;
    private Eventos selected;

    public EventosController() {
    }

    public Eventos getSelected() {
        return selected;
    }

    public void setSelected(Eventos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EventosFacade getFacade() {
        return ejbFacade;
    }

    public Eventos prepareCreate() {
        selected = new Eventos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Lang/Bundle").getString("EventosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Lang/Bundle").getString("EventosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Lang/Bundle").getString("EventosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Eventos> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Lang/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Lang/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Eventos getEventos(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Eventos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Eventos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Eventos.class)
    public static class EventosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EventosController controller = (EventosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "eventosController");
            return controller.getEventos(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Eventos) {
                Eventos o = (Eventos) object;
                return getStringKey(o.getIdEventos());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Eventos.class.getName()});
                return null;
            }
        }

    }
        public Integer numeroEvento() {
        //se crea una varible y su parseo de tipo entero por que accedo a un dato en especifico
        //y no aun objeto en general
        int numeroEvento = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IdEventos");
        System.out.println("Id en ficha: " + numeroEvento);
        return numeroEvento;
    }

    //Metodo para redireccionar las ichas dependiendo del id del Programa
    //mediante la opcion ingresar
    //=====================Metodo para 
    public String renderEventos(Integer IdEvento) {
        System.out.print(IdEvento);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("IdEventos", IdEvento);
        return "ins3";
    }

    //Metodo para capturar el id del Programa elegido.
    public Integer getIdEventos() {
        int Idevento = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IdEventos");
        return Idevento;
    }

    public List<Eventos> llenareventos() {
        FichaController fichaId = new FichaController();
        items = ejbFacade.obtenerfichas(fichaId.getIdProgramaElegido());
        return items;
    }
    

}

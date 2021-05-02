package Controllers;

import Models.Ficha;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Models.Asistencia;
import Models.FichaFacade;
import Models.Programa;
import Models.Usuario;

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

@Named("fichaController")
@SessionScoped
public class FichaController implements Serializable {

    @EJB
    private Models.FichaFacade ejbFacade;
    private List<Ficha> items = null;
    private Ficha selected;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FichaController() {
    }

    public Ficha getSelected() {
        return selected;
    }

    public void setSelected(Ficha selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FichaFacade getFacade() {
        return ejbFacade;
    }

    public Ficha prepareCreate() {
        selected = new Ficha();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Lang/Bundle").getString("FichaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Lang/Bundle").getString("FichaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Lang/Bundle").getString("FichaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Ficha> getItems() {
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

    public Ficha getFicha(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Ficha> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Ficha> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Ficha.class)
    public static class FichaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FichaController controller = (FichaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fichaController");
            return controller.getFicha(getKey(value));
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
            if (object instanceof Ficha) {
                Ficha o = (Ficha) object;
                return getStringKey(o.getIdFicha());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ficha.class.getName()});
                return null;
            }
        }

    }



    public Integer numeroPrograma() {
        //se crea una varible y su parseo de tipo entero por que accedo a un dato en especifico
        //y no aun objeto en general
        int numeroEvento = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IdFicha");
        System.out.println("Id en ficha: " + numeroEvento);
        return numeroEvento;
    }

    //Metodo para redireccionar las ichas dependiendo del id del Programa
    //mediante la opcion ingresar
    //=====================Metodo para 
    public String renderFichas(Integer IdEvento) {
        System.out.print(IdEvento);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("IdFicha", IdEvento);
        return "ins2";
    }

    //Metodo para capturar el id del Programa elegido.
    public Integer getIdProgramaElegido() {
        int Idevento = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IdFicha");
        return Idevento;
    }
    
    public List<Ficha> llenarfichas() {
        ProgramaController programaId = new ProgramaController();
        items = ejbFacade.obtenerfichas(programaId.getIdProgramaElegido());
        return items;
    }

}

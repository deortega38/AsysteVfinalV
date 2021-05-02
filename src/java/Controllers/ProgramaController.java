package Controllers;

import Models.Programa;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Models.ProgramaFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("programaController")
@SessionScoped
public class ProgramaController implements Serializable {

    @EJB
    private Models.ProgramaFacade ejbFacade;
    private List<Programa> items = null;
    Programa selected;
    private Programa programa;

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public ProgramaController() {
    }

    @PostConstruct
    void init() {
        programa = new Programa();
    }


    //my changes for render to a page
    public String idprograma(Integer Idprograma) {
        //int numeroSesion;
        //numeroSesion = IdSesion;
        System.out.println(Idprograma);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Idprograma", Idprograma);
        return "/Views/ficha/List";
    }

    

    public Integer numeroPrograma() {
        //se crea una varible y su parseo de tipo entero por que accedo a un dato en especifico
        //y no aun objeto en general
        int numeroFicha = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Idprograma");
        System.out.println("Id en ficha: " + numeroFicha);
        return numeroFicha;
    }
    //Metodo para redireccionar las ichas dependiendo del id del Programa
    //mediante la opcion ingresar
    //=====================Metodo para 
    public String renderFichas(Integer IdPrograma){
        System.out.print(IdPrograma);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("IdPrograma", IdPrograma);
        return "ins1";
    }
    //Metodo para capturar el id del Programa elegido.
    public Integer getIdProgramaElegido(){
        int idPrograma = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IdPrograma"); 
        return idPrograma;
    }

    public Programa getSelected() {
        return selected;
    }

    public void setSelected(Programa selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProgramaFacade getFacade() {
        return ejbFacade;
    }

    public Programa prepareCreate() {
        selected = new Programa();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Lang/Bundle").getString("ProgramaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Lang/Bundle").getString("ProgramaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Lang/Bundle").getString("ProgramaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Programa> getItems() {
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

    public Programa getPrograma(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Programa> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Programa> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Programa.class)
    public static class ProgramaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProgramaController controller = (ProgramaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "programaController");
            return controller.getPrograma(getKey(value));
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
            if (object instanceof Programa) {
                Programa o = (Programa) object;
                return getStringKey(o.getIdPrograma());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Programa.class.getName()});
                return null;
            }
        }

    }

}

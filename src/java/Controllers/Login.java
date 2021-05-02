/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Asistencia;
import Models.Usuario;
import Models.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Daniel
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    @EJB
    private UsuarioFacade usuFacade;
    private Models.AsistenciaFacade ejbFacade;
    //private AsistenciaFacade asiFacade;

    /*public AsistenciaFacade getAsiFacade() {
        return asiFacade;
    }

    public void setAsiFacade(AsistenciaFacade asiFacade) {
        this.asiFacade = asiFacade;
    }*/
    private String name;
    private String pass;
    private String tipouser;
    String persona;
    private Usuario usuarioAutenticado;
    private Asistencia asiAutenticado;

    /*public void idpersona(){
        String idpersona = persona;
    }*/
    public Asistencia getAsiAutenticado() {
        return asiAutenticado;
    }

    public void setAsiAutenticado(Asistencia asiAutenticado) {
        this.asiAutenticado = asiAutenticado;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getTipouser() {
        return tipouser;
    }

    public void setTipouser(String tipouser) {
        this.tipouser = tipouser;
    }

    public UsuarioFacade getUsuFacade() {
        return usuFacade;
    }

    public void setUsuFacade(UsuarioFacade usuFacade) {
        this.usuFacade = usuFacade;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario usu() {
        usuarioAutenticado = usuFacade.encontrarUsuarioxLogin(name);

        return usuFacade.encontrarUsuarioxLogin(name);
    }

    //=============================Metodo para conseguir u obtener la persona
    public Usuario usuarioSesion() {
        //Consigue el usuario que ha iniciado sesion pero antes debe ponoerse
        Usuario person = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioAutenticado");
        if (person == null) {

        }
        return person;
    }

    public String autnticar() {
        try {
            usuarioAutenticado = usuFacade.encontrarUsuarioxLogin(name);

            //asiAutenticado = asiFacade.encontrarAsistenciaxid();
            if (usuarioAutenticado != null) {
                //1. primerro se use GetSessionMap.put
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioAutenticado);
                if (usuarioAutenticado.getContraseña().equals(pass)) {
                    tipouser = usuarioAutenticado.getTipodeusuarioidTipodeusuario().toString();
                    persona = usuarioAutenticado.getPersonaIdPersona().toString();
                    if (tipouser.equalsIgnoreCase("aprendiz")) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ingresaste como aprendiz", "ingresaste como aprendize"));
                        return "aprendiz";
                    } else if (tipouser.equalsIgnoreCase("instructor")) {
                        return "instructor";
                    } else if (tipouser.equalsIgnoreCase("administrador")) {
                        return "admin";
                    }
//return "ingresar?faces-redirect=true";
                    return null;
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La contraseña no corresponde", "La contraseña no corresponde"));
                return null;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario no existe", "El usuario no existe"));
            return null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error en la conexión", "Error en la conexión"));
            return null;
        }
    }

    public String cerrar() {
        AsistenciaController asi = new AsistenciaController();
        asi.setId("0");
        asi.setVisible(0);
        return "/index?faces-redirect=false";
    }
    public String home() {
        return "/View/programa/List?faces-redirect=false";
    }
    

    public Login() {
    }

}

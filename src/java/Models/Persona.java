/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona")
    , @NamedQuery(name = "Persona.findByNombres", query = "SELECT p FROM Persona p WHERE p.nombres = :nombres")
    , @NamedQuery(name = "Persona.findByApellidos", query = "SELECT p FROM Persona p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Persona.findByDocumento", query = "SELECT p FROM Persona p WHERE p.documento = :documento")
    , @NamedQuery(name = "Persona.findByCorreo", query = "SELECT p FROM Persona p WHERE p.correo = :correo")
    , @NamedQuery(name = "Persona.findByTelefono", query = "SELECT p FROM Persona p WHERE p.telefono = :telefono")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPersona")
    private Integer idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Documento")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 320)
    @Column(name = "Correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Telefono")
    private String telefono;
    @JoinColumn(name = "Eventos_idEventos", referencedColumnName = "idEventos")
    @ManyToOne(optional = false)
    private Eventos eventosidEventos;
    @JoinColumn(name = "Genero_idGenero", referencedColumnName = "idGenero")
    @ManyToOne(optional = false)
    private Genero generoidGenero;
    @JoinColumn(name = "Tipodedocumento_idTipodedocumento", referencedColumnName = "idTipodedocumento")
    @ManyToOne(optional = false)
    private Tipodedocumento tipodedocumentoidTipodedocumento;
    @JoinColumn(name = "Tipodepersona_idTipodepersona", referencedColumnName = "idTipodepersona")
    @ManyToOne(optional = false)
    private Tipodepersona tipodepersonaidTipodepersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaIdPersona")
    private Collection<Asistencia> asistenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaIdPersona")
    private Collection<Usuario> usuarioCollection;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer idPersona, String nombres, String apellidos, String documento, String correo, String telefono) {
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Eventos getEventosidEventos() {
        return eventosidEventos;
    }

    public void setEventosidEventos(Eventos eventosidEventos) {
        this.eventosidEventos = eventosidEventos;
    }

    public Genero getGeneroidGenero() {
        return generoidGenero;
    }

    public void setGeneroidGenero(Genero generoidGenero) {
        this.generoidGenero = generoidGenero;
    }

    public Tipodedocumento getTipodedocumentoidTipodedocumento() {
        return tipodedocumentoidTipodedocumento;
    }

    public void setTipodedocumentoidTipodedocumento(Tipodedocumento tipodedocumentoidTipodedocumento) {
        this.tipodedocumentoidTipodedocumento = tipodedocumentoidTipodedocumento;
    }

    public Tipodepersona getTipodepersonaidTipodepersona() {
        return tipodepersonaidTipodepersona;
    }

    public void setTipodepersonaidTipodepersona(Tipodepersona tipodepersonaidTipodepersona) {
        this.tipodepersonaidTipodepersona = tipodepersonaidTipodepersona;
    }

    @XmlTransient
    public Collection<Asistencia> getAsistenciaCollection() {
        return asistenciaCollection;
    }

    public void setAsistenciaCollection(Collection<Asistencia> asistenciaCollection) {
        this.asistenciaCollection = asistenciaCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return documento;
    }
    
}

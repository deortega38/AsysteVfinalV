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
@Table(name = "tipodedocumento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipodedocumento.findAll", query = "SELECT t FROM Tipodedocumento t")
    , @NamedQuery(name = "Tipodedocumento.findByIdTipodedocumento", query = "SELECT t FROM Tipodedocumento t WHERE t.idTipodedocumento = :idTipodedocumento")
    , @NamedQuery(name = "Tipodedocumento.findByNombre", query = "SELECT t FROM Tipodedocumento t WHERE t.nombre = :nombre")})
public class Tipodedocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipodedocumento")
    private Integer idTipodedocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipodedocumentoidTipodedocumento")
    private Collection<Persona> personaCollection;

    public Tipodedocumento() {
    }

    public Tipodedocumento(Integer idTipodedocumento) {
        this.idTipodedocumento = idTipodedocumento;
    }

    public Tipodedocumento(Integer idTipodedocumento, String nombre) {
        this.idTipodedocumento = idTipodedocumento;
        this.nombre = nombre;
    }

    public Integer getIdTipodedocumento() {
        return idTipodedocumento;
    }

    public void setIdTipodedocumento(Integer idTipodedocumento) {
        this.idTipodedocumento = idTipodedocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipodedocumento != null ? idTipodedocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodedocumento)) {
            return false;
        }
        Tipodedocumento other = (Tipodedocumento) object;
        if ((this.idTipodedocumento == null && other.idTipodedocumento != null) || (this.idTipodedocumento != null && !this.idTipodedocumento.equals(other.idTipodedocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}

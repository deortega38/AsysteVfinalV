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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "tipodepersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipodepersona.findAll", query = "SELECT t FROM Tipodepersona t")
    , @NamedQuery(name = "Tipodepersona.findByIdTipodepersona", query = "SELECT t FROM Tipodepersona t WHERE t.idTipodepersona = :idTipodepersona")
    , @NamedQuery(name = "Tipodepersona.findByNombre", query = "SELECT t FROM Tipodepersona t WHERE t.nombre = :nombre")})
public class Tipodepersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipodepersona")
    private Integer idTipodepersona;
    @Size(max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipodepersonaidTipodepersona")
    private Collection<Persona> personaCollection;

    public Tipodepersona() {
    }

    public Tipodepersona(Integer idTipodepersona) {
        this.idTipodepersona = idTipodepersona;
    }

    public Integer getIdTipodepersona() {
        return idTipodepersona;
    }

    public void setIdTipodepersona(Integer idTipodepersona) {
        this.idTipodepersona = idTipodepersona;
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
        hash += (idTipodepersona != null ? idTipodepersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodepersona)) {
            return false;
        }
        Tipodepersona other = (Tipodepersona) object;
        if ((this.idTipodepersona == null && other.idTipodepersona != null) || (this.idTipodepersona != null && !this.idTipodepersona.equals(other.idTipodepersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}

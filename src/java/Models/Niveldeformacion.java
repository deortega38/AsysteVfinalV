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
@Table(name = "niveldeformacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Niveldeformacion.findAll", query = "SELECT n FROM Niveldeformacion n")
    , @NamedQuery(name = "Niveldeformacion.findByIdNiveldeformacion", query = "SELECT n FROM Niveldeformacion n WHERE n.idNiveldeformacion = :idNiveldeformacion")
    , @NamedQuery(name = "Niveldeformacion.findByNombre", query = "SELECT n FROM Niveldeformacion n WHERE n.nombre = :nombre")})
public class Niveldeformacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNiveldeformacion")
    private Integer idNiveldeformacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "niveldeformacionidNiveldeformacion")
    private Collection<Programa> programaCollection;

    public Niveldeformacion() {
    }

    public Niveldeformacion(Integer idNiveldeformacion) {
        this.idNiveldeformacion = idNiveldeformacion;
    }

    public Niveldeformacion(Integer idNiveldeformacion, String nombre) {
        this.idNiveldeformacion = idNiveldeformacion;
        this.nombre = nombre;
    }

    public Integer getIdNiveldeformacion() {
        return idNiveldeformacion;
    }

    public void setIdNiveldeformacion(Integer idNiveldeformacion) {
        this.idNiveldeformacion = idNiveldeformacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Programa> getProgramaCollection() {
        return programaCollection;
    }

    public void setProgramaCollection(Collection<Programa> programaCollection) {
        this.programaCollection = programaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNiveldeformacion != null ? idNiveldeformacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Niveldeformacion)) {
            return false;
        }
        Niveldeformacion other = (Niveldeformacion) object;
        if ((this.idNiveldeformacion == null && other.idNiveldeformacion != null) || (this.idNiveldeformacion != null && !this.idNiveldeformacion.equals(other.idNiveldeformacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}

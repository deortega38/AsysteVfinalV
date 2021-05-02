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
@Table(name = "tipodeusuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipodeusuario.findAll", query = "SELECT t FROM Tipodeusuario t")
    , @NamedQuery(name = "Tipodeusuario.findByIdTipodeusuario", query = "SELECT t FROM Tipodeusuario t WHERE t.idTipodeusuario = :idTipodeusuario")
    , @NamedQuery(name = "Tipodeusuario.findByNombre", query = "SELECT t FROM Tipodeusuario t WHERE t.nombre = :nombre")})
public class Tipodeusuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipodeusuario")
    private Integer idTipodeusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipodeusuarioidTipodeusuario")
    private Collection<Usuario> usuarioCollection;

    public Tipodeusuario() {
    }

    public Tipodeusuario(Integer idTipodeusuario) {
        this.idTipodeusuario = idTipodeusuario;
    }

    public Tipodeusuario(Integer idTipodeusuario, String nombre) {
        this.idTipodeusuario = idTipodeusuario;
        this.nombre = nombre;
    }

    public Integer getIdTipodeusuario() {
        return idTipodeusuario;
    }

    public void setIdTipodeusuario(Integer idTipodeusuario) {
        this.idTipodeusuario = idTipodeusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idTipodeusuario != null ? idTipodeusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodeusuario)) {
            return false;
        }
        Tipodeusuario other = (Tipodeusuario) object;
        if ((this.idTipodeusuario == null && other.idTipodeusuario != null) || (this.idTipodeusuario != null && !this.idTipodeusuario.equals(other.idTipodeusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}

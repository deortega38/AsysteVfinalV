/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")
    , @NamedQuery(name = "Asistencia.findByIdAsistencia", query = "SELECT a FROM Asistencia a WHERE a.idAsistencia = :idAsistencia")
    , @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Asistencia.findByHoradeingreso", query = "SELECT a FROM Asistencia a WHERE a.horadeingreso = :horadeingreso")
    , @NamedQuery(name = "Asistencia.findByHoradesalida", query = "SELECT a FROM Asistencia a WHERE a.horadesalida = :horadesalida")
    , @NamedQuery(name = "Asistencia.findByJustificacion", query = "SELECT a FROM Asistencia a WHERE a.justificacion = :justificacion")
        //Consulta prara filtrar asistencias por aprendiz
    , @NamedQuery(name = "Asistencia.getAsistenciasAprendiz", query = "SELECT a FROM Asistencia a WHERE a.personaIdPersona.idPersona = :IdPersona")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAsistencia")
    private Integer idAsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Hora_de_ingreso")
    private String horadeingreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Hora_de_salida")
    private String horadesalida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Justificacion")
    private String justificacion;
    @JoinColumn(name = "Estado_idEstado", referencedColumnName = "idEstado")
    @ManyToOne(optional = false)
    private Estado estadoidEstado;
    @JoinColumn(name = "Eventos_idEventos", referencedColumnName = "idEventos")
    @ManyToOne(optional = false)
    private Eventos eventosidEventos;
    @JoinColumn(name = "Persona_IdPersona", referencedColumnName = "IdPersona")
    @ManyToOne(optional = false)
    private Persona personaIdPersona;

    public Asistencia() {
    }

    public Asistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Asistencia(Integer idAsistencia, Date fecha, String horadeingreso, String horadesalida, String justificacion) {
        this.idAsistencia = idAsistencia;
        this.fecha = fecha;
        this.horadeingreso = horadeingreso;
        this.horadesalida = horadesalida;
        this.justificacion = justificacion;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoradeingreso() {
        return horadeingreso;
    }

    public void setHoradeingreso(String horadeingreso) {
        this.horadeingreso = horadeingreso;
    }

    public String getHoradesalida() {
        return horadesalida;
    }

    public void setHoradesalida(String horadesalida) {
        this.horadesalida = horadesalida;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Estado getEstadoidEstado() {
        return estadoidEstado;
    }

    public void setEstadoidEstado(Estado estadoidEstado) {
        this.estadoidEstado = estadoidEstado;
    }

    public Eventos getEventosidEventos() {
        return eventosidEventos;
    }

    public void setEventosidEventos(Eventos eventosidEventos) {
        this.eventosidEventos = eventosidEventos;
    }

    public Persona getPersonaIdPersona() {
        return personaIdPersona;
    }

    public void setPersonaIdPersona(Persona personaIdPersona) {
        this.personaIdPersona = personaIdPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsistencia != null ? idAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idAsistencia == null && other.idAsistencia != null) || (this.idAsistencia != null && !this.idAsistencia.equals(other.idAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return fecha+"";
    }
    
    
    
}

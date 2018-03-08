package Model;
// Generated 01-09-2018 11:33:30 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "ESTADO_NEGOCIO",
        schema = "ADMIN_CRM"
)

@NamedQueries(
        {
            @NamedQuery(name = "EstadoNegocio.buscarTodos", query = "SELECT d FROM EstadoNegocio d")
            ,
            @NamedQuery(name = "EstadoNegocio.buscarPorNombre", query = "SELECT d FROM EstadoNegocio d WHERE Estado_Negocio= :EstadoNegocio")
        }
)
public class EstadoNegocio implements java.io.Serializable {

    private Integer idEstadoNegocio;
    private String estadoNegocio;
    private Set<Negocio> negocios = new HashSet<Negocio>(0);

    public EstadoNegocio() {
    }

    public EstadoNegocio(String estadoNegocio, Set<Negocio> negocios) {
        this.estadoNegocio = estadoNegocio;
        this.negocios = negocios;
    }

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "ESTADONEGOCIO_AUTO_INC")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @Column(name = "ID_ESTADO_NEGOCIO", unique = true, nullable = false, precision = 22, scale = 0)
    public Integer getIdEstadoNegocio() {
        return this.idEstadoNegocio;
    }

    public void setIdEstadoNegocio(Integer idEstadoNegocio) {
        this.idEstadoNegocio = idEstadoNegocio;
    }

    @Column(name = "ESTADO_NEGOCIO", length = 40)
    public String getEstadoNegocio() {
        return this.estadoNegocio;
    }

    public void setEstadoNegocio(String estadoNegocio) {
        this.estadoNegocio = estadoNegocio;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estadoNegocio")
    public Set<Negocio> getNegocios() {
        return this.negocios;
    }

    public void setNegocios(Set<Negocio> negocios) {
        this.negocios = negocios;
    }

}

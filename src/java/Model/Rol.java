package Model;

import java.math.BigDecimal;
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
@Table(name = "ROL",
         schema = "ADMIN_CRM"
)
@NamedQueries(
        {
            @NamedQuery(name = "Rol.BuscarTodos", query = "SELECT d FROM Rol d")
            ,
            @NamedQuery(name = "Rol.buscarPorNombre", query = "SELECT d FROM Rol d WHERE Nombre= :Rol")
        }
)
public class Rol implements java.io.Serializable {

    private Integer idRol;
    private String nombre;
    private Set<Cargo> cargos = new HashSet<Cargo>(0);

    public Rol() {
    }

    public Rol(String nombre, Set<Cargo> cargos) {
        this.nombre = nombre;
        this.cargos = cargos;
    }

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "ROL_AUTO_INC")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @Column(name = "ID_ROL", unique = true, nullable = false, precision = 22, scale = 0)
    public Integer getIdRol() {
        return this.idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Column(name = "NOMBRE", length = 50)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
    public Set<Cargo> getCargos() {
        return this.cargos;
    }

    public void setCargos(Set<Cargo> cargos) {
        this.cargos = cargos;
    }

}

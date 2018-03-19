package Model;

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
@Table(name = "USUARIOS",
         schema = "ADMIN_CRM"
)
@NamedQueries(
        {
            @NamedQuery(name = "Usuarios.buscarTodos", query = "SELECT d FROM Usuarios d")
            , // @NamedQuery(name="Usuarios.buscarPorId", query ="SELECT d FROM Usuarios d WHERE d.idDep= :idDep"),
            @NamedQuery(name = "Usuarios.buscarPorCorreo", query = "SELECT d FROM Usuarios d WHERE d.correo= :correo")
        }
)
public class Usuarios implements java.io.Serializable {

    private Integer userId;
    private String nomUsuario;
    private String password;
    private String correo;
    private String estado;
    private Set<Empleados> empleadoses = new HashSet<Empleados>(0);

    public Usuarios() {
    }

    public Usuarios(String nomUsuario, String password, String correo, String estado, Set<Empleados> empleadoses) {
        this.nomUsuario = nomUsuario;
        this.password = password;
        this.correo = correo;
        this.estado = estado;
        this.empleadoses = empleadoses;
    }

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "USUARIO_AUTO_INC")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @Column(name = "USER_ID", unique = true, nullable = false, precision = 22, scale = 0)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Column(name = "NOM_USUARIO", length = 200)
    public String getNomUsuario() {
        return this.nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    @Column(name = "PASSWORD", length = 200)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "CORREO", length = 50)
    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Column(name = "ESTADO", length = 5)
    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarios")
    public Set<Empleados> getEmpleadoses() {
        return this.empleadoses;
    }

    public void setEmpleadoses(Set<Empleados> empleadoses) {
        this.empleadoses = empleadoses;
    }

}

package Model;
// Generated 12-12-2017 03:23:15 PM by Hibernate Tools 4.3.1

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
@Table(name = "USUARIOS",
         schema = "ADMIN_CRM"
)
@NamedQueries(
        {
            @NamedQuery(name = "Usuarios.buscarTodos", query = "SELECT d FROM Usuarios d"), // @NamedQuery(name="Usuarios.buscarPorId", query ="SELECT d FROM Usuarios d WHERE d.idDep= :idDep"),
        //@NamedQuery(name="Usuarios.buscarPorCorreo", query="SELECT d FROM Usuarios d WHERE d.Nom_Usuario= :nomUsuario")
        }
)
public class Usuarios implements java.io.Serializable {
    
    private int userId;
    private String nomUsuario;
    private String password;
    private String rol;
    private String correo;
    private Empleados empleados;
    private Set<Empleados> empleadoses = new HashSet<Empleados>(0);

    public Usuarios() {
        //this.userId=0;
        this.empleados = new Empleados();
    }
  
    public Usuarios(String nomUsuario, String password, String rol, String correo, Set<Empleados> empleadoses) {
        //this.userId = userId;
        this.nomUsuario = nomUsuario;
        this.password = password;
        this.rol = rol;
        this.correo = correo;
        this.empleadoses = empleadoses;
    }
    
    @Id
    @SequenceGenerator(name="seq", sequenceName="USUARIO_AUTO_INC")
    @GeneratedValue(strategy= GenerationType.AUTO, generator="seq")
    @Column(name = "USER_ID", unique = true, nullable = false, precision = 22, scale = 0)
    public Integer getUserId() {
        return this.userId;
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

    @Column(name = "ROL", length = 200)
    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Column(name = "CORREO", length = 50)
    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarios")
    public Set<Empleados> getEmpleadoses() {
        return this.empleadoses;
    }

    public void setEmpleadoses(Set<Empleados> empleadoses) {
        this.empleadoses = empleadoses;
    }

}

package Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "EMPLEADOS",
         schema = "ADMIN_CRM"
)
@NamedQueries(
        {
            @NamedQuery(name = "Empleados.buscarTodos", query = "SELECT d FROM Empleados d")
        }
)
public class Empleados implements java.io.Serializable {

    private Integer idEmpleado;
    private Cargo cargo;
    private Usuarios usuarios;
    private String nombre;
    private String apellido;
    private Date creaciondate;
    private Date modificaciondate;
    private String creadopor;
    private String modificadopor;
    private Date fechaNacimiento;
    private String telefonoMovil;
    private String telefonoFijo;
    private String email;
    private Set<Oportunidades> oportunidadeses = new HashSet<Oportunidades>(0);

    public Empleados() {
        this.cargo = new Cargo();
        this.usuarios = new Usuarios();
    }

    public Empleados(Cargo cargo, Usuarios usuarios, String nombre, String apellido, Date creaciondate, Date modificaciondate, String creadopor, String modificadopor, Date fechaNacimiento, String telefonoMovil, String telefonoFijo, String email, Set<Oportunidades> oportunidadeses) {
        this.cargo = cargo;
        this.usuarios = usuarios;
        this.nombre = nombre;
        this.apellido = apellido;
        this.creaciondate = creaciondate;
        this.modificaciondate = modificaciondate;
        this.creadopor = creadopor;
        this.modificadopor = modificadopor;
        this.fechaNacimiento = fechaNacimiento;
        this.telefonoMovil = telefonoMovil;
        this.telefonoFijo = telefonoFijo;
        this.email = email;
        this.oportunidadeses = oportunidadeses;
    }

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "EMPLEADO_AUTO_INC")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @Column(name = "ID_EMPLEADO", unique = true, nullable = false, precision = 22, scale = 0)
    public Integer getIdEmpleado() {
        return idEmpleado;
    }
    
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CARGO", nullable = false)
    public Cargo getCargo() {
        return this.cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    public Usuarios getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Column(name = "NOMBRE", length = 100)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "APELLIDO", length = 100)
    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREACIONDATE", length = 7)
    public Date getCreaciondate() {
        return this.creaciondate;
    }

    public void setCreaciondate(Date creaciondate) {
        this.creaciondate = creaciondate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFICACIONDATE", length = 7)
    public Date getModificaciondate() {
        return this.modificaciondate;
    }

    public void setModificaciondate(Date modificaciondate) {
        this.modificaciondate = modificaciondate;
    }

    @Column(name = "CREADOPOR", length = 100)
    public String getCreadopor() {
        return this.creadopor;
    }

    public void setCreadopor(String creadopor) {
        this.creadopor = creadopor;
    }

    @Column(name = "MODIFICADOPOR", length = 100)
    public String getModificadopor() {
        return this.modificadopor;
    }

    public void setModificadopor(String modificadopor) {
        this.modificadopor = modificadopor;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_NACIMIENTO", length = 7)
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name = "TELEFONO_MOVIL", length = 11)
    public String getTelefonoMovil() {
        return this.telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    @Column(name = "TELEFONO_FIJO", length = 11)
    public String getTelefonoFijo() {
        return this.telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    @Column(name = "EMAIL", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empleados")
    public Set<Oportunidades> getOportunidadeses() {
        return this.oportunidadeses;
    }

    public void setOportunidadeses(Set<Oportunidades> oportunidadeses) {
        this.oportunidadeses = oportunidadeses;
    }

}

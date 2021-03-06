package Model;
// Generated 03-13-2018 02:30:22 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EstadoCliente generated by hbm2java
 */
@Entity
@Table(name="ESTADO_CLIENTE"
    ,schema="ADMIN_CRM"
)
public class EstadoCliente  implements java.io.Serializable {


     private Integer idEstadoCliente;
     private String estadoCliente;
     private Set<Cliente> clientes = new HashSet<Cliente>(0);

    public EstadoCliente() {
    }

    public EstadoCliente(String estadoCliente, Set<Cliente> clientes) {
       this.estadoCliente = estadoCliente;
       this.clientes = clientes;
    }
   
     @Id 

    
    @Column(name="ID_ESTADO_CLIENTE", unique=true, nullable=false, precision=22, scale=0)
    public Integer getIdEstadoCliente() {
        return this.idEstadoCliente;
    }
    
    public void setIdEstadoCliente(Integer idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    
    @Column(name="ESTADO_CLIENTE", length=60)
    public String getEstadoCliente() {
        return this.estadoCliente;
    }
    
    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="estadoCliente")
    public Set<Cliente> getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }




}



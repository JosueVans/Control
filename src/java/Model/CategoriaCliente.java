package Model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORIA_CLIENTE"
    ,schema="ADMIN_CRM"
)
public class CategoriaCliente  implements java.io.Serializable {


     private Integer idCategoriaCliente;
     private String categoriaCliente;
     private Set<Cliente> clientes = new HashSet<Cliente>(0);

    public CategoriaCliente() {
    }

    public CategoriaCliente(String categoriaCliente, Set<Cliente> clientes) {
       this.categoriaCliente = categoriaCliente;
       this.clientes = clientes;
    }
   
     @Id 

    
    @Column(name="ID_CATEGORIA_CLIENTE", unique=true, nullable=false, precision=22, scale=0)
    public Integer getIdCategoriaCliente() {
        return this.idCategoriaCliente;
    }
    
    public void setIdCategoriaCliente(Integer idCategoriaCliente) {
        this.idCategoriaCliente = idCategoriaCliente;
    }

    
    @Column(name="CATEGORIA_CLIENTE", length=60)
    public String getCategoriaCliente() {
        return this.categoriaCliente;
    }
    
    public void setCategoriaCliente(String categoriaCliente) {
        this.categoriaCliente = categoriaCliente;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="categoriaCliente")
    public Set<Cliente> getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }




}



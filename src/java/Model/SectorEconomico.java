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
@Table(name="SECTOR_ECONOMICO"
    ,schema="ADMIN_CRM"
)
public class SectorEconomico  implements java.io.Serializable {


     private Integer idSectorEconomico;
     private String sectorEconomico;
     private Set<Cliente> clientes = new HashSet<Cliente>(0);

    public SectorEconomico() {
    }

    public SectorEconomico(String sectorEconomico, Set<Cliente> clientes) {
       this.sectorEconomico = sectorEconomico;
       this.clientes = clientes;
    }
   
     @Id 

    
    @Column(name="ID_SECTOR_ECONOMICO", unique=true, nullable=false, precision=22, scale=0)
    public Integer getIdSectorEconomico() {
        return this.idSectorEconomico;
    }
    
    public void setIdSectorEconomico(Integer idSectorEconomico) {
        this.idSectorEconomico = idSectorEconomico;
    }

    
    @Column(name="SECTOR_ECONOMICO", length=60)
    public String getSectorEconomico() {
        return this.sectorEconomico;
    }
    
    public void setSectorEconomico(String sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="sectorEconomico")
    public Set<Cliente> getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }


}



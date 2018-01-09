package Model;
// Generated 01-09-2018 11:33:30 AM by Hibernate Tools 4.3.1


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
 * Pais generated by hbm2java
 */
@Entity
@Table(name="PAIS"
    ,schema="ADMIN_CRM"
)
public class Pais  implements java.io.Serializable {


     private BigDecimal idPais;
     private String pais;
     private Set<Empresa> empresas = new HashSet<Empresa>(0);

    public Pais() {
    }

	
    public Pais(BigDecimal idPais) {
        this.idPais = idPais;
    }
    public Pais(BigDecimal idPais, String pais, Set<Empresa> empresas) {
       this.idPais = idPais;
       this.pais = pais;
       this.empresas = empresas;
    }
   
     @Id 

    
    @Column(name="ID_PAIS", unique=true, nullable=false, precision=22, scale=0)
    public BigDecimal getIdPais() {
        return this.idPais;
    }
    
    public void setIdPais(BigDecimal idPais) {
        this.idPais = idPais;
    }

    
    @Column(name="PAIS", length=60)
    public String getPais() {
        return this.pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="pais")
    public Set<Empresa> getEmpresas() {
        return this.empresas;
    }
    
    public void setEmpresas(Set<Empresa> empresas) {
        this.empresas = empresas;
    }




}


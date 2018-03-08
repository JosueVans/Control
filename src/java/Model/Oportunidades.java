package Model;
// Generated 01-31-2018 04:23:42 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Oportunidades generated by hbm2java
 */
@Entity
@Table(name="OPORTUNIDADES"
    ,schema="ADMIN_CRM"
)
public class Oportunidades  implements java.io.Serializable {


     private Integer idOportunidad;
     private Empresa empresa;
     private Fase fase;
     private String oportunidad;
     private Set<Negocio> negocios = new HashSet<Negocio>(0);

    public Oportunidades() {
        this.empresa=new Empresa();
    }

    public Oportunidades(Empresa empresa, Fase fase, String oportunidad, Set<Negocio> negocios) {
      // this.idOportunidad = idOportunidad;
       this.empresa = empresa;
       this.fase = fase;
       this.oportunidad = oportunidad;
       this.negocios = negocios;
    }
   
     @Id 

    
    @Column(name="ID_OPORTUNIDAD", unique=true, nullable=false, precision=22, scale=0)
    public Integer getIdOportunidad() {
        return this.idOportunidad;
    }
    
    public void setIdOportunidad(Integer idOportunidad) {
        this.idOportunidad = idOportunidad;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_EMPRESA", nullable=false)
    public Empresa getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_FASE", nullable=false)
    public Fase getFase() {
        return this.fase;
    }
    
    public void setFase(Fase fase) {
        this.fase = fase;
    }

    
    @Column(name="OPORTUNIDAD", length=100)
    public String getOportunidad() {
        return this.oportunidad;
    }
    
    public void setOportunidad(String oportunidad) {
        this.oportunidad = oportunidad;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="oportunidades")
    public Set<Negocio> getNegocios() {
        return this.negocios;
    }
    
    public void setNegocios(Set<Negocio> negocios) {
        this.negocios = negocios;
    }




}



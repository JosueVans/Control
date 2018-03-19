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

@Entity
@Table(name = "FASE",
         schema = "ADMIN_CRM"
)
public class Fase implements java.io.Serializable {

    private Integer idFase;
    private String fase;
    private Set<Oportunidades> oportunidadeses = new HashSet<Oportunidades>(0);

    public Fase() {
    }

    public Fase(String fase, Set<Oportunidades> oportunidadeses) {
        this.fase = fase;
        this.oportunidadeses = oportunidadeses;
    }

    @Id
    @SequenceGenerator(name="seq", sequenceName="FASE_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @Column(name = "ID_FASE", unique = true, nullable = false, precision = 22, scale = 0)
    public Integer getIdFase() {
        return this.idFase;
    }

    public void setIdFase(Integer idFase) {
        this.idFase = idFase;
    }

    @Column(name = "FASE", length = 80)
    public String getFase() {
        return this.fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fase")
    public Set<Oportunidades> getOportunidadeses() {
        return this.oportunidadeses;
    }

    public void setOportunidadeses(Set<Oportunidades> oportunidadeses) {
        this.oportunidadeses = oportunidadeses;
    }

}

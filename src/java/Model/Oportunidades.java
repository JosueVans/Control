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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OPORTUNIDADES",
         schema = "ADMIN_CRM"
)
public class Oportunidades implements java.io.Serializable {

    private Integer idOportunidad;
    private Empleados empleados;
    private Empresa empresa;
    private Fase fase;
    private String oportunidad;
    private Set<Negocio> negocios = new HashSet<Negocio>(0);

    public Oportunidades() {
        this.empleados = new Empleados();
        this.empresa = new Empresa();
        this.fase = new Fase();
    }

    public Oportunidades(Empleados empleados, Empresa empresa, Fase fase, String oportunidad, Set<Negocio> negocios) {
        this.empleados = empleados;
        this.empresa = empresa;
        this.fase = fase;
        this.oportunidad = oportunidad;
        this.negocios = negocios;
    }

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "OPORTUNIDADES_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @Column(name = "ID_OPORTUNIDAD", unique = true, nullable = false, precision = 22, scale = 0)
    public Integer getIdOportunidad() {
        return this.idOportunidad;
    }

    public void setIdOportunidad(Integer idOportunidad) {
        this.idOportunidad = idOportunidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO", nullable = false)
    public Empleados getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDEMPRESA", nullable = false)
    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FASE", nullable = false)
    public Fase getFase() {
        return this.fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    @Column(name = "OPORTUNIDAD", length = 100)
    public String getOportunidad() {
        return this.oportunidad;
    }

    public void setOportunidad(String oportunidad) {
        this.oportunidad = oportunidad;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "oportunidades")
    public Set<Negocio> getNegocios() {
        return this.negocios;
    }

    public void setNegocios(Set<Negocio> negocios) {
        this.negocios = negocios;
    }

}

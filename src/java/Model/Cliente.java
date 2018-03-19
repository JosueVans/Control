package Model;
// Generated 03-13-2018 02:30:22 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
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

@Entity
@Table(name = "CLIENTE",
         schema = "ADMIN_CRM"
)
public class Cliente implements java.io.Serializable {

    private Integer idCliente;
    private CategoriaCliente categoriaCliente;
    private Empresa empresa;
    private EstadoCliente estadoCliente;
    private RubroCliente rubroCliente;
    private SectorEconomico sectorEconomico;
    private TipoCliente tipoCliente;
    private String nombreCliente;
    private String telefono;
    private String email;
    private Date creaciondate;
    private Date modificaciondate;
    private String creadopor;
    private String modificadopor;
    private String apellido;
    private Set<DetalleCliente> detalleClientes = new HashSet<DetalleCliente>(0);
    private Set<ProductosCliente> productosClientes = new HashSet<ProductosCliente>(0);

    public Cliente() {
        this.categoriaCliente = new CategoriaCliente();
        this.empresa = new Empresa();
        this.estadoCliente = new EstadoCliente();
        this.rubroCliente = new RubroCliente();
        this.sectorEconomico = new SectorEconomico();
        this.tipoCliente = new TipoCliente();

    }

    public Cliente(CategoriaCliente categoriaCliente, Empresa empresa, EstadoCliente estadoCliente, RubroCliente rubroCliente, SectorEconomico sectorEconomico, TipoCliente tipoCliente, String nombreCliente, String telefono, String email, Date creaciondate, Date modificaciondate, String creadopor, String modificadopor, String apellido, Set<DetalleCliente> detalleClientes, Set<ProductosCliente> productosClientes) {
        this.categoriaCliente = categoriaCliente;
        this.empresa = empresa;
        this.estadoCliente = estadoCliente;
        this.rubroCliente = rubroCliente;
        this.sectorEconomico = sectorEconomico;
        this.tipoCliente = tipoCliente;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.email = email;
        this.creaciondate = creaciondate;
        this.modificaciondate = modificaciondate;
        this.creadopor = creadopor;
        this.modificadopor = modificadopor;
        this.apellido = apellido;
        this.detalleClientes = detalleClientes;
        this.productosClientes = productosClientes;
    }

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "CLIENTE_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @Column(name = "ID_CLIENTE", unique = true, nullable = false, precision = 22, scale = 0)
    public Integer getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORIA_CLIENTE", nullable = false)
    public CategoriaCliente getCategoriaCliente() {
        return this.categoriaCliente;
    }

    public void setCategoriaCliente(CategoriaCliente categoriaCliente) {
        this.categoriaCliente = categoriaCliente;
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
    @JoinColumn(name = "ID_ESTADO_CLIENTE", nullable = false)
    public EstadoCliente getEstadoCliente() {
        return this.estadoCliente;
    }

    public void setEstadoCliente(EstadoCliente estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RUBRO_CLIENTE", nullable = false)
    public RubroCliente getRubroCliente() {
        return this.rubroCliente;
    }

    public void setRubroCliente(RubroCliente rubroCliente) {
        this.rubroCliente = rubroCliente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SECTOR_ECONOMICO", nullable = false)
    public SectorEconomico getSectorEconomico() {
        return this.sectorEconomico;
    }

    public void setSectorEconomico(SectorEconomico sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CLIENTE", nullable = false)
    public TipoCliente getTipoCliente() {
        return this.tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Column(name = "NOMBRE_CLIENTE", length = 50)
    public String getNombreCliente() {
        return this.nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Column(name = "TELEFONO", length = 11)
    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "EMAIL", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Column(name = "CREADOPOR", length = 75)
    public String getCreadopor() {
        return this.creadopor;
    }

    public void setCreadopor(String creadopor) {
        this.creadopor = creadopor;
    }

    @Column(name = "MODIFICADOPOR", length = 75)
    public String getModificadopor() {
        return this.modificadopor;
    }

    public void setModificadopor(String modificadopor) {
        this.modificadopor = modificadopor;
    }

    @Column(name = "APELLIDO", length = 50)
    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    public Set<DetalleCliente> getDetalleClientes() {
        return this.detalleClientes;
    }

    public void setDetalleClientes(Set<DetalleCliente> detalleClientes) {
        this.detalleClientes = detalleClientes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    public Set<ProductosCliente> getProductosClientes() {
        return this.productosClientes;
    }

    public void setProductosClientes(Set<ProductosCliente> productosClientes) {
        this.productosClientes = productosClientes;
    }

}

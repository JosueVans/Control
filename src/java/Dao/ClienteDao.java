package Dao;

import Model.CategoriaCliente;
import Model.Cliente;
import Model.Empresa;
import Model.EstadoCliente;
import Model.Pais;
import Model.RubroCliente;
import Model.SectorEconomico;
import Model.TipoCliente;
import java.util.List;
import org.hibernate.HibernateException;

public interface ClienteDao {

    public void IniciaSesion();
    public void ManejaException(HibernateException he);
    public List<Cliente> ListaCliente() throws HibernateException;
    public List<Pais> ListaPais() throws HibernateException;
    public List<TipoCliente> ListaTipoCliente() throws HibernateException;
    public List<CategoriaCliente> ListaCategoriaCliente() throws HibernateException;
    public List<EstadoCliente> ListaEstadoCliente() throws HibernateException;
    public List<SectorEconomico> ListaSectorEconomico() throws HibernateException;
    public List<RubroCliente> ListaRubroCliente() throws HibernateException;
    public List<Empresa> ListaEmpresa() throws HibernateException;
    public List<Empresa> ListaEmpresas(int id) throws HibernateException;

    public boolean CreateCliente(Cliente cli) throws HibernateException; 
    public boolean DeleteCliente(int id) throws HibernateException;
    
}

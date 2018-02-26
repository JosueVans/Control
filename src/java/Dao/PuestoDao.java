package Dao;

import Model.Cargo;

import Model.Rol;
import java.util.List;
import org.hibernate.HibernateException;

public interface PuestoDao {
    
    public void IniciaSesion();
    public void ManejaException(HibernateException he);
    public List<Cargo> ListaCargo(int id);
    public List<Cargo> ListaCargos();
    public List<Rol> ListaRol();
    public boolean CreateCargo(Cargo ca);
    public boolean CreateRol(Rol ro);
    
    
}

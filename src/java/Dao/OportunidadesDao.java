package Dao;

import Model.Oportunidades;
import java.util.List;
import org.hibernate.HibernateException;

public interface OportunidadesDao {

    public void IniciaSesion();
    public void ManejaException(HibernateException he);
    public List<Oportunidades> ListaOportunidades() throws HibernateException;
    
    public boolean CreateOportunidades(Oportunidades add) throws HibernateException; 
    public boolean DeleteOportunidades(int id) throws HibernateException;
    public Oportunidades SearchOportunidades(int id) throws HibernateException;

}

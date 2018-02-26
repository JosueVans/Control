/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Rol;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RolDaoImpl implements RolDao{
 private Session sesion;
 private Transaction tx;
    @Override
    public List<Rol> BuscarTodos() throws HibernateException{
       List<Rol> rolList= null;
       try {
           iniciaOperacion();
           Query query=sesion.getNamedQuery("Rol.BuscarTodos");
           rolList=query.list();
           //sesion.beginTransaction().commit();
       }
       catch(HibernateException ex){
      manejaException(ex);
      //sesion.beginTransaction().rollback();
       }
       finally{
       sesion.close();
       }
       return rolList;
    }

    @Override
    public void iniciaOperacion() throws HibernateException {
        sesion=Utils.HibernateUtil.getSessionFactory().openSession();
        tx=sesion.beginTransaction();
    }

    @Override
    public void manejaException(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Error no se pudo accesar a los datos: " + he);
        
    }

   
    
}

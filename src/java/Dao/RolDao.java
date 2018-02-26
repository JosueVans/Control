/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Rol;
import java.util.List;
import org.hibernate.HibernateException;

public interface RolDao {
    public void iniciaOperacion() throws HibernateException;
    public void manejaException(HibernateException he) throws HibernateException;
    public List<Rol> BuscarTodos() throws HibernateException;
    
}

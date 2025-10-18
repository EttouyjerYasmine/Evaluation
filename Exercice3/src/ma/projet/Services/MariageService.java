/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.Services;

/**
 *
 * @author hp
 */

import java.util.Date;
import java.util.List;
import ma.projet.classes.Homme;
import ma.projet.classes.Marriage;
import dao.IDao;
import ma.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MariageService implements IDao<Marriage> {

    @Override
    public boolean create(Marriage o) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            etat = false;
        } finally{
            if (session != null)
                session.close();
        }
        return etat;
    }

    @Override
    public boolean delete(Marriage o) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            etat = false;
        } finally{
            if (session != null)
                session.close();
        }
        return etat;
    }

    @Override
    public boolean update(Marriage o) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            etat = false;
        } finally{
            if (session != null)
                session.close();
        }
        return etat;
    }

    @Override
    public Marriage getById(int id) {
        Session session = null;
        Transaction tx = null;
        Marriage mariage = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            mariage = (Marriage) session.get(Marriage.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
        return mariage;
    }

    @Override
    public List<Marriage> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Marriage> mariages = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            mariages = session.createQuery("from Mariage").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
        return mariages;
    }
    
    public long getHommesMarieesQuatreFemmesEntreDates(Date date1, Date date2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int count = 0;
        try {
            List<Object> result = session.createSQLQuery(
            "select homme " +
            "from mariages " +
            "WHERE dateDebut BETWEEN :date1 and :date2 " +
            "group by homme " +
            "having COUNT(femme) = 4")
            .setParameter("date1", date1)
            .setParameter("date2", date2)
            .list();
            count = result.size();
        } finally {
            session.close();
        }
        return count;
    }






}

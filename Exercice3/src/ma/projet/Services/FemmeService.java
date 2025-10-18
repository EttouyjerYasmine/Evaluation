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


import dao.IDao;
import java.util.Date;
import java.util.List;
import ma.Util.HibernateUtil;
import ma.projet.classes.Femme;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hp
 */
public class FemmeService implements IDao<Femme> {

    @Override
    public boolean create(Femme o) {
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
    public boolean delete(Femme o) {
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
    public boolean update(Femme o) {
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
    public Femme getById(int id) {
        Session session = null;
        Transaction tx = null;
        Femme femme = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femme = (Femme) session.get(Femme.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
        return femme;
    }

    @Override
    public List<Femme> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Femme> femmes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femmes = session.createQuery("from Femme").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
        return femmes;
    }
    
    public int getNombreEnfantsEntreDates(Femme femme, Date date1, Date date2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int total = 0;
        try {
            Number result = (Number) session.createSQLQuery(
            "select SUM(nbrEnfant) from mariages " +
            "where femme = :idFemme and dateDebut BETWEEN :date1 AND :date2")
            .setParameter("idFemme", femme.getId())
            .setParameter("date1", date1)
            .setParameter("date2", date2)
            .uniqueResult();
            if (result != null) {
                total = result.intValue();
            }
        } finally {
            session.close();
        }
        return total;
    }





    
    public List<Femme> getFemmesMarieesDeuxFoisOuPlus() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    List<Femme> result = null;

    try {
        tx = session.beginTransaction();
        String d = "select f from Femme f join f.mariages m group by f having count(m) >= 2";
        result = session.createQuery(d).list();
        tx.commit();
    } catch (Exception e) {
        if (tx != null) tx.rollback();
    } finally {
        session.close();
    }
    return result;
}




}

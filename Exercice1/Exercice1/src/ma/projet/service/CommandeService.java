/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.Date;
import java.util.List;
import ma.projet.classes.Commande;
import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hp
 */
public class CommandeService implements IDao<Commande> {
    @Override
    public boolean create(Commande o) {
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
    public boolean delete(Commande o) {
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
    public boolean update(Commande o) {
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
    public Commande getById(int id) {
        Session session = null;
        Transaction tx = null;
        Commande commande = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            commande = (Commande) session.get(Commande.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
        return commande;
    }

    @Override
    public List<Commande> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Commande> commandes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            commandes = session.createQuery("from Commande").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
        return commandes;
    }
    
    public List<Produit> getProduitsCommandesEntreDates(Date d1, Date d2) {
        Session session = null;
        Transaction tx = null;
        List<Produit> produits = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            produits = session.createQuery(
                "select distinct l.produit from LigneCommandeProduit l " +
                "where l.commande.date between :d1 and :d2")
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            if (session != null) session.close();
        }
        return produits;
    }
    
    public List<Object[]> getProduitsByCommande(int idCommande) {
        Session session = null;
        Transaction tx = null;
        List<Object[]> result = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            result = session.createQuery(
                "select p.reference, p.prix, l.quantite " +
                "from LigneCommandeProduit l join l.produit p " +
                "where l.commande.id = :idCommande")
                .setParameter("idCommande", idCommande)
                .list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            if (session != null) session.close();
        }
        return result;
    }


}

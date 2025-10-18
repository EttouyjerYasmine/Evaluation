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
import java.text.SimpleDateFormat;
import java.util.List;
import ma.projet.classes.Femme;
import ma.projet.classes.Homme;
import ma.projet.classes.Marriage;
import dao.IDao;
import ma.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
public class HommeService implements IDao<Homme> {

    public boolean create(Homme o) {
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

    public boolean delete(Homme o) {
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

    public boolean update(Homme o) {
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
    public Homme getById(int id) {
        Session session = null;
        Transaction tx = null;
        Homme homme = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            homme = (Homme) session.get(Homme.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
        return homme;
    }

    public List<Homme> getAll() {
        Session session = null;
        Transaction tx = null;
        List<Homme> hommes = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            hommes = session.createQuery("from Homme").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
        } finally{
            if (session != null)
                session.close();
        }
        return hommes;
    }

    public List<Femme> getEpousesEntreDates(Homme homme, Date date1, Date date2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Femme> epouses = session.createQuery(
        "select m.femme from Mariage m " +
        "where m.homme = :homme and m.dateDebut between :date1 and :date2")
        .setParameter("homme", homme)
        .setParameter("date1", date1)
        .setParameter("date2", date2)
        .list();
        return epouses;
    }

    
    public List<Marriage> getMariagesParHomme(Homme h) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Marriage> mariages = null;
        try {
            String hm = "FROM Mariage m WHERE m.homme = :h";
            org.hibernate.Query query = session.createQuery(hm);
            query.setParameter("h", h);
            mariages = query.list(); // retourne List<Mariage>
        } finally {
            session.close();
        }
        return mariages;
    }
    
    
    public void afficherMariages(Homme h) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Marriage> mariages = getMariagesParHomme(h);
        
        System.out.println("Nom: " + h.getNom() + " " + h.getPrenom());

        System.out.println("Mariages en cours:");
        int count = 1;
        for (Marriage m : mariages) {
            if (m.getDateFin() == null) {
                System.out.println(count + ". " + m.getFemme().getNom() + " " + m.getFemme().getPrenom());
                System.out.println("Date Début: " + sdf.format(m.getDateDebut()));
                System.out.println("Nombre Enfants: " + m.getNbrEnfant());
                count++;
            }
        }

        System.out.println("Mariages échoués:");
        count = 1;
        for (Marriage m : mariages) {
            if (m.getDateFin() != null) {
                System.out.println(count + ". " + m.getFemme().getNom() + " " + m.getFemme().getPrenom());
                System.out.println("Date Début: " +sdf.format(m.getDateDebut()));
                System.out.println("Date Fin: " + sdf.format(m.getDateFin()));
                System.out.println("Nombre Enfants: " + m.getNbrEnfant());
                count++;
            }
        }
    }


}


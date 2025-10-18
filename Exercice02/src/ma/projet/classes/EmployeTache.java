/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;

import java.util.Date;

/**
 *
 * @author hp
 */
public class EmployeTache {
    private Date dateDeut;
    private Date dateFin;

    public EmployeTache(Date dateDeut, Date dateFin) {
        this.dateDeut = dateDeut;
        this.dateFin = dateFin;
    }

    public EmployeTache(Date parse, Date parse0, Employe emp, Tache t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Date getDateDeut() {
        return dateDeut;
    }

    public void setDateDeut(Date dateDeut) {
        this.dateDeut = dateDeut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "EmployeTache{" + "dateDeut=" + dateDeut + ", dateFin=" + dateFin + '}';
    }

    public Tache getTache() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getDateDebutReelle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getDateFinReelle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

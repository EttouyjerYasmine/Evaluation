/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;

/**
 *
 * @author hp
 */

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ma.projet.classes.Marriage;

/**
 *
 * @author hp
 */
@Entity
@Table (name = "femmes")

@NamedNativeQuery(
    name = "Femme.nombreEnfantsEntreDates",
    query = "select SUM(nbrEnfant) from mariages " +
            "where femme = :idFemme and dateDebut >= :date1 and dateDebut <= :date2"
)

@NamedQuery(
    name = "Femme.marieesDeuxFoisOuPlus",
    query = "select f FROM Femme f join f.mariages m " +
            "group by f.id " +
            "having COUNT(m) >= 2"
)

public class Femme extends Personnes{

    public Femme() {
    }
    @OneToMany(mappedBy = "femme")
    private List<Marriage> mariages;

    public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
    
    public List<Marriage> getMariages() {
        return mariages;
    }
    
    public void setMariages(List<Marriage> mariages) {
        this.mariages = mariages;
    }
    
}
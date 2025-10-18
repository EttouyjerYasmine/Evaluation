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
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Entity
@Table (name = "hommes")
@NamedQuery(
    name = "Homme.findEpousesEntreDates",
    query = "select m.femme from Mariage m " +
            "where m.homme = :homme " +
            "and m.dateDebut between :date1 and :date2"
)
public class Homme extends Personnes {

    public Homme() {
    }

    public Homme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }

    
}
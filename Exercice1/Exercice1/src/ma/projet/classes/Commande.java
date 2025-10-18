/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author hp
 */
@Entity
@NamedQueries({
   @NamedQuery(name = "findBetweenDate", query = "from Commande where date between :d1 and :d2"),
})

@NamedNativeQueries({
    @NamedNativeQuery(name = "findBetweenDateNative", query = "select * from Commande where date between :d1 and :d2", resultClass = Commande.class)
})
public class Commande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date date;
    
    
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LigneCommandeProduit> lignecommandeproduit;

    public Commande() {
    }

    public Commande(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<LigneCommandeProduit> getLignes() { return lignecommandeproduit; }
    public void setLignes(List<LigneCommandeProduit> lignes) { this.lignecommandeproduit = lignes; }


    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date=" + date +'}';
    }
    
    
}

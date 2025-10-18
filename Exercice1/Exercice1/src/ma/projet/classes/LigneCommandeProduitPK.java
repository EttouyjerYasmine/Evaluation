/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;

/**
 *
 * @author X1 YOGA
 */

@Embeddable
public class LigneCommandeProduitPK implements Serializable{
    
    @Column(name = "commande_id")
    private int commande;
    @Column(name = "produit_id")
    private int produit;

    public LigneCommandeProduitPK() {
    }

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;

import javax.persistence.*;

/**
 *
 * @author hp
 */
@Entity
public class LigneCommandeProduit {
    @EmbeddedId
    private LigneCommandeProduitPK pK;
    private int quantite;
    
    @ManyToOne
    @JoinColumn(name = "commande_id", insertable = false, updatable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "produit_id", insertable = false, updatable = false)
    private Produit produit;

    
    public LigneCommandeProduit() {
        pK = new LigneCommandeProduitPK();
    }

    public LigneCommandeProduit(int quantite, Commande commande, Produit produit) {
        pK = new LigneCommandeProduitPK();
        this.pK.setCommande(commande.getId());
        this.pK.setProduit(produit.getId());
        this.quantite = quantite;
        this.commande = commande;
        this.produit = produit;
    }

    public LigneCommandeProduitPK getpK() {
        return pK;
    }

    public void setpK(LigneCommandeProduitPK pK) {
        this.pK = pK;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    
}

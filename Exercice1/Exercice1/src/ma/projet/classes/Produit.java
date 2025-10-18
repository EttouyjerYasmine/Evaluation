/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.classes;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "produits")
@NamedQuery(name = "Produit.findPrixSup100", query = "from Produit p where p.prix > 100")

public class Produit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reference;
    private float prix;
    @ManyToOne
    private Categorie categorie;
    
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LigneCommandeProduit> lignesCommande;

    public Produit() {
    }

    public Produit(String reference, float prix) {
        this.reference = reference;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie; 
    }
    
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie; 
    }

    public List<LigneCommandeProduit> getLignesCommande() { return lignesCommande; }
    public void setLignesCommande(List<LigneCommandeProduit> lignesCommande) { this.lignesCommande = lignesCommande; }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", reference=" + reference + ", prix=" + prix + ", categorie=" + categorie + '}';
    }
    
    
    
}

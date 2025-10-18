package ma.projet.test;

import java.util.Date;
import java.util.List;
import ma.projet.classes.*;
import ma.projet.service.*;

public class Test {
    public static void main(String[] args) {
        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService cmdService = new CommandeService();

        // --- Création de catégories et produits ---
        Categorie c1 = new Categorie("CAT01", "Ordinateurs");
        cs.create(c1);

        Produit p1 = new Produit("ES12", 120);
        p1.setCategorie(c1);
        ps.create(p1);

        Produit p2 = new Produit("ZR85", 100);
        p2.setCategorie(c1);
        ps.create(p2);

        Produit p3 = new Produit("EE85", 200);
        p3.setCategorie(c1);
        ps.create(p3);

        // --- Affichage produits par catégorie ---
        System.out.println("Produits de la catégorie Ordinateurs :");
        for (Produit p : ps.getProduitsByCategorie(c1.getId())) {
            System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
        }

        // --- Produits dont le prix > 100 DH ---
        System.out.println("\nProduits dont le prix > 100 DH :");
        for (Produit p : ps.getProduitsPrixSup100()) {
            System.out.println(p.getReference() + " - " + p.getPrix());
        }

        // --- Création d’une commande ---
        Commande commande = new Commande(new Date());
        cmdService.create(commande);

        LigneCommandeService lcs = new LigneCommandeService();
        lcs.create(new LigneCommandeProduit(7, commande, p1));
        lcs.create(new LigneCommandeProduit(14, commande, p2));
        lcs.create(new LigneCommandeProduit(5, commande, p3));

        // --- Produits d’une commande ---
        System.out.println("\nCommande: " + commande.getId());
        System.out.println("Référence\tPrix\tQuantité");
        for (Object[] obj : cmdService.getProduitsByCommande(commande.getId())) {
            System.out.println(obj[0] + "\t" + obj[1] + "\t" + obj[2]);
        }
    }
}

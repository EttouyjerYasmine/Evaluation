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


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author lenovo
 */
@Embeddable
public class EmployeTachePK implements Serializable{
    
    @Column(name = "employe_id")
    private int employe;
    @Column(name = "tache_id")
    private int tache;

    public EmployeTachePK() {
    }

    public EmployeTachePK(int employe, int tache) {
        this.employe = employe;
        this.tache = tache;
    }

    public int getEmploye() {
        return employe;
    }

    public void setEmploye(int employe) {
        this.employe = employe;
    }

    public int getTache() {
        return tache;
    }

    public void setTache(int tache) {
        this.tache = tache;
    }
    
    

}

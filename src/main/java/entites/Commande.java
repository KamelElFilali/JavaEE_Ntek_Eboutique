/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author narib
 */
public class Commande {
    private int idCommande;
    private int idUtilisateur;
    private double prixCommande;
    private Date dateDeCommande;
    private HashMap<Produit, Integer> listeProduit;

    public Commande()
    {
        listeProduit = new HashMap<>();
    }
    
    public Commande(int idUtilisateur, double prixCommande, Date dateDeCommande) {
        this.idUtilisateur = idUtilisateur;
        this.prixCommande = prixCommande;
        this.dateDeCommande = dateDeCommande;
        listeProduit = new HashMap<>();
    }
    
    public Commande(int idCommande, int idUtilisateur, double prixCommande, Date dateDeCommande) {
        this.idCommande = idCommande;
        this.idUtilisateur = idUtilisateur;
        this.prixCommande = prixCommande;
        this.dateDeCommande = dateDeCommande;
        listeProduit = new HashMap<>();
    }

    public Commande(int idCommande, int idUtilisateur, double prixCommande, Date dateDeCommande, HashMap<Produit, Integer> listeProduit) {
        this.idCommande = idCommande;
        this.idUtilisateur = idUtilisateur;
        this.prixCommande = prixCommande;
        this.dateDeCommande = dateDeCommande;
        this.listeProduit = listeProduit;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public double getPrixCommande() {
        return prixCommande;
    }

    public void setPrixCommande(double prixCommande) {
        this.prixCommande = prixCommande;
    }

    public Date getDateDeCommande() {
        return dateDeCommande;
    }

    public void setDateDeCommande(Date dateDeCommande) {
        this.dateDeCommande = dateDeCommande;
    }

    public HashMap<Produit, Integer> getListeProduit() {
        return listeProduit;
    }

    public void setListeProduit(HashMap<Produit, Integer> listeProduit) {
        this.listeProduit = listeProduit;
    }

    
}
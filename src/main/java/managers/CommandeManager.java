/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entites.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import services.BD;

/**
 *
 * @author narib
 */
public class CommandeManager {
    private static ArrayList<Commande> listeCommande = new ArrayList<Commande>();
    
    /**
     * coder une methode pour recharger la liste de commande
     * depuis la BD avec jdbc
     */
     public static void refreshListeCommande()
    {        
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statementCommande = connexion.createStatement();
            //excuter la query
            ResultSet resultListCommande = statementCommande.executeQuery("SELECT * FROM commande;");
            //vider la liste pour la remplir une autre fois
            listeCommande.clear();
            
            //remplir la liste des commande avec le resultset obtenue de la query
            while(resultListCommande.next())
            {
                Commande commande = new Commande();
                
                commande.setIdCommande(resultListCommande.getInt("id_commande"));
                commande.setIdUtilisateur(resultListCommande.getInt("id_utilisateur"));
                commande.setPrixCommande(resultListCommande.getDouble("prix_commande"));
                commande.setDateDeCommande(resultListCommande.getDate("date_de_commande"));

                listeCommande.add(commande);
            }
            
            /**
             * on utilise la meme connexion
             * pour obtenir la liste des produits de la commande
             * et les ajouter a son HashMap
             */
            Statement statementListeProduit = connexion.createStatement();
            
            ResultSet resultatListProduit = statementListeProduit.executeQuery("SELECT * FROM produits_commande;");
            
            while(resultatListProduit.next())
            {
                //on prend chaque objet de la liste des produitsCommande
                //et on ajoute le produit et sa quantite au HashMap de la commande
                for(Commande commande : listeCommande)
                {
                    //on check quelle commande a son id_commande
                    if (commande.getIdCommande() == resultatListProduit.getInt("id_commande"))
                    {
                       //et on ajoute le produit et sa quantite au HashMap de la commande on utilisant l'id du produit et la quantite
                       commande.getListeProduit().put(ProduitManager.getByIdProduit(resultatListProduit.getInt("id_produit")), resultatListProduit.getInt("quantite"));
                    }
                }
            }
            
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
//--------------------------------------------------------------------------------------------------------------------------------------------------------
   
    public static ArrayList<Commande> getAllCommande()
    {
        return listeCommande;
    }
     
//--------------------------------------------------------------------------------------------------------------------------------------------------------
   
    public static Commande getByIdCommande(int idCommande)
    {
        for(Commande commande : listeCommande)
        {
            if (commande.getIdCommande() == idCommande)
            {
                return commande;
            }
        }
        
        return null;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Commande> getByIdUtilisateur(int idUtilisateur)
    {
        ArrayList<Commande> listeCommandeByIdUtilisateur = new ArrayList<>();
        
        for(Commande commande : listeCommande)
        {
            if (commande.getIdUtilisateur() == idUtilisateur)
            {
                listeCommandeByIdUtilisateur.add(commande);
            }
        }
        
        return listeCommandeByIdUtilisateur;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static ArrayList<Commande> getByPrixCommande(double prixCommande)
    {
        ArrayList<Commande> listeCommandeByPrixCommande = new ArrayList<>();
        
        for(Commande commande : listeCommande)
        {
            if (commande.getPrixCommande()== prixCommande)
            {
                listeCommandeByPrixCommande.add(commande);
            }
        }
        
        return listeCommandeByPrixCommande;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Commande> getByDateCommande(Date dateCommande)
    {
        ArrayList<Commande> listeCommandeByDateCommande = new ArrayList<>();
        
        for(Commande commande : listeCommande)
        {
            if (commande.getDateDeCommande() == dateCommande)
            {
                listeCommandeByDateCommande.add(commande);
            }
        }
        
        return listeCommandeByDateCommande;
    }
        
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static int addCommande(Commande commande)
    {   
        int i = 0;
        /**
         * il faut ajouter la commande a la base de donnees
         * en faisant un insert avec la jdbc
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            i = statement.executeUpdate("INSERT INTO commande (id_utilisateur, prix_commande, date_de_commande) VALUES("+commande.getIdUtilisateur()+","+commande.getPrixCommande()+",'"+new SimpleDateFormat("yyyy/MM/dd hh:mm").format(commande.getDateDeCommande())+"');");

            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * recharger la liste de marque de la BD
         * en faisant un select all dans la table de marque
         */
        refreshListeCommande();
        
        return i;
    }
            
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static int addProduitsCommande(int idCommande,HashMap<Produit,Integer> listeProduitsCommande)
    {   
        int i = 0;
        /**
         * il faut ajouter la commande a la base de donnees
         * en faisant un insert avec la jdbc
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query pour ajouter les produits avec la listeProduitsCommande et son enumeration et l'idCommande
            Iterator listeAjouts = listeProduitsCommande.keySet().iterator();
            
            while(listeAjouts.hasNext())
            {
                Produit produit = (Produit)listeAjouts.next();
                i = statement.executeUpdate("INSERT INTO produits_commande (id_commande,id_produit,quantite) VALUES("+idCommande+","+produit.getIdProduit()+","+listeProduitsCommande.get(produit)+");");
            }
            
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * recharger la liste de marque de la BD
         * en faisant un select all dans la table de marque
         */
        refreshListeCommande();
        
        return i;
    }
                
//--------------------------------------------------------------------------------------------------------------------------------------------------------

}

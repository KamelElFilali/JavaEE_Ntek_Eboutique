/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entites.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.BD;


/**
 *
 * @author narib
 */
public class ProduitManager {
     private static ArrayList<Produit> listeProduit = new ArrayList<>();
    
    
    /**
     * une methode pour recharger la liste des produits
     * depuis la BD avec jdbc
     */
     public static void refreshListeProduit()
    {        
        //rafraichir la liste des images des produits
        ImageProduitManager.refreshListeImageProduit();
        
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            ResultSet resultList = statement.executeQuery("SELECT * FROM produits;");
            //vider la liste pour la remplir une autre fois
            listeProduit.clear();
            
            //remplir la liste des produit avec le resultset obtenue de la query
            while(resultList.next())
            {
                Produit produit = new Produit();
                
                produit.setIdProduit(resultList.getInt("id_produit"));
                produit.setIdCategorie(resultList.getInt("id_categorie"));
                produit.setIdMarque(resultList.getInt("id_marque"));
                produit.setCodeProduit(resultList.getInt("code_produit"));
                produit.setNomProduit(resultList.getString("nom_produit"));
                produit.setPrixProduit(resultList.getDouble("prix_produit"));
                produit.setStockProduit(resultList.getInt("stock_produit"));
                
                //ajouter les images au produits
                for(ImageProduit imgProduit : ImageProduitManager.getAllImageProduits())
                {
                    if (imgProduit.getIdProduit() == produit.getIdProduit())
                    {
                        produit.getImageProduit().add(imgProduit);
                    }
                }
                
                listeProduit.add(produit);
            }
            
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Produit> getAllProduit()
    {
        return listeProduit;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Produit> searchProduit(String caracters)
    {
        ArrayList<Produit> listeProduitSearching = new ArrayList<>(); //la liste qui contiendra les produits recherche par ces caracters

        /**
         * utiliser les caracters obtenues depuis la barre de recherche
         * pour trouver les produits depuis la liste contenant les meme caracters dans leur nom 
         */
        for (Produit produit : listeProduit)
        {
            if (produit.getNomProduit().contains(caracters))
            {
                listeProduitSearching.add(produit);
            }
        }
        
        return listeProduitSearching;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Produit getByIdProduit(int idProduit)
    {
        for (Produit produit : listeProduit)
        {
            if (produit.getIdProduit() == idProduit)
            {
                return produit;
            }
        }
        
        return null;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Produit getByNameProduit(String nomProduit)
    {
        for (Produit produit : listeProduit)
        {
            if (produit.getNomProduit().equals(nomProduit))
            {
                return produit;
            }
        }
        
        return null;
    }
     
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Produit> getByNameMarque(String nomMarque)
    {
        ArrayList<Produit> listeProduitByMarque = new ArrayList<>();
        int idMarque = MarqueManager.getByNameMarque(nomMarque).getIdMarque(); //l'id de la Marque concerne, car le produit contient seulement l'id d'une Marque
        
        for (Produit produit : listeProduit)
        {
            if (produit.getIdMarque() == idMarque)
            {
                listeProduitByMarque.add(produit);
            }
        }
        
        return listeProduitByMarque;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Produit> getByNameCategorie(String nomCategorie)
    {
        ArrayList<Produit> listeProduitByCategorie = new ArrayList<>();
        int idCategorie = CategorieManager.getByNameCategorie(nomCategorie).getIdCategorie(); //l'id de la categorie concerne, car le produit contient seulement l'id d'une categorie
        
        for (Produit produit : listeProduit)
        {
            if (produit.getIdCategorie() == idCategorie)
            {
                listeProduitByCategorie.add(produit);
            }
        }
        
        return listeProduitByCategorie;
    }
       
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Produit> getByIdMarque(int idMarque)
    {
        ArrayList<Produit> listeProduitByMarque = new ArrayList<>();
        
        for (Produit produit : listeProduit)
        {
            if (produit.getIdMarque() == idMarque)
            {
                listeProduitByMarque.add(produit);
            }
        }
        
        return listeProduitByMarque;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Produit> getByIdCategorie(int idCategorie)
    {
        ArrayList<Produit> listeProduitByCategorie = new ArrayList<>();
        
        for (Produit produit : listeProduit)
        {
            if (produit.getIdCategorie() == idCategorie)
            {
                listeProduitByCategorie.add(produit);
            }
        }
        
        return listeProduitByCategorie;
    }
       
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Produit> getByIdMarqueAndIdCategorie(int idMarque, int idCategorie)
    {
        ArrayList<Produit> listeProduitByMarqueCategorie = new ArrayList<>();
        
        for (Produit produit : listeProduit)
        {
            if (produit.getIdMarque() == idMarque && produit.getIdCategorie() == idCategorie)
            {
                listeProduitByMarqueCategorie.add(produit);
            }
        }
        
        return listeProduitByMarqueCategorie;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static boolean updatePriceProduit(Produit produit)
    {
        boolean updated = false;
       /**
        * se connecter a la BD et utiliser l'id et les information obtenue depuis l'utilisateur recu 
        * pour modifier les information d'utilisateur
        */
       try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("UPDATE produits SET prix_produit = "+produit.getPrixProduit()+" WHERE id_produit="+produit.getIdProduit()+";");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
            updated = true;
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       /**
        * rafraichir la liste des utilisateurs par la premiere methode
        */
       refreshListeProduit();
       
       return updated;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------
   
    public static boolean updateStockProduit(Produit produit)
    {
        boolean updated = false;
       /**
        * se connecter a la BD et utiliser l'id et les information obtenue depuis l'utilisateur recu 
        * pour modifier les information d'utilisateur
        */
       try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("UPDATE produits SET stock_produit = "+produit.getStockProduit()+" WHERE id_produit="+produit.getIdProduit()+";");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
            updated = true;
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       /**
        * rafraichir la liste des utilisateurs par la premiere methode
        */
       refreshListeProduit();
       
       return updated;
    }
     
//--------------------------------------------------------------------------------------------------------------------------------------------------------
   
    public static int addProduit(Produit produit)
    {   
        int i = 0;
        /**
         * il faut ajouter le Produit a la base de donnees
         * en faisant un insert avec la jdbc
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            i = statement.executeUpdate("INSERT INTO produits (id_categorie,id_marque,code_produit,nom_produit,prix_produit,stock_produit) VALUES("+produit.getIdCategorie()+","+produit.getIdMarque()+","+produit.getCodeProduit()+", '"+produit.getNomProduit()+"', "+produit.getPrixProduit()+", "+produit.getStockProduit()+");");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * recharger la liste de Produit de la BD
         * en faisant un select all dans la table de Produit
         */
        refreshListeProduit();
        
        /**
         *retourner l'id de ce dernier produit ajouter
         */
        return i;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void removeProduit(Produit produit)
    {        
        /**
         * il faut supprimer la Produit de la base de donnees
         * en fesant un delete avec la jdbc
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("DELETE FROM produits WHERE id_produit="+produit.getIdProduit()+";");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * supprimer toutes les images du produit dans la base de donne
         * en appelant la fonction de ImageProduitManager adequate
         */
        for (ImageProduit imgProduit : produit.getImageProduit())
        {
            ImageProduitManager.removeImageProduit(imgProduit);
        }
        
        /**
         * recharger la liste de Produit de la BD
         * en faisant un select all dans la table de Produit
         */
        refreshListeProduit();
    }
    
}

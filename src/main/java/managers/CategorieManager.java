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
public class CategorieManager {
    static ArrayList<Categorie> listeCategorie = new ArrayList<Categorie>();
    
    
    /**
     * une methode pour recharger la liste de categorie
     * depuis la BD avec jdbc
     */
    public static void refreshListeCategorie()
    {        
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            ResultSet resultList = statement.executeQuery("SELECT * FROM categorie;");
            //vider la liste pour la remplir une autre fois
            listeCategorie.clear();
            
            //remplir la liste des categorie avec le resultset obtenue de la query
            while(resultList.next())
            {
                Categorie categorie = new Categorie();
                
                categorie.setIdCategorie(resultList.getInt("id_categorie"));
                categorie.setNomCategorie(resultList.getString("nom_categorie"));
                categorie.setImageCategorie(resultList.getString("image_categorie"));
                
                listeCategorie.add(categorie);
            }
            
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Categorie> getAllCategorie()
    {
        return listeCategorie;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Categorie getByIdCategorie(int idCategorie)
    {
        for (Categorie categorie : listeCategorie)
        {
            if (categorie.getIdCategorie() == idCategorie)
            {
                return categorie;
            }
        }
        
        return null;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Categorie getByNameCategorie(String nomCategorie)
    {
        for (Categorie categorie : listeCategorie)
        {
            if (categorie.getNomCategorie().equals(nomCategorie))
            {
                return categorie;
            }
        }
        
        return null;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addCategorie(Categorie categorie)
    {   
        /**
         * il faut ajouter la categorie a la base de donnees
         * en faisant un delete avec la jdbc
         * 
         * faire ca dans toutes les class des managers
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("INSERT INTO categorie (nom_categorie,image_categorie) VALUES('"+categorie.getNomCategorie()+"', '"+categorie.getImageCategorie()+"');");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * recharger la liste de categorie de la BD
         * en faisant un select all dans la table de categorie
         * 
         * faire ca dans toutes les class des managers
         */
        refreshListeCategorie();
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void removeCategorie(Categorie categorie)
    {        
        /**
         * il faut supprimer la categorie de la base de donnees
         * en fesant un delete avec la jdbc
         * 
         * faire ca dans toutes les class des managers
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("DELETE FROM categorie WHERE id_categorie="+categorie.getIdCategorie()+";");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * recharger la liste de categorie de la BD
         * en faisant un select all dans la table de categorie
         * 
         * faire ca dans toutes les class des managers
         */
        refreshListeCategorie();
    }
    
}

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
public class ImageProduitManager {
    private static ArrayList<ImageProduit> listeImagesProduits = new ArrayList<ImageProduit>();
    
    /**
     * coder une methode pour recharger la liste d'image de tout les produits
     * depuis la BD avec jdbc
     */
    public static void refreshListeImageProduit()
    {        
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            ResultSet resultList = statement.executeQuery("SELECT * FROM images_produit;");
            //vider la liste pour la remplir une autre fois
            listeImagesProduits.clear();
            
            //remplir la liste des images_produit avec le resultset obtenue de la query
            while(resultList.next())
            {
                ImageProduit imageProduit = new ImageProduit();
                
                imageProduit.setIdImageProduit(resultList.getInt("id_image_produit"));
                imageProduit.setIdProduit(resultList.getInt("id_produit"));
                imageProduit.setImageProduit(resultList.getString("image_produit"));
                                
                listeImagesProduits.add(imageProduit);
            }
            
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<ImageProduit> getAllImageProduits()
    {
        return listeImagesProduits;
    }
        
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<ImageProduit> getByIdProduit(int idProduit)
    {
        ArrayList<ImageProduit> listeImageDuProduit = new ArrayList<>(); //la liste qui contiendra les images du produit concerne

        for (ImageProduit image : listeImagesProduits)
        {
            if (image.getIdProduit() == idProduit)
            {
                listeImageDuProduit.add(image);
            }
        }
        
        return listeImageDuProduit;
    }
        
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addImageProduit(ImageProduit imageProduit)
    {   
        /**
         * il faut ajouter la imageProduit a la base de donnees
         * en faisant un insert avec la jdbc
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("INSERT INTO images_produit (id_produit,image_produit) VALUES("+imageProduit.getIdProduit()+", "+imageProduit.getImageProduit()+");");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * rafraichir la liste des images du produit concerne
         */
        refreshListeImageProduit();
        
        /**
         * recharger la liste de imageProduit de la BD
         * en faisant un select all dans la table de imageProduit
         * 
         * faire ca dans toutes les class des managers
         */
        ProduitManager.refreshListeProduit();
    }
       
//--------------------------------------------------------------------------------------------------------------------------------------------------------
 
    public static void removeImageProduit(ImageProduit imageProduit)
    {        
        /**
         * il faut supprimer la imageProduit de la base de donnees
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
            
            int i = statement.executeUpdate("DELETE FROM images_produit WHERE id_image_produit="+imageProduit.getIdImageProduit()+";");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * rafraichir la liste des images du produit concerne
         */
        refreshListeImageProduit();
        
        /**
         * recharger la liste de imageProduit de la BD
         * en faisant un select all dans la table de imageProduit
         * 
         * faire ca dans toutes les class des managers
         */
        ProduitManager.refreshListeProduit();
    }
    
}

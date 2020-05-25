/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import com.mysql.cj.xdevapi.Result;
import entites.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import services.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author narib
 */
public class MarqueManager {
    private static ArrayList<Marque> listeMarque = new ArrayList<Marque>();
    
    
    /**
     *une methode pour recharger la liste de marque
     * depuis la BD avec jdbc
     * 
     */
    public static void refreshListeMarque()
    {        
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            ResultSet resultList = statement.executeQuery("SELECT * FROM marque;");
            //vider la liste pour la remplir une autre fois
            listeMarque.clear();
            
            //remplir la liste des marque avec le resultset obtenue de la query
            while(resultList.next())
            {
                Marque marque = new Marque();
                
                marque.setIdMarque(resultList.getInt("id_marque"));
                marque.setNomMarque(resultList.getString("nom_marque"));
                marque.setImageMarque(resultList.getString("image_marque"));
                
                listeMarque.add(marque);
            }
            
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Marque> getAllMarque()
    {
        return listeMarque;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Marque getByIdMarque(int idMarque)
    {
        for (Marque marque : listeMarque)
        {
            if (marque.getIdMarque() == idMarque)
            {
                return marque;
            }
        }
        
        return null;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Marque getByNameMarque(String nomMarque)
    {
        for (Marque marque : listeMarque)
        {
            if (marque.getNomMarque().equals(nomMarque))
            {
                return marque;
            }
        }
        
        return null;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addMarque(Marque marque)
    {   
        /**
         * il faut ajouter la marque a la base de donnees
         * en faisant un insert avec la jdbc
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("INSERT INTO marque (nom_marque,image_marque)VALUES('"+ marque.getNomMarque() +"', '"+marque.getImageMarque() +"');");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * recharger la liste de marque de la BD
         * en faisant un select all dans la table de marque
         */
        refreshListeMarque();
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void removeMarque(Marque marque)
    {        
        /**
         * il faut supprimer la marque de la base de donnees
         * en fesant un delete avec la jdbc
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("DELETE FROM marque WHERE id_marque="+marque.getIdMarque()+";");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * recharger la liste de marque de la BD
         * en faisant un select all dans la table de marque
         */
        refreshListeMarque();
    }
    
}

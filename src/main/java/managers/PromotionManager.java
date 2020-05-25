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
import static managers.CategorieManager.listeCategorie;
import services.BD;


/**
 *
 * @author narib
 */
public class PromotionManager {
    private static ArrayList<Promotion> listePromotion = new ArrayList<Promotion>();
    
    /**
     * coder une methode pour recharger la liste des promotions
     * depuis la BD avec jdbc
     * 
     */
      public static void refreshListePromotion()
    {        
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            ResultSet resultList = statement.executeQuery("SELECT * FROM promotions;");
            //vider la liste pour la remplir une autre fois
            listePromotion.clear();
            
            //remplir la liste des promotion avec le resultset obtenue de la query
            while(resultList.next())
            {
                Promotion promotion = new Promotion();
                
                promotion.setIdPromotion(resultList.getInt("id_promotion"));
                promotion.setCodePromotion(resultList.getString("code_promotion"));
                promotion.setRabaisPromotion(resultList.getInt("rabais_promotion"));
                //transformer le smallint de code_expire en boolean pour la variable isExpire de l'objet
                if(resultList.getInt("code_expire") == 0){promotion.setIsExpire(false);}
                else{promotion.setIsExpire(true);}
                
                listePromotion.add(promotion);
            }
            
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
      
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Promotion> getAllPromotion()
    {
        return listePromotion;
    }
        
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Promotion getByIdPromotion(int idPromotion)
    {
        Promotion promotion = null;
        
        for (Promotion promo : listePromotion)
        {
            if (promo.getIdPromotion() == idPromotion)
            {
                promotion = promo;
            }
        }
        
        return promotion;
    }
        
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Promotion getByCodePromotion(String codePromotion)
    {
        Promotion promotion = null;
        
        for (Promotion promo : listePromotion)
        {
            if (promo.getCodePromotion().equals(codePromotion))
            {
                promotion = promo;
            }
        }
        
        return promotion;
    }
        
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addPromotion(String codePromotion, int rabais_promotion)
    {
        Promotion promotion = new Promotion(codePromotion, rabais_promotion);
        
        /**
         * il faut ajouter la promotion a la base de donnees
         * en faisant un insert avec la jdbc avec l'objet promotion cree au debut de la methode
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("INSERT INTO promotions (code_promotion,rabais_promotion,code_expire) VALUES('"+codePromotion+"', "+rabais_promotion+" , 0);");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * recharger la liste de promotion de la BD
         * en faisant un select all dans la table de promotion
         */
        refreshListePromotion();
    }
        
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void removePromotion(String codePromotion)
    {
        Promotion laPromotion = new Promotion();
        
        for (Promotion promotion : listePromotion)
        {
            if (promotion.getCodePromotion().equals(codePromotion))
            {
                laPromotion = promotion;
            }
        }
        
        /**
         * il faut ajouter la promotion a la base de donnees en
         * en faisant un insert avec la jdbc avec l'objet promotion cree au debut de la methode
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("DELETE FROM promotions WHERE id_promotion="+laPromotion.getIdPromotion()+";");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * recharger la liste de promotion de la BD
         * en faisant un select all dans la table de promotion
         */
         refreshListePromotion();
    }
          
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void desactivatePromotion(Promotion promotion)
    {
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("UPDATE promotions SET code_expire = 1 WHERE id_promotion = "+promotion.getIdPromotion()+" ;");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

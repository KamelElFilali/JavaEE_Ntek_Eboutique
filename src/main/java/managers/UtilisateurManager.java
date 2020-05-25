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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.BD;


/**
 *
 * @author narib
 */
public class UtilisateurManager {
    private static ArrayList<Utilisateur> listeUtilisateur = new ArrayList<>();

    /**
     * coder une methode pour recharger la liste de Utilisateur
     * depuis la BD avec jdbc
     */
    public static void refreshListeUtilisateur()
    {        
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            ResultSet resultList = statement.executeQuery("SELECT * FROM utilisateur");
            //vider la liste pour la remplir une autre fois
            listeUtilisateur.clear();
            
            //remplir la liste des utilisateur avec le resultset obtenue de la query
            while(resultList.next())
            {
                Utilisateur utilisateur = new Utilisateur();
                
                utilisateur.setIdUtilisateur(resultList.getInt("id_utilisateur"));
                utilisateur.setIdStatus(resultList.getInt("id_status"));
                utilisateur.setNomUtilisateur(resultList.getString("nom"));
                utilisateur.setPrenomUtilisateur(resultList.getString("prenom"));
                utilisateur.setE_mailUtilisateur(resultList.getString("e_mail"));
                utilisateur.setMotDePassUtilisateur(resultList.getString("mot_de_pass"));
                utilisateur.setDateDeNaissanceUtilisateur(resultList.getDate("date_de_naissance"));
                utilisateur.setNumRueUtilisateur(resultList.getInt("num_rue"));
                utilisateur.setNomRueUtilisateur(resultList.getString("nom_rue"));
                utilisateur.setVilleUtilisateur(resultList.getString("ville"));
                utilisateur.setCodePostalUtilisateur(resultList.getString("code_postal"));
                utilisateur.setProvinceUtilisateur(resultList.getString("province"));
                utilisateur.setPaysUtilisateur(resultList.getString("pays"));
                utilisateur.setTelUtilisateur(resultList.getString("tel"));
                if (resultList.getInt("isChecked") == 0){utilisateur.setCheckedUtilisateur(false);}else if(resultList.getInt("isChecked") == 1){utilisateur.setCheckedUtilisateur(true);}
                utilisateur.setIdCheckUtilisateur(resultList.getString("id_Check"));
                listeUtilisateur.add(utilisateur);
            }
            
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Utilisateur> getAllUtilisateur()
    {
        return listeUtilisateur;
    }
       
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static ArrayList<Utilisateur> getAllAdmin()
    {
        ArrayList<Utilisateur> listeAdmin = new ArrayList<>();
        
        for (Utilisateur user : listeUtilisateur)
        {
            if (user.getIdStatus() == 2)
            {
                listeAdmin.add(user);
            }
        }
        
        return listeAdmin;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

   public static Utilisateur getLoginUtilisateur(String e_mail, String motDePass)
   {
       /**
        * utiliser la methode de refreshlisteutilisateur
        * pour faire la mise a jour de la liste
        */
       for (Utilisateur utilisateur : listeUtilisateur)
        {
            if (utilisateur.getE_mailUtilisateur().equals(e_mail) && utilisateur.getMotDePassUtilisateur().equals(motDePass))
            {
                return utilisateur;
            }
        }
        
        return null;
   }
       
//--------------------------------------------------------------------------------------------------------------------------------------------------------

   public static boolean UpdateUtilisateur(Utilisateur utilisateur)
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
            int i = statement.executeUpdate("UPDATE utilisateur SET id_status = "+utilisateur.getIdStatus()+",nom='"+utilisateur.getNomUtilisateur()+"',prenom='"+utilisateur.getPrenomUtilisateur()+"',e_mail='"+utilisateur.getE_mailUtilisateur()+"',mot_de_pass='"+utilisateur.getMotDePassUtilisateur()+"',date_de_naissance='"+new SimpleDateFormat("yyyy/MM/dd").format(utilisateur.getDateDeNaissanceUtilisateur())+"',num_rue="+utilisateur.getNumRueUtilisateur()+",nom_rue='"+utilisateur.getNomRueUtilisateur()+"',ville='"+utilisateur.getVilleUtilisateur()+"',code_postal='"+utilisateur.getCodePostalUtilisateur()+"',province='"+utilisateur.getProvinceUtilisateur()+"',pays='"+utilisateur.getPaysUtilisateur()+"',tel='"+utilisateur.getTelUtilisateur()+"',isChecked="+utilisateur.isCheckedUtilisateur()+",id_Check='"+utilisateur.getIdCheckUtilisateur()+"' WHERE id_utilisateur="+utilisateur.getIdUtilisateur()+";");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
            updated = true;
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       /**
        * rafraichir la liste des utilisateurs par la premiere methode
        */
       refreshListeUtilisateur();
       
       return updated;
   }
   
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Utilisateur getByIdUtilisateur(int idUtilisateur)
    {
        for (Utilisateur utilisateur : listeUtilisateur)
        {
            if (utilisateur.getIdUtilisateur() == idUtilisateur)
            {
                return utilisateur;
            }
        }
        
        return null;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Utilisateur getByNameByPrenomUtilisateur(String nomUtilisateur, String prenomUtilisateur)
    {
        for (Utilisateur utilisateur : listeUtilisateur)
        {
            if (utilisateur.getNomUtilisateur().equals(nomUtilisateur) && utilisateur.getPrenomUtilisateur().equals(prenomUtilisateur))
            {
                return utilisateur;
            }
        }
        
        return null;
    }
      
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Utilisateur getByEmailUtilisateur(String emailUtilisateur)
    {
        for (Utilisateur utilisateur : listeUtilisateur)
        {
            if (utilisateur.getE_mailUtilisateur().equals(emailUtilisateur))
            {
                return utilisateur;
            }
        }
        
        return null;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Utilisateur getByIdCheckAndEmail(String e_mail, String idCheck)
    {
        Utilisateur userToReturn = null;
        for (Utilisateur utilisateur : listeUtilisateur)
        {
            if (utilisateur.getE_mailUtilisateur().equals(e_mail) && utilisateur.getIdCheckUtilisateur().equals(idCheck))
            {
                userToReturn = utilisateur;
            }
        }
        
        return userToReturn;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static boolean addUtilisateur(Utilisateur utilisateur)
    {   
        boolean compteCree = false;
        /**
         * il faut ajouter le utilisateur a la base de donnees
         * en faisant un delete avec la jdbc
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            
            int i = statement.executeUpdate("INSERT INTO utilisateur (id_status,nom,prenom,e_mail,mot_de_pass,date_de_naissance,num_rue,nom_rue,ville,code_postal,province,pays,tel,isChecked,id_Check)  VALUES("+utilisateur.getIdStatus()+",'"+utilisateur.getNomUtilisateur()+"', '"+utilisateur.getPrenomUtilisateur()+"', '"+utilisateur.getE_mailUtilisateur()+"', '"+utilisateur.getMotDePassUtilisateur()+"', '"+new SimpleDateFormat("yyyy/MM/dd").format(utilisateur.getDateDeNaissanceUtilisateur())+"', "+utilisateur.getNumRueUtilisateur()+", '"+utilisateur.getNomRueUtilisateur()+"', '"+utilisateur.getVilleUtilisateur()+"', '"+utilisateur.getCodePostalUtilisateur()+"', '"+utilisateur.getProvinceUtilisateur()+"', '"+utilisateur.getPaysUtilisateur()+"', '"+utilisateur.getTelUtilisateur()+"',0,'"+utilisateur.getIdCheckUtilisateur()+"');");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
            //si on arrive ici donc le compte est bien cree
            compteCree = true;
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /**
         * recharger la liste de utilisateur de la BD
         * en faisant un select all dans la table de utilisateur
         */
        refreshListeUtilisateur();
        
        return compteCree;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void removeUtilisateur(Utilisateur utilisateur)
    {        
        /**
         * il faut supprimer le utilisateur de la base de donnees
         * en fesant un delete avec la jdbc
         */
        try {
            //se connecter a la BD
            Connection connexion = BD.ConnexionToBD();
            //creer le statment pour excuter une query
            Statement statement = connexion.createStatement();
            //excuter la query
            int i = statement.executeUpdate("DELETE FROM utilisateur WHERE id_utilisateur="+utilisateur.getIdUtilisateur()+";");
           
            //fermer la connexion vers la BD
            BD.CloseConnexionToBD(connexion);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarqueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * recharger la liste de utilisateur de la BD
         * en faisant un select all dans la table de utilisateur
         */
        refreshListeUtilisateur();
    }
}

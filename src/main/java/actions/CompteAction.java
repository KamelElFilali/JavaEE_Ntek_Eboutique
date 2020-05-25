/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import managers.*;
import entites.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.MailManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author narib
 */
public class CompteAction {
    
    public static void initialiserListesDepuisLaBD()
    {
        UtilisateurManager.refreshListeUtilisateur();
        PromotionManager.refreshListePromotion();
        MarqueManager.refreshListeMarque();
        CategorieManager.refreshListeCategorie();
        ImageProduitManager.refreshListeImageProduit();
        ProduitManager.refreshListeProduit();
        CommandeManager.refreshListeCommande();
    }
    
    public static int SeConnecter(HttpServletRequest req,String e_mail, String motDePass)
    {
        int etatCompteUser = 0;
        //obtenir le login de user et l'entregistrer sur la session sinon on fait rien
        Utilisateur user = UtilisateurManager.getLoginUtilisateur(e_mail, motDePass);
        if (user != null && user.isCheckedUtilisateur())
        {
            //le user est checke et dans la session maintenant
            SessionManager.SetUser(req, user);
            etatCompteUser = 2;
        }
        else if (user != null && !user.isCheckedUtilisateur())
        {
            //le user est correct mais il n'est pas checke
            etatCompteUser = 1;
        }
        else if (user == null)
        {
            //le user n'est pas correct
            etatCompteUser = 0;
        }
        
        return etatCompteUser;
    }
    
    public static boolean CreationCompte(HttpServletRequest req)
    {
        if (req.getParameter("email") != null && UtilisateurManager.getByEmailUtilisateur(req.getParameter("email")) != null)
        {
            //en cas d'existance d'un compte avec cet e-mail
            req.setAttribute("email_incorrecte", "un compte avec cet e-mail existe deja, veuillez utilser un autre e-mail");
            return false;
        }

        //c'est pour obtenir la date de naissance avec la format correcte
        Date dateDeNaissance = new Date();
        try {
            dateDeNaissance = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("date_de_naissance"));
        } catch (ParseException ex) {
            Logger.getLogger(CompteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Utilisateur user = new Utilisateur(Integer.parseInt(req.getParameter("status")), req.getParameter("nom"), req.getParameter("prenom"), req.getParameter("email"), req.getParameter("password"), dateDeNaissance, Integer.parseInt(req.getParameter("num_rue")), req.getParameter("nom_rue"), req.getParameter("ville"), req.getParameter("code_postal"), req.getParameter("province"), req.getParameter("pays"), req.getParameter("tel"), false, UUID.randomUUID().toString());

        //creer le compte et renvoyer un true sinon un false
        if (UtilisateurManager.addUtilisateur(user))
        {
            /**
             * envoyer un mail de check
             */
            EnvoyerMessageCheckUtilisateur(user);
            
            return true;
        }
        
        //on cas de probleme lors de la creation
        req.setAttribute("creation_incorrecte", "la creation du compte rencontre un probleme, veuillez reasseayer apres quelque menute. merci");
        
        return false;
    }
    
    public static boolean ConfigurationCompte(HttpServletRequest req, Utilisateur user)
    {
        //modifier compte et renvoyer un false
        if (UtilisateurManager.UpdateUtilisateur(user))
        {
            SessionManager.SetUser(req, user);
            return true;
        }
        
        return false;
    }
     
    public static boolean isConnected(HttpServletRequest req)
    {
        //voir si le client est connecte
        if (SessionManager.getUser(req) != null)
        {
            return true;
        }
        
        return false;
    }
    
    public static void Deconnexion(HttpServletRequest req)
    {
        //deconnecter le client
        SessionManager.removeUser(req);
    }
    
    public static void EnvoyerMessageAuxAdmin(String nom, String prenom, String e_mail, String sujet,String commentaire)
    {
        //envoyer un message aux admin
        /**
         * il faut envoyer le Message du client a tout les admins
         * 
         * on utilise : req->user, Message, getAdmin()
         */
        for (Utilisateur admin : UtilisateurManager.getAllAdmin())
        {
            String message = "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "    <head>\n" +
                        "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "        <title>JSP Page</title>\n" +
                        "    </head>\n" +
                        "    <body>\n" +
                        "    \n" +
                        "    <h1 style=\"width: 150px; margin: auto;text-align: center; color: white; background-color: black\">N-TeK</h1>\n" +
                        "        <h2 style=\"width: 30%; margin: auto;text-align: center; color: black;\">Bonjour "+admin.getNomUtilisateur()+" "+admin.getPrenomUtilisateur()+"</h2><br/>\n" +
                        "        <h3 style=\"width: 30%; margin: auto;text-align: center; color: black;\">Message d'un client chez N-TeK</h3>\n" +
                        "        \n" +
                        "        <br/>\n" +
                        "        \n" +
                        "        <ul style=\"width: 60%; margin: auto; color: black;\">\n" +
                        "            <li>Nom : "+nom+"</li>\n" +
                        "            <li>Prenom : "+prenom+"</li>\n" +
                        "            <li>Email : "+e_mail+"</li>\n" +
                        "            <li>sujet : "+sujet+"</li><br/>\n" +
                        "            <li>Commentaire: "+commentaire+"</li>\n" +
                        "        </ul><br/>\n" +
                        "    </body>\n" +
                        "</html>";
            
            String subject  = "Message d'un client N-TeK";
            
            MailManager.sendEmail(message, admin.getE_mailUtilisateur(), subject);
        }
        
    }
    
    public static void EnvoyerMessageCheckUtilisateur(Utilisateur user)
    {
        //envoyer un message aux clients
        /**
         * il faut envoyer le Message au client
         * on cree le html/css du message 
         * 
         * on utilise : req->user, Message, getAdmin()
         */
           String message = "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "    <head>\n" +
                            "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                            "        <title>JSP Page</title>\n" +
                            "    </head>\n" +
                            "    <body>\n" +
                            "        <h1 style=\"width: 150px; margin: auto;text-align: center; color: white; background-color: black\">N-TeK</h1>\n" +
                            "        <h2 style=\"width: 30%; margin: auto;text-align: center; color: black;\">Bonjour "+user.getNomUtilisateur()+" "+user.getPrenomUtilisateur()+"</h2><br/>\n" +
                            "        <h3 style=\"width: 30%; margin: auto;text-align: center; color: black;\">Validation de votre compte chez N-TeK</h3>\n" +
                            "        \n" +
                            "        <br/>\n" +
                            "        <ul style=\"width: 80%; margin: auto; color: black;\">\n" +
                            "            <li>vous avez cree un compte avec cet e_mail : "+user.getE_mailUtilisateur()+"</li>\n" +
                            "            <li>votre mot de pass du compte n-tek est : "+user.getMotDePassUtilisateur()+"</li>\n" +
                            "        </ul><br/>\n" +
                            "        <p style=\"width: 80%; margin: auto; color: black;\">pour valider votre compte veuillez cliquer sur ce lien : <a href=\"http://localhost:8080/TP_N-TeK/?controller=validation&email="+user.getE_mailUtilisateur()+"&idCheck="+user.getIdCheckUtilisateur()+"\">notre site N-TeK</a></p>\n" +
                            "    </body>\n" +
                            "</html>";
           
           String sujet="Validation Compte N-TeK";
           
           MailManager.sendEmail(message, user.getE_mailUtilisateur(), sujet);
    }
    
    public static void ValidationCompteUtilisateur(String emailClient, String idCheck)
    {
        /**
         * validation compte avec l'id et l'email recus
         */
        Utilisateur userToUpdate = UtilisateurManager.getByIdCheckAndEmail(emailClient, idCheck);
        if (userToUpdate != null)
        {
            userToUpdate.setCheckedUtilisateur(true);
            UtilisateurManager.UpdateUtilisateur(userToUpdate);
        }
    }
    
}

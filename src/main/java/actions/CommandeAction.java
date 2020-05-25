/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import entites.Commande;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import managers.*;
import entites.*;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Iterator;
import services.MailManager;

/**
 *
 * @author narib
 */
public class CommandeAction {

    public static boolean PayerCommande(HttpServletRequest req) {
        Date date = new Date();//on cree un objet date de java.util.date qui contient la date actuel par defaut 
        //verifier ces deux lignes
        Commande commande = new Commande(SessionManager.getUser(req).getIdUtilisateur(), SessionManager.getTotalPricePanierWithPromotion(req), date);

        //ajouter la commande et obtenir son id et l'ajouter a l'objet commande
        int idCommandeAjoute = CommandeManager.addCommande(commande);

        commande.setIdCommande(idCommandeAjoute);

        if (idCommandeAjoute != 0) {
            /**
             * ajouter les produits du panier avec l'id de la commande a la BD dans table produits_commande
             */
            CommandeManager.addProduitsCommande(idCommandeAjoute, SessionManager.getAllProduitPanier(req));

            /**
             * il faut envoyer la facture au client par mail comme preuve d'achat
             */
            String messageCommande = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                    + "        <title>JSP Page</title>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "    \n"
                    + "    <h1 style=\"width: 150px; margin: auto;text-align: center; color: white; background-color: black\">N-TeK</h1>\n"
                    + "        <h2 style=\"width: 30%; margin: auto;text-align: center; color: black;\">Bonjour " + SessionManager.getUser(req).getNomUtilisateur() + " " + SessionManager.getUser(req).getPrenomUtilisateur() + "</h2><br/>\n"
                    + "        <h3 style=\"width: 30%; margin: auto;text-align: center; color: black;\">Facture d'achat chez N-TeK</h3>\n"
                    + "        \n"
                    + "        <br/>\n"
                    + "        \n"
                    + "        <ul style=\"width: 40%; margin: auto; color: black;\">\n";

            
            
            HashMap<Produit, Integer> listeProduitQuantiteDuPanier = SessionManager.getAllProduitPanier(req);
            Iterator listeProduitPanier = listeProduitQuantiteDuPanier.keySet().iterator();
            while (listeProduitPanier.hasNext()) {
                Produit produit = (Produit) listeProduitPanier.next();
                //on afficher la quantite le nom du produit et son prix total en utilisant le cast
                messageCommande += "        <li>" + listeProduitQuantiteDuPanier.get(produit) + " " + produit.getNomProduit() + " : " + produit.getPrixProduit() * listeProduitQuantiteDuPanier.get(produit) + "</li>\n";
            }
            messageCommande += "          <br/><li style=\"text-align: center; list-style-type: none;\">prix total : " + SessionManager.getTotalPricePanier(req) + "</li>\n"
                    + "          <br/><li style=\"text-align: center; list-style-type: none;\">prix total apres rabais : " + SessionManager.getTotalPricePanierWithPromotion(req) + "</li>\n"
                    + "          <br/><li style=\"text-align: center; list-style-type: none;\">num carte : " + req.getParameter("numCarte") + "</li>\n"
                    + "          <br/><li style=\"text-align: center; list-style-type: none;\">nom carte : " + req.getParameter("nomCarte") + "</li>\n"
                    + "          <br/><li style=\"text-align: center; list-style-type: none;\">date d'expiration : " + req.getParameter("dateExpiration") + "</li>\n"
                    + "          <br/><li style=\"text-align: center; list-style-type: none;\">code Securite : " + req.getParameter("codeSecurite") + "</li>\n"
                    + "        </ul><br/>\n"
                    + "    </body>\n"
                    + "</html>";

            String sujetCommande = "Facture N-Tek";

            //envoyer le mail au client
            MailManager.sendEmail(messageCommande, SessionManager.getUser(req).getE_mailUtilisateur(), sujetCommande);

            //vider le panier apres le payement
            SessionManager.clearProduitsPanier(req);
            
            if(req.getSession().getAttribute("promotion") != null)
            {
                PromotionManager.desactivatePromotion((Promotion)req.getSession().getAttribute("promotion"));
                req.getSession().removeAttribute("promotion");
            }
            return true;
        }

        return false;
    }

    public static ArrayList<Commande> AfficherTouteLesCommande() {
        return CommandeManager.getAllCommande();
    }

}

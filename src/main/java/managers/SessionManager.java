/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entites.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author narib
 */
public class SessionManager {
    private static Utilisateur utilisateur;
    private static HashMap<Produit, Integer> listeProduitPanier = new HashMap<>();
          
//--------------------------------------------------------------------------------------------------------------------------------------------------------
  
    public static Utilisateur getUser(HttpServletRequest request)
    {
        //on rentre dans la session du user et on prend l'objet user 
        utilisateur = (Utilisateur)request.getSession().getAttribute("user");
        return utilisateur;
    }
          
//--------------------------------------------------------------------------------------------------------------------------------------------------------
  
    public static void SetUser(HttpServletRequest request, Utilisateur utilisateurEnvoye)
    {
        utilisateur = utilisateurEnvoye;
        //on rentre dans la session du user et on sauvgarde l'objet utilisateur a l'interieur
        request.getSession().setAttribute("user", utilisateur);
    }
           
//--------------------------------------------------------------------------------------------------------------------------------------------------------
 
    public static void removeUser(HttpServletRequest request)
    {
        utilisateur = null;
        //on rentre dans la session du user et on sauvgarde l'objet utilisateur a l'interieur
        request.getSession().removeAttribute("user");
    }
         
//--------------------------------------------------------------------------------------------------------------------------------------------------------
   
    public static HashMap<Produit, Integer> getAllProduitPanier(HttpServletRequest request)
    {
        //on rentre dans la session du user et on prend la liste des produit de son panier
        listeProduitPanier = (HashMap<Produit, Integer>)request.getSession().getAttribute("panier");
        return listeProduitPanier;
    }
            
//--------------------------------------------------------------------------------------------------------------------------------------------------------
  
    public static void clearProduitsPanier(HttpServletRequest request)
    {
        request.getSession().removeAttribute("panier");
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addProduitPanier(HttpServletRequest request, Produit produit,int quantite)
    {
        //prendre la liste des produit contenues dans la session du user
        getAllProduitPanier(request);
        
        //si le panier est compelent vide on initialise la liste pour ne pas avoir un null pointer 
        if (listeProduitPanier == null)
        {
            listeProduitPanier = new HashMap<>();
        }
        
        //si le produit existe deja dans la session du user on ajoute la quantite 
        //sinon on ajoute le produit a liste avec cette quantite
        if (listeProduitPanier.get(produit) != null)
        {
            addQuantiteProduit(request, produit, quantite);
        }
        else
        {
            listeProduitPanier.put(produit, quantite);
        }
        
        //on sauvgarde la liste des produits dans la session du user 
        //apres avoir fait notre travaille sur elle
        request.getSession().setAttribute("panier", listeProduitPanier);
    }
            
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void removeProduitPanier(HttpServletRequest request, Produit produit)
    {
        //prendre la liste des produit contenues dans la session du user
        getAllProduitPanier(request);
               
        //si le panier est compelent vide on initialise la liste pour ne pas avoir un null pointer 
        if (listeProduitPanier == null)
        {
            listeProduitPanier = new HashMap<>();
        }
        
        //on supprimer le produit dans la liste
        listeProduitPanier.remove(produit);
        
        //on sauvgarde la liste des produits dans la session du user 
        //apres avoir fait notre travaille sur elle
        request.getSession().setAttribute("panier", listeProduitPanier);
    }
            
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static int getQuantiteProduit(HttpServletRequest request, Produit produit)
    {
        //prendre la liste des produit contenues dans la session du user
        getAllProduitPanier(request);
        
        //si le panier est compelent vide on initialise la liste pour ne pas avoir un null pointer 
        if (listeProduitPanier == null)
        {
            listeProduitPanier = new HashMap<>();
        }
        
        return listeProduitPanier.get(produit);
    }
            
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addQuantiteProduit(HttpServletRequest request, Produit produit, int quantite)
    {
        //prendre la liste des produit contenues dans la session du user
        getAllProduitPanier(request);
        
        //si le panier est compelent vide on initialise la liste pour ne pas avoir un null pointer 
        if (listeProduitPanier == null)
        {
            listeProduitPanier = new HashMap<>();
        }
        
        listeProduitPanier.put(produit, listeProduitPanier.get(produit) + quantite); //ajouter la quantite envoyer a la quantite existante
    }
                
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static double getTotalPricePanier(HttpServletRequest request)
    {
        //prendre la liste des produit contenues dans la session du user
        getAllProduitPanier(request);
        
        //si le panier est compelent vide on initialise la liste pour ne pas avoir un null pointer 
        if (listeProduitPanier == null)
        {
            listeProduitPanier = new HashMap<>();
        }
        
        double montant = 0;
        Iterator listeProduitNom = listeProduitPanier.keySet().iterator();
        
        while(listeProduitNom.hasNext())
        {
            Produit produit = (Produit)listeProduitNom.next();
            montant += produit.getPrixProduit() * listeProduitPanier.get(produit);
        }
        
        return montant;
    }
             
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static double getTotalPricePanierWithPromotion(HttpServletRequest req)
    {
        double montant;
        if (req.getSession().getAttribute("promotion") != null)
        {
            montant = getTotalPricePanier(req) - getTotalPricePanier(req) * ((Promotion)req.getSession().getAttribute("promotion")).getRabaisPromotion() / 100;
        }
        else
        {
            montant = getTotalPricePanier(req);
        }
        
        return montant;
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void addPromotionToPanier(HttpServletRequest req, Promotion promotion)
    {
        req.getSession().setAttribute("promotion", promotion);
    }
}

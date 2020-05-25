/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import entites.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import managers.*;


/**
 *
 * @author narib
 */
public class ProduitAction {
    public static DecimalFormat df = new DecimalFormat("#.##");//c'est pour formater les doubles en 2 nombre apres la virgule
    
    public static void AfficherRechercheProduit(HttpServletRequest req, String caracters)
    {
        //retourner la liste produit recherche
         req.setAttribute("produits", ProduitManager.searchProduit(caracters));
    }
    
    public static ArrayList<Produit> AfficherToutLesProduits()
    {
        //retourner la liste de tout les produits
        return ProduitManager.getAllProduit();
    }
    
    public static Produit AfficherProduitParId(int idProduit)
    {
        return ProduitManager.getByIdProduit(idProduit);
    }
    
    public static ArrayList<Produit> AfficherProduitParMarque(int idMarque)
    {
        //retourner la liste produit recherche par marque
        return ProduitManager.getByIdMarque(idMarque);
    }
    
    public static ArrayList<Produit> AfficherProduitParCategorie(int idCategorie)
    {
        //retourner la liste produit recherche par categorie
        return ProduitManager.getByIdCategorie(idCategorie);
    } 
    
    public static ArrayList<Produit> AfficherProduitParMarqueEtCategorie(int idMarque, int idCategorie)
    {
        return ProduitManager.getByIdMarqueAndIdCategorie(idMarque, idCategorie);
    }
    
    public static void AjouterProduit(String nomMarque, String nomCategorie, int codeProduit, String nomProduit, double prixProduit, int stockProduit)
    {
        //ajouter le produit a BD 
        ProduitManager.addProduit(new Produit(CategorieManager.getByNameCategorie(nomCategorie).getIdCategorie(), MarqueManager.getByNameMarque(nomMarque).getIdMarque(), codeProduit, nomProduit, prixProduit, stockProduit));
    }
    
    public static void SupprimerProduit(int idProduit)
    {
        //supprimer le produit avec son id recu depuis le JSP
        ProduitManager.removeProduit(ProduitManager.getByIdProduit(idProduit));
    }
    
    public static ArrayList<Marque> AfficherToutesLesMarques()
    {
        //renvoyer la liste des toutes les marques
        return MarqueManager.getAllMarque();
    }
    
    public static void AjouterMarque(String nomMarque, String imageMarque)
    {
        //ajouter la marque a la BD
        MarqueManager.addMarque(new Marque(nomMarque,imageMarque));
    }
    
    public static void SupprimerMarque(int idMarque)
    {
        //supprimer la marque avec son id
        MarqueManager.addMarque(MarqueManager.getByIdMarque(idMarque));
    }
    
    public static ArrayList<Categorie> AfficherToutesLesCategories()
    {
        //renvoyer la liste des toutes les categorie
        return CategorieManager.getAllCategorie();
    }
    
    public static void AjouterCategorie(String nomCategorie, String imageCategorie)
    {
        //ajouter la categorie a la BD
        CategorieManager.addCategorie(new Categorie(nomCategorie, imageCategorie));
    }
    
    public static void SupprimerCategorie(int idCategorie)
    {
        //supprimer la categorie avec son id
        CategorieManager.removeCategorie(CategorieManager.getByIdCategorie(idCategorie));
    }
    
    public static HashMap<Produit, Integer> AfficherPanier(HttpServletRequest req)
    {
        //afficher le panier selon la session
        return SessionManager.getAllProduitPanier(req);
    }
    
    public static void AjouterProduitAuxPanier(HttpServletRequest req, int idProduit, int quantite)
    {
        //ajouter le produit aux panier ou la quantite du produit si il existe deja dans le panier
        if (ProduitManager.getByIdProduit(idProduit) != null && quantite > 0)
        {
            SessionManager.addProduitPanier(req, ProduitManager.getByIdProduit(idProduit), quantite);
        }
    }
    
    public static void EnleverProduitDansPanier(HttpServletRequest req, int idProduit)
    {
        //enlever le produit du panier
        if (idProduit > 0)
        {
            SessionManager.removeProduitPanier(req, ProduitManager.getByIdProduit(idProduit));
        }
    }
    
    public static void AfficherPrixTotalDuPanier(HttpServletRequest req)
    {
        req.setAttribute("totalPricePanier",df.format(SessionManager.getTotalPricePanier(req)));
    }
    
    public static void AfficherPrixTotalDuPanierAvecPromotion(HttpServletRequest req)
    {
        req.setAttribute("totalPricePanierWithPromotion",df.format(SessionManager.getTotalPricePanierWithPromotion(req)));
    }
    
    public static ArrayList<Promotion> AfficherToutesLesPromotions()
    {
        //renvoyer la liste des promotion
        return PromotionManager.getAllPromotion();
    }
    
    public static void AjouterPromotion(String codePromotion,int rabais_promotion)
    {
        //ajouter la promotion a la BD
        PromotionManager.addPromotion(codePromotion, rabais_promotion);
    }
    
    public static void SupprimerPromotion(String codePromotion)
    {
        //supprimer la promotion de la BD
        PromotionManager.removePromotion(codePromotion);
    }
    
    public static void AppliquerPromotionAuPanier(HttpServletRequest req, String codePromotion)
    {
        if (PromotionManager.getByCodePromotion(codePromotion) != null && !PromotionManager.getByCodePromotion(codePromotion).isIsExpire())
        {
            SessionManager.addPromotionToPanier(req, PromotionManager.getByCodePromotion(codePromotion));
        }
    }
}

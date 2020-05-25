/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import actions.*;
import entites.Categorie;
import entites.Utilisateur;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import managers.MarqueManager;
import managers.CategorieManager;
import managers.MarqueManager;
import managers.ProduitManager;
import managers.SessionManager;
import managers.UtilisateurManager;
import services.MailManager;

/**
 *
 * @author narib
 */
@WebServlet(name = "ActionController", urlPatterns = {"/actionController"})
public class ActionController extends HttpServlet {
    private boolean firstCall = true;
    //private static DecimalFormat df = new DecimalFormat("0.00");
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        //si c'est la premiere visite dans le serveur on rafraichit les liste des donnees 
        if (this.firstCall)
        {
            CompteAction.initialiserListesDepuisLaBD();
            
            this.firstCall = false;
        }
        // on commence par cette page car on est entrain de tester
          String urlRedirection = "WEB-INF/accueil.jsp";


        
        if (req.getParameter("controller") != null)
        {
    //le client cherche la page de nouveaute ===============================================
            if (req.getParameter("controller").equals("nouveaute"))
            {
                req.setAttribute("produits", ProduitAction.AfficherToutLesProduits());
                urlRedirection = "WEB-INF/nouveautesPage.jsp";
            }
            
    //le client cherche la page de contact ===============================================
            else if (req.getParameter("controller").equals("contact"))
            {
                urlRedirection = "WEB-INF/contactPage.jsp";
            }
            
    //le client cherche la page de livraison ===============================================
            else if (req.getParameter("controller").equals("livraison"))
            {
                urlRedirection = "WEB-INF/livraisonPage.jsp";
            }
    //le client cherche la page de connexion ===============================================

            else if (req.getParameter("controller").equals("connexion"))
            {
                urlRedirection = "WEB-INF/connexionPage.jsp";
            }
            
    //le client veut se deconnecter ===============================================
            else if (req.getParameter("controller").equals("deconnexion"))
            {
                CompteAction.Deconnexion(req);
                urlRedirection = "WEB-INF/connexionPage.jsp";
            }
            
    //le client veut creer un compte ==============================================
            else if (req.getParameter("controller").equals("creationCompte"))
            {
                urlRedirection = "WEB-INF/CreationComptePage.jsp"; // soit revenir a la page d'accueil ou rester dans la meme page ou voir panier
            }
    
    //le client veut creer un compte ==============================================
            else if (req.getParameter("controller").equals("configurationCompte"))
            {
                urlRedirection = "WEB-INF/configurationComptPage.jsp"; // soit revenir a la page d'accueil ou rester dans la meme page ou voir panier
            }
    //le client veut voir les produits ================================================
            else if (req.getParameter("controller").equals("voirProduit"))
            {
                    if (req.getParameter("voirBy") != null)
                    {
                        switch (req.getParameter("voirBy"))
                        {
                            case "all":
                                req.setAttribute("produits", ProduitAction.AfficherToutLesProduits());
                                urlRedirection = "WEB-INF/recherchePage.jsp";
                                break;
                           
                            case "marque":
                                if (req.getParameter("id_marque") != null) {
                                    req.setAttribute("produits", ProduitAction.AfficherProduitParMarque(Integer.parseInt(req.getParameter("id_marque"))));                                                                   
                                    urlRedirection = "WEB-INF/"+MarqueManager.getByIdMarque(Integer.parseInt(req.getParameter("id_marque"))).getNomMarque()+"Page.jsp";
                                }  
                                break;
                            
                            case "categorie":
                                if (req.getParameter("id_categorie") != null) {
                                    req.setAttribute("produits", ProduitAction.AfficherProduitParCategorie(Integer.parseInt(req.getParameter("id_categorie"))));
                                    urlRedirection = "WEB-INF/"+CategorieManager.getByIdCategorie(Integer.parseInt(req.getParameter("id_categorie"))).getNomCategorie()+"Page.jsp";                               
                                }  
                                break;
                            case "marque_categorie" :
                                if (req.getParameter("id_marque") != null && req.getParameter("id_categorie") != null)
                                {
                                    req.setAttribute("produits", ProduitAction.AfficherProduitParMarqueEtCategorie(Integer.parseInt(req.getParameter("id_marque")), Integer.parseInt(req.getParameter("id_categorie"))));
                                    urlRedirection = "WEB-INF/"+MarqueManager.getByIdMarque(Integer.parseInt(req.getParameter("id_marque"))).getNomMarque()+"Page.jsp?par="+req.getParameter("id_categorie")+"";                    
                                }
                                break;
                            case "produit":
                                if(req.getParameter("id_produit") != null) 
                                {
                                    req.setAttribute("produit", ProduitAction.AfficherProduitParId(Integer.parseInt(req.getParameter("id_produit"))));
                                    urlRedirection = "WEB-INF/infoProduitPage.jsp";
                                }
                        }
                    
                    }
            }
            
    //le client veut ajouter supprimer voir un produit au panier ===============================================
            else if (req.getParameter("controller").equals("panier"))
            {
                if (req.getParameter("action") != null)
                {
                    switch(req.getParameter("action"))
                    {
                        case "add" :
                            if (req.getParameter("id_produit") != null && req.getParameter("amount") != null)
                            {
                                ProduitAction.AjouterProduitAuxPanier(req, Integer.parseInt(req.getParameter("id_produit")), Integer.parseInt(req.getParameter("amount")));
                                req.setAttribute("panier", ProduitAction.AfficherPanier(req));
                                ProduitAction.AfficherPrixTotalDuPanier(req);
                                ProduitAction.AfficherPrixTotalDuPanierAvecPromotion(req);
                                urlRedirection = "WEB-INF/panierPage.jsp";
                            }
                            break;
                        case "show" :
                                req.setAttribute("panier", ProduitAction.AfficherPanier(req));
                                ProduitAction.AfficherPrixTotalDuPanier(req);
                                ProduitAction.AfficherPrixTotalDuPanierAvecPromotion(req);
                                urlRedirection = "WEB-INF/panierPage.jsp";
                            break;
                        case "remove" :
                               if(req.getParameter("id_produit") != null)
                               {
                                   ProduitAction.EnleverProduitDansPanier(req, Integer.parseInt(req.getParameter("id_produit")));
                                   ProduitAction.AfficherPrixTotalDuPanier(req);
                                   ProduitAction.AfficherPrixTotalDuPanierAvecPromotion(req);
                                   req.setAttribute("panier", ProduitAction.AfficherPanier(req));//pour l'instant seulement
                                   urlRedirection = "WEB-INF/panierPage.jsp";
                               }
                            break;
                    }
                }
            }
    
    //le client veut valider sont compte ===============================================
            else if (req.getParameter("controller").equals("validation") && req.getParameter("email") != null && req.getParameter("idCheck") != null)
            {
                CompteAction.ValidationCompteUtilisateur(req.getParameter("email"), req.getParameter("idCheck"));
                urlRedirection = "WEB-INF/connexionPage.jsp";
            }
    
    //le client veut payer une commande ===============================================
            else if (req.getParameter("controller").equals("paiement"))
            {
                ProduitAction.AfficherPrixTotalDuPanier(req);
                ProduitAction.AfficherPrixTotalDuPanierAvecPromotion(req);
                urlRedirection = "WEB-INF/paimentPage.jsp";
            }
        }
       
        
        
        //on prend l'url de redirection et on dispatch la requete vers la page JSP correcspondante
        RequestDispatcher dsp = req.getRequestDispatcher(urlRedirection);
        dsp.forward(req, resp);
    }

               
//--------------------------------------------------------------------------------------------------------------------------------------------------------

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        //si c'est la premiere visite dans le serveur on rafraichit les liste des donnees 
        if (this.firstCall)
        {
            CompteAction.initialiserListesDepuisLaBD();
            
            this.firstCall = false;
        }
        
        String urlRedirection = "WEB-INF/accueil.jsp";
        
        //si le parametre controller existe sinon on dispatch vers la page par defaut
        if (req.getParameter("controller") != null)
        {
            //le client essaye de se connecter ===================================================================================================================
            if (req.getParameter("controller").equals("connexion") && req.getParameter("e_mail") != null && req.getParameter("password") != null)
            {
                //on connecte le client et on recupere l'etat de sa connexion
                int etatConnexion = CompteAction.SeConnecter(req, req.getParameter("e_mail"), req.getParameter("password"));
                
                switch (etatConnexion) {
                //le user est connecte et son compte est checke
                    case 2:
                        urlRedirection = "WEB-INF/accueil.jsp";
                        break;
                //le user est correcte mais son compte n'est pas checke
                    case 1:
                        urlRedirection = "WEB-INF/connexionPage.jsp?authentification=unchecked";
                        break;
                //le user n'est pas correcte
                    case 0:
                        urlRedirection = "WEB-INF/connexionPage.jsp?authentification=incorrecte";
                        break;
                    default:
                        break;
                }
            }
            //le client essaye d'ajouter une promotion a son panier ===================================================================================================================
            else if (req.getParameter("controller").equals("ajouter promotion") && req.getParameter("codePromotion") != null)
            {
                ProduitAction.AppliquerPromotionAuPanier(req, req.getParameter("codePromotion"));
                ProduitAction.AfficherPrixTotalDuPanier(req);
                ProduitAction.AfficherPrixTotalDuPanierAvecPromotion(req);
                req.setAttribute("panier", ProduitAction.AfficherPanier(req));
                urlRedirection = "actionController?controller=panier&action=show"; 
            }
            
            //le client essaye de faire une recherche ===================================================================================================================
            else if (req.getParameter("controller").equals("recherche") && req.getParameter("cle") != null)
            {
                ProduitAction.AfficherRechercheProduit(req, req.getParameter("cle"));
                urlRedirection = "WEB-INF/recherchePage.jsp";
            }
            
            //le client essaye de creer un compte ===================================================================================================================
            else if (req.getParameter("controller").equals("creerCompte"))
            {
                if(CompteAction.CreationCompte(req))
                {
                    urlRedirection = "WEB-INF/connexionPage.jsp";
                }
                else
                {
                    urlRedirection = "WEB-INF/CreationComptePage.jsp"; 
                }
            }

            //le client essaye de configurer son compte ===================================================================================================================
            else if(req.getParameter("controller").equals("valider configuration"))
            {
                    try {
                        if (!req.getParameter("password").equals(req.getParameter("confirmationPassword")))
                        {
                            urlRedirection = "WEB-INF/configurationComptPage.jsp?problemePassword=vos mots de passe ne se correspondent pas";
                        }
                        else if(CompteAction.ConfigurationCompte(req, new Utilisateur(SessionManager.getUser(req).getIdUtilisateur(),SessionManager.getUser(req).getIdStatus(),req.getParameter("nom"),req.getParameter("prenom"),SessionManager.getUser(req).getE_mailUtilisateur(),req.getParameter("password"),new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date_de_naissance")),Integer.parseInt(req.getParameter("num_rue")),req.getParameter("nom_rue"),req.getParameter("ville"),req.getParameter("code_postal"),req.getParameter("province"),req.getParameter("pays"),req.getParameter("tel"),SessionManager.getUser(req).isCheckedUtilisateur(),SessionManager.getUser(req).getIdCheckUtilisateur())))
                        {
                            urlRedirection = "WEB-INF/accueil.jsp";
                        }
                        else
                        {
                            urlRedirection = "WEB-INF/configurationComptPage.jsp?probleme=probleme de serveur, ressayez apres quelques minutes. . .";
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            
            //le client essaye de payer une commande ===================================================================================================================           
            else if (req.getParameter("controller").equals("payer"))
            {
                if (ProduitAction.AfficherPanier(req) == null)
                {
                    ProduitAction.AfficherPrixTotalDuPanier(req);
                    ProduitAction.AfficherPrixTotalDuPanierAvecPromotion(req);
                    urlRedirection = "WEB-INF/paimentPage.jsp?probleme=panierVide";
                }
                else if (CompteAction.isConnected(req))
                {
                    if (req.getParameter("numCarte")!=null && req.getParameter("nomCarte")!=null && req.getParameter("dateExpiration")!=null && req.getParameter("codeSecurite")!=null && CommandeAction.PayerCommande(req))
                    {
                        urlRedirection = "WEB-INF/accueil.jsp";
                    }
                    else
                    {
                        ProduitAction.AfficherPrixTotalDuPanier(req);
                        ProduitAction.AfficherPrixTotalDuPanierAvecPromotion(req);
                        urlRedirection = "WEB-INF/paimentPage.jsp?probleme=serveur";
                    }
                }
                else
                {
                    ProduitAction.AfficherPrixTotalDuPanier(req);
                    ProduitAction.AfficherPrixTotalDuPanierAvecPromotion(req);
                    urlRedirection = "WEB-INF/paimentPage.jsp";
                }
            }
        
        //le client essaye de payer une commande ===================================================================================================================           
        else if (req.getParameter("controller").equals("message"))
        {
            for (Utilisateur admin : UtilisateurManager.getAllAdmin())
            {   
                String unMessage = "envoye par " + req.getParameter("email")+ "<br/><br/>" + req.getParameter("message");
                MailManager.sendEmail(unMessage, admin.getE_mailUtilisateur() , req.getParameter("sujet"));
            }
        }
        }
        
        
        
        //on prend l'url de redirection et on dispatch la requete vers la page JSP correcspondante
        RequestDispatcher dsp = req.getRequestDispatcher(urlRedirection);
        dsp.forward(req, resp);

    }
    
    
   
    
    
}

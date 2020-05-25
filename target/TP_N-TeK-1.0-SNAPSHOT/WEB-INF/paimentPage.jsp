<%-- 
    Document   : paimentPage
    Created on : 17 janv. 2020, 17:02:04
    Author     : narib
--%>

<%@page import="actions.CompteAction"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Enumeration"%>
<%@page import="actions.ProduitAction"%>
<%@page import="java.util.HashMap"%>
<%@page import="entites.Produit"%>
<%@page import="java.util.ArrayList"%>
<%@page import="managers.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entites.Utilisateur"%>
<%@page import="managers.SessionManager"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>


        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inconsolata">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="bootstrap-4.4.1-dist/cssBootstrap/bootstrap-grid.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-4.4.1-dist/cssBootstrap/bootstrap-grid.min.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-4.4.1-dist/cssBootstrap/bootstrap-reboot.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-4.4.1-dist/cssBootstrap/bootstrap-reboot.min.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-4.4.1-dist/cssBootstrap/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-4.4.1-dist/cssBootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>

        <script src="bootstrap-4.4.1-dist/jsBootstrap/bootstrap.bundle.js" type="text/javascript"></script>
        <script src="bootstrap-4.4.1-dist/jsBootstrap/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="bootstrap-4.4.1-dist/jsBootstrap/bootstrap.js" type="text/javascript"></script>
        <script src="bootstrap-4.4.1-dist/jsBootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap-4.4.1-dist/jsBootstrap/jquery-3.4.1.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">

        <style>
            body{
                background-color: rgba(240,237,237,1);
            }
            table, th, td{
                border : 1px solid red;
                border-collapse: collapse;
            }
            .paiement_facture{
                width: 80%;
                display: flex;
                justify-content: space-between;
            }
            .informations{
                width: 75%;
                padding-bottom: 10px;
                padding-top: 0px;
                position: relative;
                left: 50px;
            }
            .facture{
                text-align: center;
                padding: 15px;
                max-height: 400px;
               
                background-color: white;
            }
            input{
                height: 20px;
                background-color: rgba(196,196,196,0.6);
                border: none;
                margin-top: 5px;
                margin-left: 15px;
            }
            .paiement input{
                width: 60%;
            }
            .paiement_button{
                border: none;
                background-color: rgba(130,102,6,0.92);
                height: 40px;
                font-size: 1em;
                color: white;
                padding: 0px 30px;
                position: relative;
                right: 60px;
            }
            .paiement{
                width: 60%;
                position: relative;
                left: 50px;
            }

        </style>
    </head>

    <body>
        <%@include file="header.jsp" %>
        
        <section style="margin-top: 70px; margin-bottom: 70px;">
        
        <div class="paiement_facture">  
            
            <div class="informations">
                <% if (!CompteAction.isConnected(request))
                {%>
                     <h5 style="font-size: 0.9em; width: 95%; border-bottom: 1px solid gray; color: grey; margin: 0px 0px 20px 10px; color: red;">Vous n'etes pas connecte, veuillez vous connecter ou creer un compte pour pouvoir payer votre commande ... merci :)</h5>
                <%}%>
                <%if (request.getParameter("probleme") != null && request.getParameter("probleme").equals("panierVide"))
                {%>
                     <h5 style="font-size: 0.8em; width: 95%; border-bottom: 1px solid gray; color: grey; margin: 0px 0px 0px 10px; color: red;">Votre panier est vide, veuillez le remplir pour pouvoir payer une commande ... merci :)</h5>
                <%}%>
                <%if (request.getParameter("probleme") != null && request.getParameter("probleme").equals("serveur"))
                {%>
                     <h5 style="font-size: 0.8em; width: 95%; border-bottom: 1px solid gray; color: grey; margin: 0px 0px 0px 10px; color: red;">un probleme dans le serveur viens de servenir, vueillez patienter et recommande apres quelque minute ... merci :)</h5>
                <%}%>
                
                <h3 style="border-bottom: 1px solid black;padding-bottom: 5px;">1. ADRESSES DE LIVRAISON</h3>
                <form action="actionController" method="post" style="background-color: white; padding: 10px;">
                    <h5 style="font-size: 0.8em; width: 95%; border-bottom: 1px solid gray; color: grey; margin: 0px 0px 15px 10px;">L'adresse sélectionnée sera utilisée à la fois comme adresse personnelle (pour la facture) et comme adresse de livraison .</h5>
                    <% if (CompteAction.isConnected(request)){
                    Utilisateur user = SessionManager.getUser(request); %>
                    <label for="nom">nom : <input type="text" name="nom" value=<%= user.getNomUtilisateur() %> id="nom" required></label><br/>
                    <label for="prenom">prenom : <input type="text" name="prenom" value=<%= user.getPrenomUtilisateur() %> id="prenom" required></label><br/>
                    <label for="email">email : <input type="email" name="email" value=<%= user.getE_mailUtilisateur() %> id="email" required></label><br/>
                    <label for="num_rue">num_rue : <input type="text" name="num_rue" value=<%= user.getNumRueUtilisateur() %> id="num_rue" required></label><br/>
                    <label for="nom_rue">nom_rue : <input type="text" name="nom_rue" value=<%= user.getNomRueUtilisateur() %> id="nom_rue" required></label><br/>
                    <label for="ville">ville : <input type="text" name="ville" value=<%= user.getVilleUtilisateur() %> id="ville" required></label><br/>
                    <label for="code_postal">code_postal : <input type="text" name="code_postal" value=<%= user.getCodePostalUtilisateur() %> id="code_postal" required></label><br/>
                    <label for="province">province : <input type="text" name="province" value=<%= user.getProvinceUtilisateur() %> id="province" required></label><br/>
                    <label for="pays">pays : <input type="text" name="pays" value=<%= user.getPaysUtilisateur() %> id="pays" required></label><br/>
                    <label for="tel">tel : <input type="text" name="tel" value=<%= user.getTelUtilisateur() %> id="tel" required></label><br/>
                    <%}
                    else {%>
                    <label for="nom">nom : <input type="text" name="nom" id="nom" required></label><br/>
                    <label for="prenom">prenom : <input type="text" name="prenom" id="prenom" required></label><br/>
                    <label for="email">email : <input type="email" name="email" id="email" required></label><br/>
                    <label for="num_rue">num_rue : <input type="text" name="num_rue" id="num_rue" required></label><br/>
                    <label for="nom_rue">nom_rue : <input type="text" name="nom_rue" id="nom_rue" required></label><br/>
                    <label for="ville">ville : <input type="text" name="ville" id="ville" required></label><br/>
                    <label for="code_postal">code_postal : <input type="text" name="code_postal" id="code_postal" required></label><br/>
                    <label for="province">province : <input type="text" name="province" id="province" required></label><br/>
                    <label for="pays">pays : <input type="text" name="pays" id="pays" required></label><br/>
                    <label for="tel">tel : <input type="text" name="tel" id="tel" required></label><br/>
                    <%}%>
                   <!-- <input type="submit" name="controller" value="continuerPaiement" style="background-color: rgba(130,102,6,0.92); color: white;">-->
                </form>
            </div>
            
            <div style="width: 30%; position: absolute; right: 30px;">
                <h3 style="border-bottom: 1px solid black;padding-bottom: 5px;">RESUME COMMAND</h3>
                <div  class="facture">
                <img src="images/icons_site/logo_img_officiel.png" alt="logo N-TeK" style="width: 100px;height:45px;"/>
                
                <% String totalPricePanier = (String) request.getAttribute("totalPricePanier");
                    String totalPricePanierWithPromotion = (String) request.getAttribute("totalPricePanierWithPromotion");
                    if (totalPricePanier == null) {
                        totalPricePanier = "0";
                        totalPricePanierWithPromotion = "0";
                    }
                %>
                <h4 style="margin-top: 25px;">Prix Total : <%= totalPricePanier%>$</h4>
                <h4>Prix Total apres rabais : <%= totalPricePanierWithPromotion%>$</h4>
                </div>
                <!--
                <a href="actionController?controller=paiement" style="position: absolute; bottom: -50px;"><button class="paiement_button">Paiement</button></a>
                -->
            </div>

        </div>
                
                
        <div class="paiement" >
            <h3 style="border-bottom: 1px solid black;padding-bottom: 5px;">2. PAIEMENT</h3>
            <img src="images/icons_site/paiement-securise-garantie 1.png" alt="paiement securise" style="display: block; margin: auto;"/>
            <form action="actionController" method="post" style="background-color: white; width: 50%; margin: auto; min-height: 200px;">
                <h5 style="font-size: 0.9em; border-bottom: 1px solid black; padding: 10px;">Information Carte</h5>
                <input type="text" name="numCarte" placeholder=" Numero de la carte" required><br/>
                <input type="text" name="nomCarte" placeholder=" Nom de la carte" required><br/>
                <input type="text" name="dateExpiration" placeholder=" Date d’expiaration (MM/AA)" required>
                <input type="text" name="codeSecurite" placeholder=" code sécurité" required style="width: 90px;"><br/>
                <input type="submit" name="controller" value="payer" style="background-color: rgba(130,102,6,0.92); margin-top: 20px; color:white; width: 70px;">
            </form>
        </div>
                
        </section>        
                
                <%@include file="footer.jsp" %>
    </body>
</html>

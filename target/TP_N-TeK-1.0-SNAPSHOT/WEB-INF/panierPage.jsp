<%-- 
    Document   : panierPage
    Created on : 17 janv. 2020, 17:01:46
    Author     : narib
--%>

<%@page import="managers.MarqueManager"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                width: 100%;
                height: auto; 
                overflow: hidden;
                font-family: San Francisco , sans-serif;
                background-color: rgba(240,237,237,1);

            }
            .panier_facture{
                width: 90%;
                display: flex;
                justify-content: space-between;
                text-align: center;
                padding: 20px;
                min-height: 500px;
            }
            .panier{
                width: 90%;
                display: flex;
                justify-content: space-between;
                text-align: center;
                border-bottom: 1px solid black;
                padding-bottom: 10px;
                padding-top: 10px;
                margin: auto;
            }
            .panier h4
            {
                width: 25%;
                position: relative;
                top: 20px;
            }
            .facture{
                width: 25%;
                text-align: center;
                background-color: white;
                padding: 15px;
                max-height: 400px;
                position: absolute;
                right: 30px;
            }
            input{
                height: 20px;
                background-color: rgba(196,196,196,0.6);
                border: none;
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

        </style>

    </head>
    <body>
        <%@include file="header.jsp" %>

        <!-- afficher la facture et le panier si il contient des produits-->
        <div class="panier_facture" style="height: 1000px; width: 100% ">   

            <% if (request.getAttribute("panier") != null) {
                HashMap<Produit, Integer> listePanier = (HashMap<Produit, Integer>) request.getAttribute("panier");
                Iterator listeDesProduitPanier = listePanier.keySet().iterator();%>    

            <div style="width: 60%; background-color: white; text-align: center;">
                <%while (listeDesProduitPanier.hasNext()) {
                        Produit produit = (Produit) listeDesProduitPanier.next();
                %>
                <div class="panier">
                    <img src="images/produits/<%= MarqueManager.getByIdMarque(produit.getIdMarque()).getNomMarque()%>/<%= produit.getImageProduit().get(0).getImageProduit()%>" alt="image produit" style="width: 100px; height: 100px">
                    <h4><%= produit.getNomProduit()%></h4>
                    <h4><%= listePanier.get(produit)%></h4>
                    <h4><%= listePanier.get(produit) * produit.getPrixProduit()%>$</h4>

                    <h4>
                        <a href="actionController?controller=panier&action=remove&id_produit=<%= produit.getIdProduit()%>">
                            <img src="images/icons_site/remove_icon.png" alt="enlever produt" style="width:42px; height: 31px; "/>
                        </a>
                    </h4>
                </div>
                <%}%>
            </div>

            <%}%>

            <div class="facture">
                <img src="images/icons_site/logo_img_officiel.png" alt="logo N-TeK" style="width: 90px;height:45px;"/>
                <h4 style="font-size: 1em; margin-top: 20px;">AVEZ-VOUS UN CODE PROMOTIONNEL ?</h4>
                <form action="actionController" method="post" style="padding-bottom: 15px; border-bottom: 1px solid black; margin-top: 20px;">
                    <label for="promo"><input type="text" name="codePromotion" id="promo" maxlength="5" required style="width: 100px;"></label>
                    <input type="submit" name="controller" value="ajouter promotion" style="color: white; background-color: rgba(130,102,6,0.92); height: 20px;">
                </form>
                <% String totalPricePanier = (String) request.getAttribute("totalPricePanier");
                    String totalPricePanierWithPromotion = (String) request.getAttribute("totalPricePanierWithPromotion");
                    if (totalPricePanier == null) {
                        totalPricePanier = "0";
                        totalPricePanierWithPromotion = "0";
                    }
                %>
                <h4>Prix Total : <%= totalPricePanier%>$</h4>
                <h4>Prix Total apres rabais : <%= totalPricePanierWithPromotion%>$</h4>

                <a href="actionController?controller=paiement" style="position: absolute; bottom: -50px;"><button class="paiement_button">Paiement</button></a>
            </div>

        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>

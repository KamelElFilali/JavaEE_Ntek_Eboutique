<%-- 
    Document   : nouveautesPage
    Created on : 17 janv. 2020, 17:02:39
    Author     : narib
--%>

<%@page import="managers.MarqueManager"%>
<%@page import="java.util.ArrayList"%>
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
                background-color: rgba(240,237,237,1);
                height: auto; 
                overflow: hidden;
                font-family: San Francisco , sans-serif;
                
                
            }
            .liste{
                display: flex;
                justify-content: space-around;
                flex-wrap: wrap;
                padding: 30px;
            }
            .affichageProduit{
                width: 25%;
                text-align: center;
                padding: 20px;
                font-size: 1em;
                font-weight: bold;
                color: black;
            }
            .liste a {
                font-size: 1em;
            }
        </style>
        
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1 style="margin-bottom: 20px;"></h1>
        <h1 style="margin: auto; padding: 10px; border: 2px rgba(130,102,6,0.92) outset;background-color: rgba(130,102,6,0.92);color: white; width: 50%; text-align: center; "> Les Derniers Produits Ajoutes : </h1>    

        <div class="liste" style="height:700px;">
        <% if (request.getAttribute("produits") != null) {%>
        <%for (Produit produit : (ArrayList<Produit>) request.getAttribute("produits")) {%>
                <div class="affichageProduit">
                    <figure>
                        <a style="margin-top: 10px;" class="titreProduit" href="actionController?controller=voirProduit&voirBy=produit&id_produit=<%= produit.getIdProduit() %>"><img src="images/produits/<%= MarqueManager.getByIdMarque(produit.getIdMarque()).getNomMarque()%>/<%= produit.getImageProduit().get(0).getImageProduit()%>" style="width: 150px; height: 120px">
                    <figcaption>
                        <%= produit.getNomProduit()%><br/>
                        <%= produit.getPrixProduit()%>$
                    </figcaption></a>
                        <figcaption><a href="actionController?controller=panier&action=add&id_produit=<%= produit.getIdProduit()%>&amount=1">ajouter panier</a></figcaption>
                    </figure>
                </div>
                <%}%>
        <%}%> 
        
        </div>
        
        <%@include file="footer.jsp" %>
    </body>
</html>

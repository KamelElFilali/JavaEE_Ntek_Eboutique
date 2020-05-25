<%-- 
    Document   : produitPage
    Created on : 17 janv. 2020, 17:00:06
    Author     : narib
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="managers.MarqueManager"%>
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
            }
            .produit{
                display: flex;
                justify-content: space-around;
                flex-wrap: wrap;
                padding: 50px;
            }
            .affichageProduit{
                text-align: center;
                padding: 10px;
            }
            input{
                height: 20px;
            }
            input[type="submit"]
            {
                border: none;
                font-family: fantasy;
            }
    </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <div class="produit">
            
        <% if (request.getAttribute("produit") != null) {%>
        <% Produit produit = (Produit)request.getAttribute("produit"); /*for (Produit produit : (ArrayList<Produit>) request.getAttribute("produits")) {*/%>
        <% //}%>
        
                <div class="titreProduit" class="affichageProduit">
                    <figure>
                        <img src="images/produits/<%= MarqueManager.getByIdMarque(produit.getIdMarque()).getNomMarque()%>/<%= produit.getImageProduit().get(0).getImageProduit()%>" style="width: 300px; height: 250px">
                    </figure>
                </div>
                    
                    <div class="infoProduit">
                        <h1 style="margin-bottom: 20px;padding-bottom: 10px; border-bottom: 1px black ridge"><%= produit.getNomProduit()%></h1><br/>
                        <% if (produit.getStockProduit() <= 0){%>
                        <h4 style="color: white; background: red; width: 100px;">RUPTURE DE STOCK</h4><br/>
                        <%}%>
                        
                        <h2>Prix : <%= produit.getPrixProduit()%>$</h2><br/>                       
                        
                        <form action="actionController" method="get" style="margin-top: 20px;">
                            <input type="text" name="controller" value="panier" hidden/>
                            <input type="text" name="action" value="add" hidden/>
                            <input type="text" name="id_produit" value="<%= produit.getIdProduit() %>" hidden/>
                            <input type="number" name="amount" max="<%= produit.getStockProduit()%>" min="0"/>
                            <input type="submit" value="Ajouter panier" style="color: white; background-color: rgba(156,156,156,1);"/>
                        </form>
                    </div>    
                    
        <%}%> 
        
        </div>
        
        <%@include file="footer.jsp" %>
    </body>
</html>

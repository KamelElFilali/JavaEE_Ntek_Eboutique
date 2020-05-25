<%-- 
    Document   : applePage
    Created on : 17 janv. 2020, 16:58:37
    Author     : narib
--%>

<%@page import="managers.CategorieManager"%>
<%@page import="managers.MarqueManager"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorie Apple</title>

        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/styleApplePage.css" rel="stylesheet" type="text/css"/>

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
            .affichageProduit{
                background-color: rgba(240,237,237,1);
                text-align: center;
                padding: 10px;
                font-size: 1em;
            }
            .affichageProduit  .titreProduit{
                font-weight: bold;
                color: black;
            }
            #iphone, #mac{
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
                padding: 20px;
            }
        </style>

    </head>

    <body id="bodyApplePage">
        <%@include file="header.jsp" %>

        <!--************************************ TEXT PRESENTATIONS CATEGORIE APPLE PRODUIT IPHONE ********************************* -->
        
        <div id = "divTextCategorieAppleIphone">  

            <p style="  margin-left: auto; margin-right: auto; ">
                <span class="lettreGold">Q</span>uel i<span class="lettreGold">P</span>hone choisir <span class="lettreGold">?</span> <br>
                <span class="lettreGold">I</span>ls sont tous <span class="lettreGold">F</span>ait pour <span class="lettreGold">V</span>ous <br>
                <span class="lettreGold">L</span>aissez vous <span class="lettreGold">T</span>enter <span class="lettreGold">!</span>  

            </p>

        </div>


        <div id = "divTextCategorieAppleIphone2">  

            <p class="alignP" style=" padding-left: 15px;  "> 
                <a href="actionController?controller=voirProduit&voirBy=marque_categorie&id_marque=1&id_categorie=1" > Voir tout les modèles d’iPhone >  </a>  
            </p>   

            <p class="alignP"  > 
                <a href="https://www.apple.com/ca/fr/iphone/"  > En savoir plus >  </a>  
            </p>   


        </div>


        <!--************************************ IMAGES PRESENTATIONS IPHONE ********************************* -->

        <section class="imgPresentationCategorieAppleIphone">

            <img src="images/images des produits de presentation sur le site/apple/imgCategorieAppleIphone.png" style=" width: 100%;" alt=""/>
        </section>
        
        <div id="iphone">
        <% if (request.getAttribute("produits") != null && request.getParameter("par") != null && request.getParameter("par").equals("1")) {%>
            <%for (Produit produit : (ArrayList<Produit>) request.getAttribute("produits")) {%>
               
            <div class="affichageProduit">
                <figure>
                    <a class="titreProduit" href="actionController?controller=voirProduit&voirBy=produit&id_produit=<%= produit.getIdProduit() %>"><img src="images/produits/<%= MarqueManager.getByIdMarque(produit.getIdMarque()).getNomMarque()%>/<%= produit.getImageProduit().get(0).getImageProduit()%>" style="width: 100px; height: 150px">
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
        <!--************************************ TEXT PRESENTATIONS CATEGORIE APPLE PRODUIT MAC ********************************* -->

        <div id = "divTextCategorieAppleMac">  

            <p style="  margin-left: auto; margin-right: auto; ">
                MacBook <span class="lettreGold">Pro</span>
            </p>

        </div>


        <div id = "divTextCategorieAppleMac2" style="  ">  

            <p class="alignP2" style=" padding-left: 15px;  "> 
                <a href="actionController?controller=voirProduit&voirBy=marque_categorie&id_marque=1&id_categorie=2" style="   "> Voir tout les modèles de Mac >  </a>  
            </p>   

            <p class="alignP2" style="  " > 
                <a href="https://www.apple.com/ca/fr/mac/" style="   "> En savoir plus >  </a>  
            </p>   


        </div>

        <!--************************************ IMAGES PRESENTATIONS MAC ********************************* -->

        <section class="imgPresentationCategorieAppleMac">
            <img src="images/images des produits de presentation sur le site/apple/imgCategorieAppleMac2.png" style=" width: 100%;" alt=""/>
        </section>
        
        <div id="mac" style="background-color: white">
        <!-- si on a clique sur le lient void tout es produtis on afficher la liste des produits -->
        <% if (request.getAttribute("produits") != null && request.getParameter("par") != null && request.getParameter("par").equals("2")) {%>
                <%for (Produit produit : (ArrayList<Produit>) request.getAttribute("produits")) {%>
                <div class="titreProduit" class="affichageProduit">
                    <figure>
                        <a style="margin-top: 10px;" class="titreProduit" href="actionController?controller=voirProduit&voirBy=produit&id_produit=<%= produit.getIdProduit() %>"><img src="images/produits/<%= MarqueManager.getByIdMarque(produit.getIdMarque()).getNomMarque()%>/<%= produit.getImageProduit().get(0).getImageProduit()%>" style="width: 100px; height: 150px">
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

        <!--************************************ IMAGES MARKETING APPLE ********************************* -->

        <section class="imgPresentationCategorieAppleMac">
            <img src="images/images des produits de presentation sur le site/apple/imgMarketingApple.png" style=" width: 100%;"  alt=""/>
        </section>
        

        <%@include file="footer.jsp" %>
    </body>
</html>

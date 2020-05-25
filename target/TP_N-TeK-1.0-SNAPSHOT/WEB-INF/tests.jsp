<%-- 
    Document   : tests
    Created on : 23 janv. 2020, 10:04:46
    Author     : narib
--%>

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
        <style>
            body{
                background-color: rgba(240,237,237,1);
            }
            table, th, td{
                border : 1px solid red;
                border-collapse: collapse;
            }
            .panier_facture{
                width: 90%;
                display: flex;
                justify-content: space-between;
                text-align: center;
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
        <!--
          <h1 style="width: 150px; margin: auto;text-align: center; color: white; background-color: black">N-TeK</h1>
          <h2 style="width: 30%; margin: auto;text-align: center; color: black;">Bonjour -Nom Prenom-</h2><br/>
          <h3 style="width: 30%; margin: auto;text-align: center; color: black;">Validation de votre compte chez N-TeK</h3>
          
          <br/>
          <ul style="width: 80%; margin: auto; color: black;">
              <li>vous avez cree un compte avec cet e_mail : -e_mail-</li>
              <li>votre mot de pass du compte n-tek est : -password-</li>
          </ul><br/>
          <p style="width: 80%; margin: auto; color: black;">pour valider votre compte veuillez cliquer sur ce lien : <a href="http://localhost:8080/TP_N-TeK/?Controller=validation&idCheck=-idCheck-">notre site N-TeK</a></p>
        -->

        <!--
        <h1 style="width: 150px; margin: auto;text-align: center; color: white; background-color: black">N-TeK</h1>
            <h2 style="width: 30%; margin: auto;text-align: center; color: black;">Bonjour -Nom Prenom admin-</h2><br/>
            <h3 style="width: 30%; margin: auto;text-align: center; color: black;">Message d'un client chez N-TeK</h3>
            
            <br/>
            
            <ul style="width: 60%; margin: auto; color: black;">
                <li>Nom :</li>
                <li>Prenom :</li>
                <li>Email : -e_mail-</li>
                <li>sujet : -sujet-</li><br/>
                <li>Commentaire: -message-</li>
            </ul><br/>
        -->

        <!--
        <h1 style="width: 150px; margin: auto;text-align: center; color: white; background-color: black">N-TeK</h1>
            <h2 style="width: 30%; margin: auto;text-align: center; color: black;">Bonjour -Nom Prenom client-</h2><br/>
            <h3 style="width: 30%; margin: auto;text-align: center; color: black;">Facture d'achat chez N-TeK</h3>
            
            <br/>
            
            <ul style="width: 40%; margin: auto; color: black;">
                <li>liste de produit avec quantite et prix</li> <br/>
                <li style="text-align: center; list-style-type: none;">prix total : %=(SessionManager.getUser(request).getNomUtilisateur())% </li>
            </ul><br/>
        -->
        <header>
            
        <h2><a href="actionController?controller=deconnexion">Deconnexion</a></h2>

        <p><a href="actionController?controller=voirProduit&voirBy=all">voir tout les produits</a></p>

        <form action="actionController" method="post" style="margin-bottom: 20px;">
            <input type="text" name="cle">
            <input type="submit" name="controller" value="recherche">
        </form>
        
        <p style="width:60px;height:60px;position:absolute; top: 5px; right: 5px;"><a href="actionController?controller=panier&action=show"><img src="images/icons_site/panier_icon.png" alt="panier" style="width:30px;height: 30px;"/></a>
                <% if (request.getSession().getAttribute("panier") != null && ((HashMap<Produit, Integer>) request.getSession().getAttribute("panier")).size() > 0) {
                %>
            <span style="font-size: 12px;position: relative;bottom: 3px;right: 15px; background-color: red; border-radius: 50%; color: white;display: inline-block;text-align: center; min-width:10px;min-height:10px">
                <%=((HashMap<Produit, Integer>) request.getSession().getAttribute("panier")).size()%></span>
                <%}%>
        </p>
        
        </header>

        <section>
        <!-- si on a clique sur le lient void tout es produtis on afficher la table des produits -->
        <% if (request.getAttribute("produits") != null) {%>
        <table>
            <tr>
                <th>marque</th>
                <th>categorie</th>
                <th>nom produit</th>
                <th>code produit</th>
                <th>prix produit</th>
            </tr>
            <%for (Produit produit : (ArrayList<Produit>) request.getAttribute("produits")) {%>
            <tr>
                <td><%= MarqueManager.getByIdMarque(produit.getIdMarque()).getNomMarque()%></td>
                <td><%= CategorieManager.getByIdCategorie(produit.getIdCategorie()).getNomCategorie()%></td>
                <td><%= produit.getNomProduit()%></td>
                <td><%= produit.getCodeProduit()%></td>
                <td><%= produit.getPrixProduit()%>$</td>
                <td><img src="images/produits/<%= MarqueManager.getByIdMarque(produit.getIdMarque()).getNomMarque()%>/<%= produit.getImageProduit().get(0).getImageProduit()%>" style="width: 50px; height: 50px"></td>
                <td><a href="actionController?controller=panier&action=add&id_produit=<%= produit.getIdProduit()%>&amount=1">ajouter panier</a></td>
            </tr>
            <%}%>
        </table>
        <%}%> 
        
        </section>
    </body>
</html>

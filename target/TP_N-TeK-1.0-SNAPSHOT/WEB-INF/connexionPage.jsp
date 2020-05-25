<%-- 
    Document   : connexionPage
    Created on : 17 janv. 2020, 17:03:12
    Author     : narib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
        
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/styleConnexionPage.css" rel="stylesheet" type="text/css"/>
        
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
         
            input{
                height: 30px;
                background-color: rgba(196,196,196,0.6);
                border: none;
            }
            input[type="submit"], button{
                height: 60px;
                border: none;
                color: white;
                font-size: 15px;
                padding: 0px 30px;
               
            }
        </style>
    </head>
    <body id="bodyConnexionPage">
        <%@include file="header.jsp" %>
       
        <!--prend les html que je fais, enleve seuelemt les style ou tu peux les laisser il sont pret pour ne pas perdre du temps-->
        <section id="sectionConnexion"  >
            <!-- c'et pour creation compte-->
            <div class="CreationCompte" >
                
                <h3>NOUVEAUX CLIENTS</h3> 
                
                <br/>
                
                <p>
                    En créant un compte sur notre boutique, vous pourrez passer vos commandes plus rapidement, 
                    enregistrer plusieurs adresses de livraison, visualiser
                    et suivre vos commandes sur votre compte et plus encore.
                </p>
                
                <a href="actionController?controller=creationCompte">
                    <button > Creer un compte </button>
                </a>
            </div>
            
             <!-- c'et pour connexion compte-->
            <div class="Connexion" style="width: 50%;">
                
                <h3 >CONNECTEZ-VOUS À VOTRE COMPTE</h3> <br/> 
                
                <form action="actionController" method="post">
                    
                    <% if (request.getParameter("authentification") != null && request.getParameter("authentification").equals("incorrecte")) {%>
                    <p style="color: red;text-align: left;">votre e_mail ou password n'est pas correcte veuillez verifier vos informations</p>
                    <%}%>
                    <% if (request.getParameter("authentification") != null && request.getParameter("authentification").equals("unchecked")) {%>
                    <p style="color: red;">votre e_mail ou password n'est pas checke ya wa7ad lfaya7</p>
                    <%}%>
                    <input type="email" name="e_mail" value="nassiminformaticien@gmail.com" required style="margin-bottom: 20px;width :50%; margin-right: 150px;"/><br/>
                    <input type="password" name="password" value="abc123..." required style="margin-bottom: 10px;width :50%; margin-right: 150px;"/><br/>
                    <input type="submit" name="controller" value="connexion" style="background-color: rgba(130,102,6,0.92);margin: auto;"/>
                </form>
            </div>

        </section>
                       <%@include file="footer.jsp"  %>
                 
    </body>
</html>

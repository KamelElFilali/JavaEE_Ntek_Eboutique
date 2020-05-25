<%-- 
    Document   : configurationComptPage
    Created on : 17 janv. 2020, 17:03:25
    Author     : narib
--%>

<%@page import="managers.SessionManager"%>
<%@page import="entites.Utilisateur"%>
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
            form{
                 background: white;
                 padding: 10px;
            }
            input{
                margin-bottom: 10px;
                background-color: rgba(196,196,196,0.6);
                border: none;
            }
            input[type="radio"]{
               
            }
            input[type="submit"], button{
                height: 20px;
                border: none;
                color: white;
                font-size: 8px;
                padding: 0px 18px;
            }
            label{
            }
        </style>
        
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <section style="width: 40%; padding: 20px;">
            <%if(request.getParameter("probleme") != null){%>
            <h4 style="font-size: 0.8em; width: 60%; border-bottom: 1px solid gray; color: grey; margin: 0px 0px 0px 10px; color: red;"><%=request.getParameter("probleme")%></h4>
                <%}%>
            <h3 style="border-bottom: 1px solid black;">CONFIGURATION DU COMPTE</h3>
        <form action="actionController" method="post">
            <%Utilisateur user = SessionManager.getUser(request); %>
            <label for="nom">nom : <input type="text" name="nom" value="<%= user.getNomUtilisateur() %>" id="nom" required></label><br/>
            <label for="prenom">prenom : <input type="text" name="prenom" value="<%= user.getPrenomUtilisateur() %>" id="prenom" required></label><br/>
            <label for="password">password : <input type="password" name="password" value="<%= user.getMotDePassUtilisateur() %>" id="password" required></label><br/>
            <%if(request.getParameter("problemePassword") != null){%>
            <h5 style="font-size: 0.8em; width: 60%; color: grey; margin: 0px 0px 0px 10px; color: red;margin-bottom: 10px;"><%=request.getParameter("problemePassword")%></h5>
            <%}%>
            <label for="confirmationPassword">confirmation password : <input type="password" name="confirmationPassword" value="<%= user.getMotDePassUtilisateur() %>" id="confirmationPassword" required></label><br/>
            <label for="date">date de naissance : <input type="date" name="date_de_naissance" value="<%= user.getDateDeNaissanceUtilisateur() %>" id="date_de_naissance" min="01/01/1950" max="30/12/2010" required></label><br/>
            <label for="num_rue">num_rue : <input type="text" name="num_rue" value="<%= user.getNumRueUtilisateur() %>" id="num_rue" ></label><br/>
            <label for="nom_rue">nom_rue : <input type="text" name="nom_rue" value="<%= user.getNomRueUtilisateur() %>" id="nom_rue" ></label><br/>
            <label for="ville">ville : <input type="text" name="ville" value="<%= user.getVilleUtilisateur() %>" id="ville" ></label><br/>
            <label for="code_postal">code_postal : <input type="text" name="code_postal" value="<%= user.getCodePostalUtilisateur() %>" id="code_postal" ></label><br/>
            <label for="province">province : <input type="text" name="province" value="<%= user.getProvinceUtilisateur() %>" id="province" ></label><br/>
            <label for="pays">pays : <input type="text" name="pays" value="<%= user.getPaysUtilisateur() %>" id="pays" ></label><br/>
            <label for="tel">tel : <input type="text" name="tel" value="<%= user.getTelUtilisateur() %>" id="tel" ></label><br/>
            <input type="submit" name="controller" value="valider configuration" style="background-color: rgba(130,102,6,0.92);">
        </form>
            
        </section>
        
        <%@include file="footer.jsp" %>
    </body>
</html>

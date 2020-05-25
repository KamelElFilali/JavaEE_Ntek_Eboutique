<%-- 
    Document   : CreationComptePage
    Created on : 27 janv. 2020, 13:14:52
    Author     : narib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>

        <link href="css/styleAccueille.css" rel="stylesheet" type="text/css"/>


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
            <h3 style="border-bottom: 1px solid black;">CREE UN COMPTE</h3>
        <form action="actionController" method="post">
            <% if(request.getAttribute("creation_incorrecte") != null)
            {%>
            <label style="color: red;"><%= (String)request.getAttribute("creation_incorrecte") %></label>
            <%}%>
            <h4>Etes vous Client ou Commercant ?</h4>
            <div style="display: flex;justify-content: space-between; width: 30%;">
                <label for="client">client<br/><input type="radio" name="status" id="client" value="1" required></label><br/>
                <label for="commercant">commercant<br/><input type="radio" name="status" id="commercant" value="3" required></label><br/>
            </div>
            <label for="nom">nom : <input type="text" name="nom" id="nom" required></label><br/>
            <label for="prenom">prenom : <input type="text" name="prenom" id="prenom" required></label><br/>
            <% if(request.getAttribute("email_incorrecte") != null)
            {%>
            <label style="color: red;"><%= (String)request.getAttribute("email_incorrecte") %></label>
            <%}%>
            <label for="email">email : <input type="email" name="email" id="email" required></label><br/>
            <label for="password">password : <input type="password" name="password" id="password" required></label><br/>
            <label for="date">date de naissance : <input type="date" name="date_de_naissance" id="date_de_naissance" min="01/01/1950" max="30/12/2010" required></label><br/>
            <label for="num_rue">num_rue : <input type="text" name="num_rue" id="num_rue" ></label><br/>
            <label for="nom_rue">nom_rue : <input type="text" name="nom_rue" id="nom_rue" ></label><br/>
            <label for="ville">ville : <input type="text" name="ville" id="ville" ></label><br/>
            <label for="code_postal">code_postal : <input type="text" name="code_postal" id="code_postal" ></label><br/>
            <label for="province">province : <input type="text" name="province" id="province" ></label><br/>
            <label for="pays">pays : <input type="text" name="pays" id="pays" ></label><br/>
            <label for="tel">tel : <input type="text" name="tel" id="tel" ></label><br/>
            <input type="submit" name="controller" value="creerCompte" style="background-color: rgba(130,102,6,0.92);">
        </form>
            
        </section>
            
            <%@include file="footer.jsp" %>
    </body>
</html>

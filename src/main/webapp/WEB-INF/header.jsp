
        <%@page import="managers.SessionManager"%>
<%@page import="actions.CompteAction"%>
<%@page import="entites.Produit"%>
<%@page import="java.util.HashMap"%>
<header> 

            <!-- DEBUT du block header ou il ya toutes les icones-->

            <section class = "premierBlockHeader"> 


                <div class="flex-container">

                    <div id ="barreDeRechercheHesder">

                        <form action="actionController" method="post">

                            <input id="barreRecherche" class="form-control mr-sm-2" type="search" name="cle"  placeholder="Recherche" aria-label="Search"> <%--barre de recherche--%>
                            
                            <input type="text" name="controller" value="recherche" hidden>
                            
                            <input type="image" id="boutonRechercher" src="images/images des produits de presentation sur le site/icone footer et header/240349.png"  class="btn btn-outline-success my-2 my-sm-0"  > 
                        </form>


                    </div>


                    <div id="logoHeader">

                        <a id= "logoSite" class="navbar-brand" href="actionController">

                            <img src="images/images des produits de presentation sur le site/icone footer et header/logo img officiel.png"  style=" width: 190px; height: 100px;" alt=""> 

                        </a>

                    </div>

                    <div class ="iconeGaucheHeader">

                        <% if(CompteAction.isConnected(request)){%>
                        <div style="background-color: rgba(130,102,6,0.92); border-radius: 50%;display: flex;justify-content: center; text-align: center;color: white; width: 50px; height: 50px;">
                            <h1 style="position: relative;top: 10px;"><%= SessionManager.getUser(request).getPrenomUtilisateur().charAt(0) %><%= SessionManager.getUser(request).getNomUtilisateur().charAt(0) %></h1>
                        </div>
                            <%}%>

                        <div id= "iconeConnexion" >
                            <% if(!CompteAction.isConnected(request)){%>
                            <a class="navbar-brand" href="actionController?controller=connexion">

                                <img src="images/images des produits de presentation sur le site/icone footer et header/icone connexion2.png" style=" width: 50px; height: 50px;"alt=""/>

                            </a>
                            <%}
                              else{%>
                            <a class="navbar-brand" href="actionController?controller=deconnexion">

                                <img src="images/images des produits de presentation sur le site/icone footer et header/icone connexion2.png" style=" width: 50px; height: 50px;"alt=""/>

                            </a>
                            <%}%>
                        </div>
                            
                        <div id= "iconeCompteUtilisateur">
                        <% if(!CompteAction.isConnected(request)){%>
                            <a  class="navbar-brand" href="actionController?controller=connexion">

                                <img src="images/images des produits de presentation sur le site/icone footer et header/iconeCompteUtilisateur2.png" style=" width: 35px; height:35px; margin-top: 7px; margin-left: 10px;" alt=""/>
                            </a>
                        <%}
                          else{%>
                             <a  class="navbar-brand" href="actionController?controller=configurationCompte">

                                <img src="images/images des produits de presentation sur le site/icone footer et header/iconeCompteUtilisateur2.png" style=" width: 35px; height:35px; margin-top: 7px; margin-left: 10px;" alt=""/>
                            </a>
                        <%}%>

                        </div>
                        <div id= "iconePanier">

                            <a  class="navbar-brand" href="actionController?controller=panier&action=show">

                                <img src="images/images des produits de presentation sur le site/icone footer et header/iconePanierHeader.png" style=" width: 50px; height: 50px; margin-left: 10px;" alt=""/>

                            </a>
                            <% if (request.getSession().getAttribute("panier") != null && ((HashMap<Produit, Integer>) request.getSession().getAttribute("panier")).size() > 0) {
                            %>
                                <span style="font-size: 12px;position: relative;bottom: 10px;left: 51px; background-color: red; border-radius: 50%; color: white;display: inline-block;text-align: center; min-width:15px;min-height:10px">
                                    <%=((HashMap<Produit, Integer>) request.getSession().getAttribute("panier")).size()%></span>
                                    <%}%>

                        </div>

                    </div>
                </div>


            </section> 

            <!-- FIN du block header ou il ya toutes les icones-->

            <div id = "lgneSeparanteHaut"> 

            </div>

            <!-- DEBUT du block header ou il y a les sections de redirections -->

            <section class = "deuxiemeBlockHeader"> 

                <ul >

                    <li>
                        <p style=" ">

                            <a href="actionController?controller=voirProduit&voirBy=all">Tout Nos Produits</a>
                        </p>

                    </li> 

                    <li class="menuDeroulantMarques" style="  ">
                        <p> <a   href="#"  >  Les Marques </a> </p>

                        <ul class="sousMenuDeroulant" style=" padding-left: 0px; padding-right: 0px;  ">
                            <li> <a href="actionController?controller=voirProduit&voirBy=marque&id_marque=2" > Samsung  </a>   </li>
                            <li style=" padding-bottom: 10px; padding-top: 10px; width: auto; "> <a href="actionController?controller=voirProduit&voirBy=marque&id_marque=1" > Apple  </a>   </li>
                            <li style=" border-bottom: none; " > <a href="actionController?controller=voirProduit&voirBy=marque&id_marque=3" > Google </a>   </li>

                        </ul>



                    </li>


                    <li>
                        <p>
                            <a href="actionController?controller=nouveaute">Nouveautes</a>
                        </p>
                    </li>

                    <li style=" border-right: none;">
                        <p>
                            <a href="actionController?controller=contact">Contact</a>
                        </p>
                    </li>

                </ul>


            </section> 

            <!-- FIN du block header ou il y a les sections de redirections -->


        </header>

        <div id = "lgneSeparanteBas">

        </div>


        <script src = "jsBootstrap/jquery-3.4.1.min"></script>
        <script src = "jsBootstrap/bootstrap.min"></script>

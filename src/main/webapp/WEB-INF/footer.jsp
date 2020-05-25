   <%@page import="actions.CompteAction"%>
<%@page import="managers.SessionManager"%>
<div class="footerContainer"> </div>

        <div id = "lgneSeparanteHaut">  </div>


        <footer>

            <!-- Footer principal -->
            <div class="footerMain">



                <div class="footerLogo">

                    <a href="actionController">

                        <img src="images/images des produits de presentation sur le site/icone footer et header/logo img officiel.png"  style=" width: 190px; height: 100px;" alt=""> 

                    </a>

                </div>



                <div class="footerItem">
                    <h2 class="footerTitre">Utilisateurs</h2>
                    <% if(!CompteAction.isConnected(request)){%>
                        <ul>
                        <li><a href="actionController?controller=connexion">Connexion</a></li>
                        <li><a href="actionController?controller=creationCompte">Mon Compte</a></li><!--il me reste ca----------------------->
                        <li><a href="actionController?controller=panier&action=show">Mon Panier</a></li>
                    </ul>
                        <%}
                    else{%>
                        <ul>
                        <li><a href="actionController?controller=deconnexion">Deconnexion</a></li>
                        <li><a href="actionController?controller=configurationCompte">Mon Compte</a></li><!--il me reste ca----------------------->
                        <li><a href="actionController?controller=panier&action=show">Mon Panier</a></li>
                    </ul>
                        <%}%>
                        
                </div>

                
                <div class="footerItem">
                    <h2 class="footerTitre">A Propos</h2>
                    <ul>
                        
                        <li><a href="actionController?controller=contact">Nous Contacter</a></li>
                        <li><a href="actionController?controller=livraison">Livraison</a></li>
                        <li><a href="actionController?controller=livraison"> Conditions Génerales de Vente  </a></li>
                    </ul>
                </div>


                <div class="footerPaiement" >

                    <a href="actionController"> 
                        <img src="images/images des produits de presentation sur le site/paiement/paiement-securite.png" style=" width: 350px; height: 110px;  background:rgba(121, 94, 0, 0.35); " alt=""/>
                        
                    </a>
                </div>



            </div>


            <!-- Footer reseaux -->
            <div class="footerReseaux">
                <ul class="footerListeResaux" style="border-top: 1px darkgoldenrod solid; ">
                    <li> <a href="https://www.facebook.com/" target="_blank"> <img src="images/images des produits de presentation sur le site/icone footer et header/logoFacebook2.png" style=" width: 50px; height: 50px;" alt=""/>  </a>  </li>
                    <li> <a href="https://www.instagram.com/?hl=fr-ca" target="_blank" > <img src="images/images des produits de presentation sur le site/icone footer et header/logoInstagram.png" style=" width: 50px; height: 50px;" alt=""/> </a>  </li>
                    <li> <a href="https://twitter.com" target="_blank" > <img src="images/images des produits de presentation sur le site/icone footer et header/logoTwitter.png" style=" width: 50px; height: 50px;" alt=""/>   </a>  </li>
                    <li> <a href="https://www.linkedin.com/in/kamel-elfilali/" target="_blank" > <img src="images/images des produits de presentation sur le site/icone footer et header/logolinkedin.png" style=" width: 50px; height: 50px;" alt=""/>  </a>  </li>
                    <li> <a href="https://accounts.snapchat.com/accounts/snapcodes" target="_blank" > <img src="images/images des produits de presentation sur le site/icone footer et header/logoSnap.png" style=" width: 50px; height: 50px;" alt=""/> </a>  </li>
                    <li> <a href="https://web.whatsapp.com/" target="_blank" > <img src="images/images des produits de presentation sur le site/icone footer et header/logoWhatssApp.png" style=" width: 50px; height: 50px;" alt=""/>  </a>  </li>
                </ul>
            </div>

            <!-- Footer Condition -->
            <div class="footerCondition">
                <ul class="footerListeCondition">
                    <li><a href="#">Terms &amp; Conditions</a></li>
                    <li>Copyright© 2020-2021 N-TeK Inc. Tous droits réservés.</li>
                    <!--<li>&copy;Copyright 2020-2021 N-TeK Inc. Tous droits réservés.</li>-->
                </ul>
            </div>


        </footer>
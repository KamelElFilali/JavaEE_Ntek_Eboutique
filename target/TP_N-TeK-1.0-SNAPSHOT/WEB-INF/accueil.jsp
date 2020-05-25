<%-- 
    Document   : accueil
    Created on : 17 janv. 2020, 16:26:00
    Author     : narib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueille</title>

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



    </head>

    <body id="bodyAccueille">

        <%@include file="header.jsp" %>

<!--        <div class="divVideo"  >
            <center> 
                <video controls claas="videoFond" preload="" autoplay loop="" poster = "images/images des produits de presentation sur le site/video/AirPodsPro.mp4" >

                    <source src="images/images des produits de presentation sur le site/video/AirPodsPro.mp4"  type="video/mp4">
                </video>

            </center> 
        </div>-->


        <!--************************************ IMAGES PRESENTATIONS ********************************* -->
        <section class="imgPresentation">

            <img src="images/images des produits de presentation sur le site/presentationAccueil.png" style=" width: 100%;" alt=""/>

        </section>

        <!--************************************ CHOIX DES DIFFERENTES MARQUES ********************************* -->


        <section class="sectionChoixMarque">


            <div id="divMarqueSamsung" style=" ">

                <div id="divImgLogoSamsung">
                    <a href="actionController?controller=voirProduit&voirBy=marque&id_marque=2">
                        <img class="imgLogo"  style=" width: 110px; height: 30px; "   src="images/images des produits de presentation sur le site/samsung/logoSamsung.png" style=" width: 100px; height: 50px;" alt=""/>
                    </a>

                </div>

                <div class="divTextRedirectionSamsung" style="  ">

                    <a href="actionController?controller=voirProduit&voirBy=marque&id_marque=2">
                        <p>
                            Cliquez pour voir les Produits <br />  de la marque Samsung > 
                        </p>
                    </a>
                </div>


            </div>



            <div id="divMarqueApple" style=" ">

                <div id="divImgLogoApple">

                    <a href="actionController?controller=voirProduit&voirBy=marque&id_marque=1">
                        <img class="imgLogo" style=" width: 110px; height: 80px; " src="images/images des produits de presentation sur le site/apple/logoApple.png" style=" width: 100px; height: 50px;" alt=""/>
                    </a>
                </div>
   

                <div id="divTextRedirectionApple" style="  ">

                    <a href="actionController?controller=voirProduit&voirBy=marque&id_marque=1">
                        <p>
                            Cliquez pour voir les Produits <br /> de la marque Apple > 
                        </p>
                    </a>

                </div>

            </div>



            <div id="divMarqueGoogle" style=" ">

                <div id="divImgLogoGoogle">

                    <a href="actionController?controller=voirProduit&voirBy=marque&id_marque=3">
                        <img class="imgLogo" style=" width: 110px; height: 33px; "  src="images/images des produits de presentation sur le site/google/logoGoogle.png" style=" width: 100px; height: 50px;" alt=""/>              
                    </a>

                </div>

                <div id="divTextRedirectionGoogle" style="  ">

                    <a href="actionController?controller=voirProduit&voirBy=marque&id_marque=3">
                        <p>
                            Cliquez pour voir les Produits <br /> de la marque Google > 
                        </p>
                    </a>

                </div>


            </div>


        </section>

        <!--************************************ PRODUIT RECEMENT VISITEE ********************************* -->

        <div id = "lgneSeparation">  </div>

        <div id = "divTextProduitRecementVisite">  

            <p style="  margin-left: auto; margin-right: auto;   ">
                Produits que Vous avez
                récemment visité

            </p>

        </div>



        <section class="sectionProduitRecent" >


            <p id="imgPrecedent" style=" width: 5px; margin-left: auto; margin-right: auto;   ">
                <a href=".jsp" style="   "> < </a>
            </p>


            <div id="divProduit1" style=" ">

                <div id="divImgProduit1">
                    <a href=".jsp">
                        <img class="imgProduit" style=" width: 125px; height: 180px; " src="images/produits/Apple/3_1_iphone 11 Pro.png" alt=""/>
                    </a>

                </div>

                <div class="divTextRedirectionProduit1" style="  ">

                    <a href=".jsp">
                        <p>
                            Apple - iPhone 11 Pro Max - 64 Go <br /> 
                            Vert Nocturne <br /> 
                            1519 $

                        </p>
                    </a>
                </div>


            </div>



            <div id="divProduit2" style=" ">

                <div id="divImgProduit2">

                    <a href=".jsp">
                        <img class="imgProduit" style=" width: 170px; height: 180px; " src="images/produits/Samsung/2_1_galaxy s10Plus.png" alt=""/>
                    </a>
                </div>


                <div id="divTextRedirectionProduit2" style="  ">

                    <a href=".jsp">
                        <p>
                            Samsung - Galaxy S10 Plus - 128 Go <br /> 
                            Bleu Ocean <br /> 
                            1259 $
                        </p>
                    </a>

                </div>

            </div>



            <div id="divProduit3" style=" ">

                <div id="divImgProduit3">

                    <a href=".jsp">
                        <img class="imgProduit" style=" width: 239px; height: 208px;  " src="images/produits/Google/5_1_google pixel 4.png" alt=""/>
                    </a>

                </div>

                <div id="divTextRedirectionProduit3" style="  ">

                    <a href=".jsp">
                        <p>
                            Google - Pixel 4 XL - 64 Go <br />
                            Juste Noir <br />
                            1129 $
                        </p>
                    </a>

                </div>


            </div>

            <div id="divProduit4" style=" ">

                <div id="divImgProduit4">

                    <a href=".jsp">

                        <img class="imgProduit" style=" width: 220px; height: 190px; " src="images/produits/Apple/MBP_15_SG_TB_2018-1.png" alt=""/>
                    </a>

                </div>

                <div id="divTextRedirectionProduit4" style="  ">

                    <a href=".jsp">
                        <p>
                            Apple - MacBook Pro - 16 po <br />
                            Gris Cosmique <br />
                            3499 $

                        </p>
                    </a>

                </div>


            </div>

            <p id="imgSuivante" style=" width: 5px; margin-left: auto; margin-right: auto;   ">
                <a href=".jsp" style="   "> > </a>
            </p>

        </section>

        <%@include file="footer.jsp" %>
    </body>

</html>

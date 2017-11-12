<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- lien vers création de compte --%>



<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>


<%-- body --%>

<div class="centralSection col-lg-offset-2 col-lg-8 col-lg-offset-2 ">
    <div class="titleConnexion">connexion</div>


    <div class="connexionParagraph col-lg-6 " >
        <form action="ControllerMain?connect" method="post" >
            <h4>Vous avez déjà un compte ?</h4>
            <br>
            <br>
            <div class="connexionInfo">
                <label for="adresseMail">Adresse mail :</label> <input type="email" name="adresseMail" id="adresseMail" /><br>
                <br>
                <label for="motDePasse">Mot de passe :</label> <input type="password" name="motDePasse" id="motDePasse" /><br>
                <br>  
            </div>    
                <div class="buttonConnexion"><a><input type="submit" value="Connexion" name="connexion" ></a></div>
            
        </form>
    </div>



    <div  class="col-lg-6 connexionParagraph">

        <h4>Vous êtes un nouveau client ? </h4>
        <br>
        <br>
         <div class="connexionInfo">
        <p class="connexionText">
            En créant un compte sur notre boutique, vous pourrez passer vos commandes plus rapidement, enregistrer plusieurs adresses de livraison,
            consulter et suivre vos commandes,et pleins d'autres choses encore.
        </p>
         </div>
       
        <c:url value="ControllerMain?section=createAccount" var="url300" /> 
        <div class="buttonConnexion"><button><a href="${url300}">Créer un compte</a></button></div>

    </div>
</div>


<%-- footer --%>
<%@include file="includes/footer.jsp" %>

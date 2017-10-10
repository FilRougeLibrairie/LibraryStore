<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- lien vers création de compte --%>
<c:url value="ControllerMain?section=createAccount" var="url103" />

<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>





<%-- body --%>

<div class="monCompte">
        <h1>connexion</h1>

        <div class="row">
        <div class="bisous col-lg-6 " >
        <form action="action">
            <h3>Vous avez déjà un compte ?</h3>
            <br>
            <br>
            <label for="adresseMail">Adresse mail :</label> <input type="text" name="adresseMail" id="adresseMail" /><br>
            <br>
            <label for="motDePasse">Mot de passe :</label> <input type="text" name="motDePasse" id="motDePasse" /><br>
            <br>
            <input type="submit" value="Connexion" name="connect"/><br>
        </form>
        </div>
        
        
        
        <div bisous class="col-lg-6 ">
        <form action="action">
            <h3>Vous êtes un nouveau client ? </h3>
            <br>
            <br>
            <p>
                En créant un compte sur notre boutique, vous pourrez passer vos commandes plus rapidement, enregistrer plusieurs adresses de livraison,
                consulter et suivre vos commandes,et pleins d'autres choses encore.
            </p>
            <br>
            
            <a href="${url103}"><input type="button" value="Créer un compte" name="createAccount" >   </input></a><br>
        </form>
        </div>
        </div>
</div>
        
<%-- footer --%>
<%@include file="includes/footer.jsp" %>

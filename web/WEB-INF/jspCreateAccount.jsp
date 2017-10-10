<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>





<%-- body --%>

<div class="monCompte">
    <h1>Créer un compte</h1>
    <div class="row">
        <div class="bisous col-lg-6 " >
            <label for="adresseMail">Adresse mail* :</label> <input type="text" name="adresseMail" id="adresseMail" placeholder="exemple@exemple.com" /><br><br>
            <label for="Civilité">Civilité :</label> Mme <input type="radio" name="Civilité" value="" id="Civilité" />
            Mr <input type="radio" name="Civilité" value="" /><br><br>
            <label for="lastName">Nom* :</label> <input type="text" name="lastName" id="lastName" /> <br><br>
            <label for="firstName">Prénom* :</label> <input type="text" name="firstName" id="firstName" /><br><br>
            <label for="organisationName">Organisation :</label> <input type="text" name="organisationName" id="organisationName" placeholder="(facultatif)"/><br><br>
            <label for="phoneNumber">Téléphone :</label> <input type="text" name="phoneNumber" maxlength="10" id="phoneNumber" placeholder="(facultatif)" /><br><br>

            <p>Afin de bénéficier d'offres personalisées, merci de bien vouloir nous indiquer votre date de naissance :</p>

            <label for="dateOfBirth">Date de naissance :</label> <input type="text" name="dateN" size="10"  maxlength="10" value="dd/mm/aaaa" placeholder="dd/mm/aaa" id="dateOfBirth"/>

        </div>
        <div class="bisous col-lg-6 " >
            <label for="password">Mot de passe* :</label> <input type="text" name="password" id="password"/><br><br>
            <label for="confirmPassword">Confirmer le mot de passe* :</label> <input type="text" name="confirmPassword" id="confirmPassword" /><br><br>
            je souhaite recevoir par email : <input type="checkbox" name="newsletter" value="off" /> La newsletter de Caroline<br>
            <input type="checkbox" name="commerciarlPartners" value="off" /> Les offres des partenaires de Caroline<br><br>
            <p>Les informations recueillies sont destinées aux sociétés du groupe Caroline(Gavanese SAS, Bhian SAS, Arrais SAS, Kouamen SAS et Cuaz SAS)
                et à leurs prestataires situés dans et hors de l'union européenne, afin de permetrre aux Membres de bénéficier d'informations sur Caroline. 
                Conformément à la loi "informatiques et libertés" n°78-17 du 6 janvier 1978 modifiée, chaque Membre bénéficie d'un droit d'accès, de rectification et
                d'opposition aux informations qui le concernent auprès de : Service Clients Caroline - 1 tombe du soldat inconnu - 75008 PARIS ou à l'adresse mail :
                caroline@serviceClientCaroline.com
            </p><br><br>
            <p> * Champs obligatoires</p>
            <input type="submit" value="Valider" name="createAccount"/><br>
        </div>
    </div>
</div>

<%-- footer --%>
<%@include file="includes/footer.jsp" %>





<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>



<%-- body --%>

<div class="monCompte">
    <h1>Créer un compte</h1>
    <form action="ControllerMain?section=validerCreateCus" method="post">
        <div class="row">
            <div class="bisous col-lg-6 " >

                <c:if test="${errorCreate != null}">LA CREATION A ECHOUEE</c:if>
                    <label for="adresseMail">Adresse mail* :</label> 
                    <input type="email" name="adresseMail" id="adresseMail" placeholder="exemple@exemple.com" /><br><br>



                    <label for="Civilité">Civilité :</label> 
                    <select class="selectSearch" name="searchGender" > 


                        <option value="Mme">Mr</option>
                        <option value="Mr"> Mme</option>

                    </select>
                    
                    <br><br>
                    
                    <label for="lastName">Nom* :</label> <input type="text" name="lastName" id="lastName"  /> <br><br>
                    <label for="firstName">Prénom* :</label> <input type="text" name="firstName" id="firstName"  /><br><br>
                    <label for="organisationName">Société :</label> <input type="text" name="organisationName" id="organisationName" placeholder="(facultatif)" /><br><br>
                    <label for="phoneNumber">Téléphone :</label> <input type="tel" name="phoneNumber" maxlength="10" id="phoneNumber" placeholder="(facultatif)"/><br><br>

                    <p>Afin de bénéficier d'offres personalisées, merci de bien vouloir nous indiquer votre date de naissance :</p>

                    <label for="dateOfBirth">Date de naissance :</label> <input type="text" name="dateN" size="10"  maxlength="10" placeholder="dd/mm/aaa" id="dateOfBirth" />

                </div>
                <div class="bisous col-lg-6 " >
                    <label for="password">Mot de passe* :</label> <input type="password" name="password" id="password" /><br><br>
                    <label for="confirmPassword">Confirmer le mot de passe* :</label> <input type="password" name="confirmPassword" id="confirmPassword" /><br><br>
                    je souhaite recevoir par email : <input type="checkbox" name="newsletter" value="off" /> La newsletter de Caroline<br>
                    <input type="checkbox" name="commerciarlPartners" value="off" /> Les offres des partenaires de Caroline<br><br>
                    <p>Les informations recueillies sont destinées aux sociétés du groupe Caroline(Gavanese SAS, Bihan SAS, Arrais SAS, Kouamen SAS et Cuaz SAS)
                        et à leurs prestataires situés dans et hors de l'union européenne, afin de permetrre aux Membres de bénéficier d'informations sur Caroline. 
                        Conformément à la loi "informatiques et libertés" n°78-17 du 6 janvier 1978 modifiée, chaque Membre bénéficie d'un droit d'accès, de rectification et
                        d'opposition aux informations qui le concernent auprès de : Service Clients Caroline - 1 tombe du soldat inconnu - 75008 PARIS ou à l'adresse mail :
                        caroline@serviceClientCaroline.com
                    </p><br><br>
                    <p> * Champs obligatoires</p>
                    <input type="hidden" name="ip" value="${pageContext.request.localAddr}">
                <div><input type="submit" value="Créer" name="validerCreateCus" ></div>

                <c:url value="ControlleurMain?section=monCompte" var="url320"/>
                <div> <a href="${url320}">Retour</a> </div>
            </div>
        </div>
    </form>
</div>

<%-- footer --%>
<%@include file="includes/footer.jsp" %>





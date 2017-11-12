<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- lien vers création de compte --%>

<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>


<%-- body --%>
<div class="monCompte">
    <h1>Mon profil</h1>
    
        <div class="bisous col-lg-12 " >
            <div>Mes données personnels :</div>
            <c:if test="${errorCreate != null}">LA MODIFICATION A ECHOUEE</c:if>
            <form action="ControllerMain?section=updateCustomer" method="post">
            <div class="row"><div class="clientInfo"><label for="adresseMail">Adresse mail :</label></div><input type="email" name="adresseMail" value="${c.getCusEmail()}"id="adresseMail"  /></div>
            <div class="row"><div class="clientInfo"><label for="Civilité">Civilité :</label> Mme <input type="radio" name="Civilité" value="${c.getCusGender()}" id="CivilitéMme" />
                    Mr <input type="radio" name="Civilité" value="${c.getCusGender()}"    id="CivilitéMr" /></div></div>
            <div class="row"> <div class="clientInfo"><label for="lastName">Nom :</label></div> <input type="text" name="lastName" value="${c.getCusLastName()}" id="lastName" /></div>
            <div class="row"><div class="clientInfo"><label for="firstName">Prénom :</label> </div><input type="text" name="firstName" value="${c.getCusFirstName()}" id="firstName" /></div>
            <div class="row"><div class="clientInfo"><label for="organisationName">Société :</label> </div><input type="text" name="organisationName" value="${c.getCusOrganisationName()}" id="organisationName" /></div>
            <div class="row"><div class="clientInfo"><label for="dateOfBirth">Date de naissance :</label> </div><input type="text" name="dateN" size="10"  maxlength="10" value="${c.getCusDateOfBirth()}" id="dateOfBirth"/></div>
            <div class="row"><div class="clientInfo"><label for="phoneNumber">Téléphone :</label> </div><input type="tel" name="phoneNumber" maxlength="10" value="${c.getCusPhoneNumber()}" id="phoneNumber"  /></div><br>
            <div class="row"><div class="clientInfo"><label for="password">Mot de Passe :</label> </div><input type="password" name="password"  value="" id="password"  /></div><br>
            
            <c:url value="ControllerMain?section=return" var="url307" />
            <div><a href="${url307}">Retour</a></div><br>
            <br>
            <c:url value="ControllerMain?section=updateCustomer" var="url310" />
            <div><a href="${url310}"><input type="submit" value="Modifier" name="modifier" ></a></div>
            </form>
        </div>
    

    <%-- footer --%>
    <%@include file="includes/footer.jsp" %>

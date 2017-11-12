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
    <div class="row">



        <div class="bisous">
            Mes adresses <br>
            <select name="myAddress">
                <c:forEach items="${listLabel}"  var="p">
                    <option value="${p.getAddId()}" selected="${p.getAddId()}">${p.getAddLabel()}</option>
                </c:forEach>
            </select>

            <br>
            <c:url value="ControllerMain?section=detailAdresse" var="url317" />
            <div><a href="${url317}"><input type="submit" value="Gérer" name="detailAdresse" ></a></div>
        </div>
        <form action="ControllerMain?section=createAddress" method="post">
            <div class="bisous col-lg-4 " >

                <br>

                <div class="formulaire"><label for="addLabel">Nom de l'adresse :</label> <input type="text" name="addLabel" id="addLabel" value="${p.getAddLabel()}" /></div><br>
                <div class="formulaire"><label for="addLastName">Nom :</label> <input type="text" name="addLastName" id="addLastName" value="${p.getAddFirstName()}"  /></div><br>
                <div class="formulaire"><label for="addFirstName">Prénom :</label> <input type="text" name="addFirstName" id="addFirstName" value="${p.getAddLastName()}"  /></div><br>

                <div class="formulaire"><label for="addCompany">Société :</label> <input type="text" name="addCompany" id="addCompany" value="${p.getAddCompany()}"  /></div><br>
            </div>
            <div class="bisous col-lg-4 " >
                <div class="formulaire"><label for="addNumber">Numéro :</label> <input type="text" name="addNumber" id="addNumber" value="${p.getAddNumber()}"  /></div><br>
                <div class="formulaire"><label for="addStreetType">Type de voie :</label> <input type="text" name="addStreetType" id="addStreetType" placeholder="rue, avenue..." value="${p.getAddStreetType()}"   /></div><br>
                <div class="formulaire"><label for="addStreetName">Libellé de la voie :</label> <input type="text" name="addStreetName" id="addStreetName" value="${p.getAddStreetName()}" /></div><br>

                <div class="formulaire"><label for="addComplement">Complement :</label> <input type="text" name="addComplement" id="addComplement" value="${p.getAddComplement()}"  /></div><br>
            </div>
            <div class="bisous col-lg-4 " >
                <div class="formulaire"><label for="addZipCode">Code postal :</label> <input type="text" name="addZipCode" id="addZipCode" value="${p.getAddZipCode()}"  /></div><br>
                <div class="formulaire"><label for="addCity">Ville : </label> <input type="text" name="addCity" id="addCity" value="${p.getAddCity()}"  /></div><br>
                <div class="formulaire"><label for="addSecurityCode">Digicode :</label> <input type="text" name="addSecurityCode" id="addSecurityCode" value="${p.getAddSecurityCode()}"  /></div><br>
                <div class="formulaire"><label for="addPhone">Téléphone :</label> <input type="text" name="addPhone" id="addPhone" value="${p.getAddPhone()}"  /></div><br>
            </div>
            <c:url value="ControllerMain?section=return" var="url308" />
            <div class="formulaire"><a href="${url308}">Retour</a></div><br>
            <div class="col-lg-6"><input type="submit" value="Créer" name="createAddress" /></div>
        </form>
    </div>








</div>
<%-- footer --%>
<%@include file="includes/footer.jsp" %>

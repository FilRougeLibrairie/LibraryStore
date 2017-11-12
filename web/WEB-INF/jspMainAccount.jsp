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
        <div class="bisous col-lg-6 " >
            mes données personnels<br><br>
            
            Adresse mail : <div>${c.getCusEmail()}</div><br>
            Nom Prenom :<div>  ${c.getCusFirstName()} ${c.getCusLastName()}</div> <br>
            Date de naissance :<div>${c.getCusDateOfBirth()}</div> <br>
            
            <c:url value="ControllerMain?section=updateAccountCustomer" var="url304" />
            <div class="formulaire"><a href="${url304}">Gérer mes infos personnelles</a></div><br>
        </div>
        <div class="bisous col-lg-6  " >
            Mes adresses <br><br>
             <c:forEach items="${listLabel}" var="p">
            adresse : <br><br>
            <div> ${p.getAddLabel()}</div><br>
            <div> ${p.getAddNumber()} ${p.getAddStreetType()} ${p.getAddStreetName()}</div><br>
            <div> ${p.getAddZipCode()} ${p.getAddCity()}</div><br><br>
            </c:forEach>   
            <c:url value="ControllerMain?section=updateAddressCustomer" var="url305" />
            <div class="formulaire"><a href="${url305}&cusId=${c.getCusID()}">Gérer mes adresses</a></div><br>
            
        </div>
        </div>
        <div class="row">
        <div class="bisous col-lg-12  " >
            mes commandes :
            <c:url value="ControllerMain?section=orderCustomer" var="url306" />
            <div class="formulaire"><a href="${url306}">Mes Commandes</a></div><br>
            
        </div>
        </div>
        <div class="row">
        <div class="bisous col-lg-12  " >
            <c:url value="ControllerMain?section=deconnexion" var="url312" />
            <div class="formulaire"><a href="${url312}">Deconnexion</a></div><br>
            
        </div>
        </div>
        
        
</div>
    <%-- footer --%>
<%@include file="includes/footer.jsp" %>
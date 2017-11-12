
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
        <div class="bisous col-lg-12 " >
            Mes commandes : <br><br>

            <div class="row"><div class="clientInfo"><label for="adresseMail">Adresse mail :</label></div><input type="email" name="adresseMail" value="${c.getCusEmail()}"id="adresseMail"  /></div>

            <c:url value="ControllerMain?section=return" var="url309" />
            <div class="formulaire"><a href="${url309}">Retour</a></div><br>

        </div>

        <%-- footer --%>
        <%@include file="includes/footer.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!--  -->

<c:url value="ControllerMain?section=home" var="url100" />
<c:url value="ControllerMain?section=monCompte" var="url102" />
<c:url value="ControllerMain?section=listCatalog" var="url103" />
<c:url value="ControllerMain?section=book" var="url104" />
<c:url value="ControllerMain?section=offer" var="url119" />
<c:url value="ControllerMain?section=author" var="url121" />
<c:url value="ControllerMain?section=displaycart" var="urldisplaycart" />


<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span> 
            </button>

        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${url100}">Home</a></li>
                <li><a href="${url103}">Catalogue</a>
                <li>   




                <li><a href="${url104}">Livre</a></li>

                <li><a href="${url119}" >Evenement</a>
                </li>
                <li><a href="${url121}">Auteur</a></li>



                <ul class="nav navbar-nav navbar-right navbar-test">
                    <li><a href="${urldisplaycart}"><span class="badge">

                                <%--DEBUT GUILLAUME--%><c:choose>
                                    <c:when test="${totalItemsCount > 0}">${totalItemsCount}</c:when>
                                    <c:otherwise>0</c:otherwise>
                                </c:choose><%--FIN GUILLAUME--%></span><span class="glyphicon glyphicon-shopping-cart shopping-cart"></span> Mon Panier</a></li>

                    <li><a href="${url102}"><span class="glyphicon glyphicon-user"></span> Mon Compte</a> </li>
                   

                    <li class="user">    <c:if test="${c != null}">(${c.cusFirstName} ${c.cusLastName})</c:if></li>

                     <li> 
                     <c:if test="${c != null}"><c:url value="ControllerMain?section=deconnexion" var="url312" />
                        <a href="${url312}">Deconnexion</a> </c:if>

                    </li>
                    
                </ul>
        </div>
    </div>
</nav>
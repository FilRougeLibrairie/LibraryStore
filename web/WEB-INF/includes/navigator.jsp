<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!--  -->

<c:url value="ControllerMain?section=home" var="url100" />
<c:url value="ControllerMain?section=monCompte" var="url102" />
<c:url value="ControllerMain?section=listCatalog" var="url103" />
<c:url value="ControllerMain?section=book" var="url104" />

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


                <!--CATALOGUE -->

                <li>

                    <ul id="menu-accordeon">
                        <li><a href="${url103}">Catalogue</a>
                            <ul class="test">
                                <c:forEach items="${keys}" var="c">
                                    <li > <a href="#"  >   
                                            ${c}
                                        </a></li>  
                                    </c:forEach>    
                            </ul>
                        </li>
                    </ul>
                <li>
                    <!--LIVRE -->




                <li><a href="${url104}">Livre</a></li>

                <li><a href="#" >Evenement</a>
                </li>
                <li><a href="#">Auteur</a></li>
                <li><a href="#">Nouveauté</a></li>


                <ul class="nav navbar-nav navbar-right navbar-test">
                    <li><a href="${url102}"><span class="glyphicon glyphicon-user"></span> Mon Compte</a></li>

                </ul>
        </div>
    </div>
</nav>
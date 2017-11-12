<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="ControllerMain?section=offerbook" var="url130" />


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>





<%-- body --%>


<%@include file="includes/menuLeftEvent.jsp" %>

<div class="centralSection col-lg-10 col-md-10 col-sm-10 col-xs-10 ">
    <div class="row">

        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
               
            </ol>

            <div class="carousel-inner" role="listbox">
                <div class="item active">  
                    <img class="img-responsive" src="image/event/offer.png"/>
                </div>
                <c:forEach items="${listeOffer}" var="p">   
                    
                    
                    
                    
                    
                    <div class="item">
                        <a href="${url130}&offid=${p.getOffId()}">
                        <img class="img-responsive" src="${p.getOffPicture()}"/>
                        </a>
                        <div class="carousel-caption"> </div>
                    </div>
                        
                        
                        
                        
                </c:forEach>        
            </div>
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Précédent</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Suivant</span>
            </a>
        </div>
    </div>
</div>





<%-- footer --%>
<%@include file="includes/footer.jsp" %>
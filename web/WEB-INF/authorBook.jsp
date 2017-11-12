<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>


<c:url value="ControllerMain?section=bookGeneral" var="url125" />


<%-- body --%>

<%@include file="includes/menuLeftAuthor.jsp" %>

<div class="centralSection col-lg-10 col-md-10 col-sm-10 col-xs-10 ">

    <div class="col-lg-12">

        <c:forEach items="${listeBook}" var="p">
            <div class=" cover col-lg-4">
                <a class="blocCatalog" href="${url125}&booId=${p.getBookIsbn()}"><img src="${p.getImageURL()}" alt="cover"/></a>
                <div class="elementCatalog" style="font-weight: bold;"> ${p.getBookTitle()}</div>
                <div class="elementCatalog"> ${p.getAuthor()}</div>
                <div class="elementCatalog"> ${p.getPriceTTC()} EUR TTC</div>
                 <c:choose>

                    <c:when test="${p.getBooQuantity()<1}">

                <div class="boutonOrder"><span class="zocial cart indisponible">Indisponible</span></div>   

                    </c:when>

                <c:when test="${p.getBooQuantity()>=1}">

                <div class="boutonOrder"><a href="ControllerMain?item=${p.getBookIsbn()}&action=add&section=${currentSection}&authorId=${authorId}" class=" zocial cart">Commander</a></div>   

                    </c:when>
                </c:choose>
            </div>     
        </c:forEach>
    </div>
</div>


    
        
<%-- footer --%>
<%@include file="includes/footer.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>


<%-- body --%>
<h2>Tous les livres : ${itemsCount} résultats</h2>

<div>Pagination</div>

<div style="width:30%">
    <ul>
        <c:forEach var="item" items="${listItems}" varStatus="">
               <div class="alert alert-info">
                   <h3><a href="controllerMain?action=addToCart&id=${item.getBookIsbn()}&callBack=catalog">${item.getBookTitle()}</a></h3>
                   <img class="img-thumbnail img-responsive" height="300px" width="200px" src="${item.getImageURL()}"/>
                   <p>${item.getEditorName()} | 
                       <c:forEach var="author" items="${item.getListAuthors()}" varStatus="loop">
                          ${author}<c:if test="${item.getListAuthors().size() > 1}"><c:if test="${!loop.last}">, </c:if></c:if>
                       </c:forEach>
                   </p>
                   <h2><a href="controllerMain?action=addToCart&id=${item.getBookIsbn()}&callBack=catalog">Acheter</a></h2>
               </div>
           </c:forEach>
    </ul>
</div>



<%-- footer --%>
<%@include file="includes/footer.jsp" %>
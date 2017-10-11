<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>


<%-- body --%>
<h2>Hello Catalog</h2>

<div>
    <ul>
           <c:forEach var="item" items="${listItems}">
               <div>
                   <h3><a href="controllerMain?action=addToCart&id=${item.getBookIsbn()}&callBack=catalog">${item.getBookTitle()}</a></h3>
                   <img src="${item.getImageURL()}"/>
                   <p>${item.getEditorName()} | 
                       <c:forEach var="author" items="${item.getListAuthors()}">
                          ${author}
                          <c:if test="${item.getListAuthors().size() > 1}" var="a">
                              , 
                          </c:if>
                       </c:forEach>
                   </p>
               </div>
           </c:forEach>
    </ul>
</div>


<a href="controllerMain?action=addToCart&id=9781444799132&callBack=catalog">The whistler</a><br>
<a href="controllerMain?action=addToCart&id=9782226328717&callBack=catalog">Fin de ronde</a><br>
<a href="controllerMain?action=addToCart&id=9782226328717&callBack=catalog">Underground railroad </a><br>



<%-- footer --%>
<%@include file="includes/footer.jsp" %>
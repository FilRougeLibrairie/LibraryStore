<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>
<c:url value="ControllerMain?section=authorBook" var="url127" />
<c:url value="ControllerMain?section=authorInfo" var="url128" />



<%-- body --%>
<%@include file="includes/menuLeftAuthor.jsp" %>


<div class="centralSection col-lg-10 col-md-10 col-sm-10 col-xs-10 ">
    
    
    
        <c:forEach items="${listeAuthor}" var="p">
            
            <div class=" author col-lg-2 col-md-3 col-sm-4 col-xs-5">
                <div class="authorImg"></div>
                <div class="authorItemPrinc"><a href="${url128}&authorId=${p.getAuthorId()}">${p.getAuthor()}</a></div>
                <div class="authorItem"> <a href="${url127}&authorId=${p.getAuthorId()}">Ses ouvrages</a></div>
            </div>     
        </c:forEach>
   
</div>

<%-- <h1>${bookTitle}</h1>--%>
    
        
<%-- footer --%>
<%@include file="includes/footer.jsp" %>
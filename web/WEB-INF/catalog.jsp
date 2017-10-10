<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>





<%-- body --%>

<div class="row">
 
    <div class="leftSection col-lg-offset-1 col-lg-2 col-lg-offset-1 col-md-offset-2 col-md-2 col-md-offset-2 col-sm-offset-2 col-sm-2 col-sm-offset-2  col-xs-offset-2 col-xs-2 col-xs-offset-2">
        <ul>
            <li><a href="#">Prix </a>
            
            </li>
            <li><a href="#">Notes </a></li>
            <li><a href="#">Date de parution </a></li>
           
        </ul>
    </div>
    <div class="centralSection col-lg-8 col-md-8 col-sm-8 col-xs-8 "></div>
</div>








<%-- footer --%>
<%@include file="includes/footer.jsp" %>






<%--
<c:url value="ControllerMain?section=menu-main" var="url01" />
<c:import url="${url01}" />
<c:forEach items="${clefs}" var="c">
    <div>
        <h1>${c}</h1>
        <ul>
            <c:forEach items="${listeBook.get(c)}" var="p">
                <li>
                <c:url value="#" var="url350" />
                <a href="${url350}">${p}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</c:forEach>  --%>
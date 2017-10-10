<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>


<%-- body --%>
<h2>Hello Fake Catalog</h2>

<a href="controllerMain?action=addToCart&id=9781444799132&page=fake-catalog">The whistler</a><br>
<a href="controllerMain?action=addToCart&id=9782226328717&callBack=fake-catalog">Fin de ronde</a><br>
<a href="controllerMain?action=addToCart&id=9782226328717&callBack=fake-catalog">Underground railroad </a><br>



<%-- footer --%>
<%@include file="includes/footer.jsp" %>
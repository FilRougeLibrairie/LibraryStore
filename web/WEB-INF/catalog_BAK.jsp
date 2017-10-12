<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>





<%-- body --%>

<%--<c:url value="ControllerMain?section=carousel-event" var="url01" />
<c:import url="${url01}" /> --%>

<p>test</p>

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
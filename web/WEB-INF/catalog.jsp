<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>



<%-- body --%>

<div class="row">

    <div class="leftSection col-lg-2 col-md-2 col-sm-2 col-xs-2 ">
        <ul>
            <li><a href="${url02}">Tout le catalogue </a></li>
            <li><a href="#">Prix </a>  </li>
            <li><a href="#">Notes </a></li>
            <li><a href="#">Date de parution </a></li>

        </ul>
    </div>
    <div class="centralSection col-lg-8 col-md-8 col-sm-8 col-xs-8 ">



        <div class="row">
            <c:forEach items="${keys}" var="c">
                
                        <c:forEach items="${listeBook.get(c)}" var="p">
                         <div class="cover col-lg-4">
                             <img src="${p.getBooFrontCover()}" alt="cover"/>
                             <div> ${p.getBooTitle()}</div>
                             <div> ${p.getAuthorList()}</div>
                             <div> ${p.getAuthorList()}</div>
                             <div> ${p.getBooPriceHT()}</div>
                             <div><a href="#" class="zocial cart">Commander</a></div>
                             
                         </div>     
                        </c:forEach>
                    
            </c:forEach>
            <div >




            </div>
        </div>

        <%-- footer --%>
        <%@include file="includes/footer.jsp" %>


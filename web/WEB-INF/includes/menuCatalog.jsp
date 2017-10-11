<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="header.jsp" %>

<%-- navigator --%>
<%@include file="navigator.jsp" %>



<%-- body --%>



<c:url value="ControllerMain?section=pageCatalog" var="url02" />
    


<div class="row">

    <div class="leftSection col-lg-2 col-md-2 col-sm-2 col-xs-2 ">
        <ul>
            
            <li><a href="${url02}">Tout le catalogue </a></li>
            <li><a href="#">Prix </a></li>
            <li><a href="#">Notes </a></li>
            <li><a href="#">Date de parution </a></li>

        </ul>
    </div>
    <div class="centralSection col-lg-8 col-md-8 col-sm-8 col-xs-8 ">

        
      
    </div>
</div>

<%-- footer --%>
<%@include file="footer.jsp" %>














    
    
    
    
    
    
    
    
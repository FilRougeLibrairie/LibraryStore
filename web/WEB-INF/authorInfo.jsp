<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navigator.jsp" %>





<%@include file="includes/menuLeftAuthor.jsp" %>

<div class="centralSection col-lg-10 col-md-10 col-sm-10 col-xs-10 ">
    <c:url value="ControllerMain?section=authorOuvrage" var="url129" />
    
     <div class="bookBackground"> 

        <c:forEach items="${listeAuthor}" var="p">
            <div class="row">

                <div class="col-lg-3">
                    <div class="authorImg"></div>
                </div>
                <div class="bookInfo col-lg-9">


                    <div >
                        <div class="titleBook"><h3>${p.getAutLastName()} ${p.getAutFirstName()}</h3></div>
                        
                        <div><a class="authorInfo" href="${url129}&authorId=${p.getAutId()}">Ses ouvrages</a></div>
                        
                    </div>
                    
                    <div class="autobio col-lg-12">
                        <div>${p.getAutBiography()}</div>
                    </div>
                </div>
                
            </c:forEach>
        </div>
    
    
    
    
    
    
    
    
</div>


 
<%@include file="includes/footer.jsp" %>
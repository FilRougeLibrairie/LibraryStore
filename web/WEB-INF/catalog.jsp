<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>

<c:url value="ControllerMain?section=bookGeneral" var="url125" />



<%@include file="includes/menuLeft.jsp" %>


<div class="centralSection col-lg-10 col-md-10 col-sm-10 col-xs-10 ">

    <div class="col-lg-12">

        <c:forEach items="${listeBook}" var="p">
        
            <div class=" cover col-lg-4">



                <c:choose>

                    <c:when test="${p.getDiscount()==null}">
                        <div class="elementCatalog">   
                            <a class="blocCatalog" href="${url125}&booId=${p.getBookIsbn()}"> <img src="${p.getImageURL()}" alt="cover"/> </a>
                        </div>
                        <div class="elementCatalog" style="font-weight: bold;"> ${p.getBookTitle()}</div>
                        
                        
                        <div class="elementCatalog"> ${p.getListAuthors()}</div>
                        
                        
                        
                        <div class="elementCatalog"> ${p.getPriceTTC()} EUR TTC </div>
                    </c:when>


                    <c:when test="${p.getDiscount()==0}">
                        <div class="elementCatalog">   
                            <a class="blocCatalog" href="${url125}&booId=${p.getBookIsbn()}"> <img src="${p.getImageURL()}" alt="cover"/> </a>
                        </div>
                        <div class="elementCatalog" style="font-weight: bold;"> ${p.getBookTitle()}</div>
                        <div class="elementCatalog"> ${p.getListAuthors()}</div>
                        <div class="elementCatalog"> ${p.getPriceTTC()} EUR TTC</div>
                    </c:when>


                    <c:when test="${p.getDiscount()!=null}"> 
                        <div class="elementCatalog">   
                            <a class="blocCatalog" href="${url125}&booId=${p.getBookIsbn()}"> 
                                <img class="imgDiscount" src="image/specialOffer.png" alt="cover"> <img src="${p.getImageURL()}" alt="cover"/> 
                                </img>

                            </a>
                        </div>
                        <div class="elementCatalog" style="font-weight: bold;"> ${p.getBookTitle()}</div>
                        <div class="elementCatalog"> ${p.getListAuthors()}</div>
                        <div class=" elementCatalog row">
                            <div class="elementPriceOld col-lg-6">${p.getPriceTTC()} EUR TTC</div>
                            <div class="elementPrice col-lg-6"> ${p.getPriceTTCWithoutDiscount()} EUR TTC </div>
                        </div>
                    </c:when>

                </c:choose>


                         <c:choose>

                    <c:when test="${p.getBooQuantity()<1}">

                <div class="boutonOrder"><span class="zocial cart indisponible">Indisponible</span></div>   

                    </c:when>

                <c:when test="${p.getBooQuantity()>=1}">

                <div class="boutonOrder"><a href="ControllerMain?item=${p.bookIsbn}&action=add&section=${currentSection}&page=${page}" class=" zocial cart">Commander</a></div>   

                    </c:when>
                </c:choose>

            </div>     
        </c:forEach>
    </div>


  
    


        <div class="col-lg-12">
         <nav class="paginat"aria-label="Page navigation example">
                <ul class="pagination">
                 
              
                 
                 
                 <c:forEach items="${nbrePages}" var="nb">
                 <li class="page-item"><a class="page-link" href="ControllerMain?section=${currentSection}&page=${nb}">${nb}</a></li>
                 </c:forEach>
                 
                 
                 
                 
                 
                </ul>
            </nav>
     </div>

        </div>



    <%-- footer --%>
    <%@include file="includes/footer.jsp" %>
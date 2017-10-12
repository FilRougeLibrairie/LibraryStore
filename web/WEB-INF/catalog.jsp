<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>


<%-- body --%>
<h2>Tous les livres : ${itemsCount} résultats</h2>

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
            
            <c:url value="ControllerMain?section=catalog" var="url105" />
            
            
    <div class="centralSection col-lg-8 col-md-8 col-sm-8 col-xs-8 ">
        <h1>Panier : <c:if test="${cartitems != null}" var="nb" >${cartitems}</c:if><c:if test="${cartitems == null}" var="nb" >0</c:if></h1>
        <div class="col-lg-12">
            <c:forEach items="${keys}" var="c">
                <c:forEach items="${listeBook.get(c)}" var="p">
                    <div class="cover col-lg-4">
                        <img src="${p.getBooFrontCover()}" alt="cover"/>
                        <div> ${p.getBooTitle()}</div>
                        <div> ${p.getAuthorList()}</div>
                        <div> ${p.getAuthorList()}</div>
                        <div> ${p.getBooPriceHT()}</div>
                        <div><a href="ControllerMain?action=add&item=${p.getBooIsbn13()}&callback=pageCatalog" class="zocial cart">Commander</a></div>   
                    </div>     
                </c:forEach>
            </c:forEach>
         <div >


             <div class="col-lg-12">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Précédent</span>
                            </a>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Suivant</span>
                            </a>
                        </li>
                    </ul>
                </nav>
             </div>




            </div>
        </div>

        <%-- footer --%>
        <%@include file="includes/footer.jsp" %>


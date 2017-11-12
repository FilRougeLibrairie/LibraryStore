<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="ControllerMain?section=bookGeneral" var="url129" />


<div class="row">
    <div class="centralSection col-lg-offset-2 col-lg-8  col-md-offset-2 col-md-8 col-sm-offset-2 col-sm-8 col-xs-12">

        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <div class="carousel-inner" role="listbox">

                <div class="item active">
                    <img class="img-responsive" src="image/cclogo.png" >
                    <div class="carousel-caption">
                    </div>
                </div>


                <c:forEach items="${listeBook}" var="p"> 


                    <c:choose>

                        <c:when test="${p.getDiscount()==null}">
                            <div class="item">
                                <div class="price">${p.getPriceTTC() } EUR TTC </div>
                               <a href="${url129}&booId=${p.getBookIsbn()}"> <img class="img-responsive" src="${p.getImageURL()}"/></img> ></a>
                            </div>
                        </c:when>


                        <c:when test="${p.getDiscount()==0}">
                            <div class="item">
                                <div class="price">${p.getPriceTTC()}EUR TTC </div>
                                <a href="${url129}&booId=${p.getBookIsbn()}"><img class="img-responsive" src="${p.getImageURL()}"/> </a>
                            </div> 
                        </c:when>

                        <c:when test="${p.getDiscount()!=null}"> 
                            
                            <div class="item">
                                <div class="priceBookOldCarousel">${p.getPriceTTC()} EUR TTC</div>
                                <div class="price">${p.getPriceTTCWithoutDiscount()} EUR TTC  </div>
                                <img class="imgDiscount" src="image/specialOffer.png" alt="cover">
                                <a href="${url129}&booId=${p.getBookIsbn()}"><img class="img-responsive" src="${p.getImageURL()}"/> </img></a>
                            </div>
                        </c:when>

                    </c:choose>






                </c:forEach> 


            </div>


            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Précédent</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Suivant</span>
            </a>
        </div>
    </div>
</div>

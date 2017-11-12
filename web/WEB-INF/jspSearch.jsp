<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@include file="includes/header.jsp" %>


<%@include file="includes/navigator.jsp" %>

<c:url value="ControllerMain?section=authorBook" var="url127" />
<c:url value="ControllerMain?section=authorInfo" var="url128" />
<c:url value="ControllerMain?section=bookGeneral" var="url125" />



<c:choose>
    <c:when test="${notFound.equals(notFound)}">


        <div class="bookBackground notFound"> Votre recherche n'a pas aboutie. Aucun résultat a été trouvé. </div>
    </c:when>


    <c:when test="${listSearchAuthor.size()>0}">
        <%@include file="includes/menuLeftAuthor.jsp" %>
        <div class="centralSection col-lg-10 col-md-10 col-sm-10 col-xs-10 ">
            <c:forEach items="${listSearchAuthor}" var="p">

                <div class=" author col-lg-2 col-md-3 col-sm-4 col-xs-5">
                    <div class="authorImg"></div>
                    <div class="authorItemPrinc"><a href="${url128}&authorId=${p.getAuthorId()}">${p.getAuthor()}</a></div>
                    <div class="authorItem"> <a href="${url127}&authorId=${p.getAuthorId()}">Ses ouvrages</a></div>
                </div>     
            </c:forEach>
        </div>
    </c:when>


    <c:when test="${listSearchBook.size()>0}">
        <%@include file="includes/menuLeft.jsp" %>
        <div class="centralSection col-lg-10 col-md-10 col-sm-10 col-xs-10 ">
            <div class="col-lg-12">
                <c:forEach items="${listSearchBook}" var="p">
                    <div class=" cover col-lg-4">
                        <c:choose>
                            <c:when test="${p.getDiscount()==null}">
                                <div class="elementCatalog">   
                                    <a class="blocCatalog" href="${url125}&booId=${p.getBookIsbn()}&page=1"> <img src="${p.getImageURL()}" alt="cover"/> </a>
                                </div>
                                <div class="elementCatalog" style="font-weight: bold;"> ${p.getBookTitle()}</div>
                                <div class="elementCatalog"> ${p.getListAuthors()}</div>
                                <div class="elementCatalog"> ${p.getPriceTTC()} EUR TTC </div>
                            </c:when>
                            <c:when test="${p.getDiscount()==0}">
                                <div class="elementCatalog">   
                                    <a class="blocCatalog" href="${url125}&booId=${p.getBookIsbn()}&page=1"> <img src="${p.getImageURL()}" alt="cover"/> </a>
                                </div>
                                <div class="elementCatalog" style="font-weight: bold;"> ${p.getBookTitle()}</div>
                                <div class="elementCatalog"> ${p.getListAuthors()}</div>
                                <div class="elementCatalog"> ${p.getPriceTTC()} EUR TTC</div>
                            </c:when>
                            <c:when test="${p.getDiscount()!=null}"> 
                                <div class="elementCatalog">   
                                    <a class="blocCatalog" href="${url125}&booId=${p.getBookIsbn()}&page=1"> 
                                        <img class="imgDiscount" src="image/specialOffer.png" alt="cover"> <img src="${p.getImageURL()}" alt="cover"/> </img>
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

                                <div class="boutonOrder"><a href="ControllerMain?item=${p.getBookIsbn()}&action=add&section=${currentSection}&searchBarre=${searchBarre}&searchType=${searchType}" class=" zocial cart">Commander</a></div>   

                            </c:when>
                        </c:choose>






                    </div>     
                </c:forEach>
            </div>
        </c:when>
    </c:choose>


    <%@include file="includes/footer.jsp" %>
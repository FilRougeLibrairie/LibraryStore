<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- header--%>
<%@include file="includes/header.jsp" %>

<%-- navigator --%>
<%@include file="includes/navigator.jsp" %>



<%@include file="includes/menuLeft.jsp" %>

<div class="centralSection col-lg-10 col-md-10 col-sm-10 col-xs-10 ">
    <div class="bookBackground"> 
        <div>
            <c:forEach items="${book}" var="p">
                <div class="row">
                    <div class="col-lg-3">
                        <div><img src="${p.getImageURL()}"/></div>
                    </div>
                    <div class="bookInfo col-lg-9">
                        <div class="col-lg-6">
                            <div class="titleBook"><h3>${p.getBookTitle()}</h3></div>
                            <div>N° d'Isbn: ${p.getBookIsbn()}</div>
                            <div>Auteur(s):
                                <c:forEach items="${authors}" var="a">
                                    <c:choose>
                                        <c:when test="${((authors!=null)&&(authors.size()==1))}">        
                                            ${a.getAutLastName()} ${a.getAutFirstName()}
                                        </c:when>
                                        <c:when test="${(authors.size()>1)}">        
                                            ${a.getAutLastName()} ${a.getAutFirstName()} ,

                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </div>        



                            <div>Editeur: ${p.getEditorName()}</div>
                            <div>Format: ${p.getFormat()}</div>
                            <div>Langue: ${p.getBooLangName()}</div>
                            <c:choose>
                                <c:when test="${p.getReviewByBook() != null}">
                                    <c:choose>
                                        <c:when test="${(p.getReviewByBook() >= 0)&&(p.getReviewByBook()<1.0)}">
                                            Note:
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                        </c:when>

                                        <c:when test="${(p.getReviewByBook() >= 1.0)&&(p.getReviewByBook()<2.0)}">
                                            Note:
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                        </c:when>

                                        <c:when test="${(p.getReviewByBook() >=2.0)&&(p.getReviewByBook()<3.0)}">
                                            Note:
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>

                                        </c:when>

                                        <c:when test="${(p.getReviewByBook() >=3)&&(p.getReviewByBook()<4.0)}">
                                            Note:
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                        </c:when>

                                        <c:when test="${(p.getReviewByBook() >=4.0)&&(p.getReviewByBook()< 5.0)}">
                                            Note:
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>

                                        </c:when>

                                        <c:when test="${(p.getReviewByBook() == 5.0)}">
                                            Note:
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>

                                        </c:when>

                                        <c:otherwise></c:otherwise>
                                    </c:choose>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="col-lg-6">
                            <c:choose>
                                <c:when test="${p.getDiscount()==null}">
                                    <div class="titleBook "><h3>${p.getPriceTTC()} EUR TTC</h3></div> 
                                </c:when>
                                <c:when test="${p.getDiscount()==0}">
                                    <div class="titleBook "><h3>${p.getPriceTTC()} EUR TTC</h3></div>
                                </c:when>
                                <c:when test="${p.getDiscount()!=null}"> 
                                    <div class="priceBookOld">${p.getPriceTTC()} EUR TTC /</div>
                                    <div class=" titleBook  "><h3>${p.getPriceTTCWithoutDiscount()}EUR TTC</h3></div>
                                </c:when>
                            </c:choose>

                            <c:choose>
                                <c:when test="${(p.getBooQuantity()<10) &&(p.getBooQuantity()>1)}">
                                    <div >
                                        <div><span class="glyphicon glyphicon-warning-sign"></span> Plus que ${p.getBooQuantity()}  exemplaires en stock </div>
                                    </div> 
                                </c:when>
                                <c:when test="${p.getBooQuantity()==0}">
                                    <div >
                                        <div><span class="glyphicon glyphicon-warning-sign"></span> Indisponible </div>
                                    </div> 
                                </c:when>
                            </c:choose>   
                        </div>
                    </div>
                    <div class="col-lg-12 resume"> ${p.getBooResume()}</div>
                    <div class="col-lg-12 resume">
                    <c:choose>    
                         <c:when test="${(review.size()>=1)}">  

                            <div> Les commentaires de nos lecteurs : (${review.size()} avis)</div>   

                            <div class="comment col-lg-8">
                                <c:forEach items="${review}" var="rev">
                                    <div>${rev.cusId.cusFirstName} ${rev.cusId.cusLastName} | 



                                        <c:choose>
                                            <c:when test="${(rev.revNote >= 0) && (rev.revNote < 1)}">
                                                Note:
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                            </c:when>

                                            <c:when test="${(rev.revNote >= 1) && (rev.revNote < 2)}">
                                                Note:
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                            </c:when>

                                            <c:when test="${(rev.revNote >= 2) && (rev.revNote < 3)}">
                                                Note:
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>

                                            </c:when>

                                            <c:when test="${(rev.revNote >=3) && (rev.revNote < 4)}">
                                                Note:
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>
                                            </c:when>

                                            <c:when test="${(rev.revNote >=4)&&(rev.revNote< 5)}">
                                                Note:
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star-empty"></span>

                                            </c:when>

                                            <c:when test="${(rev.revNote >= 5)}">
                                                Note:
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>

                                            </c:when>

                                        </c:choose>


                                        <c:if test="${c != null}">
                                            <c:if test="${c.cusID eq rev.cusId.cusID}">
                                                | <a href="ControllerMain?section=${currentSection}&booId=${p.bookIsbn}&modifyReview">Modifier mon commentaire</a>
                                            </c:if>
                                        </c:if>
                                    </div>
                                    <c:choose>
                                        <c:when test="${(rev.revComment !=null)}">                                                    
                                            <div >${rev.revComment}</div>
                                            <br>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </div>     
                        </c:when>
                        <c:otherwise>Soyez le premier à commenter cet ouvrage !</c:otherwise>
                    </c:choose>    
                    <c:choose>
                            <c:when test="${(p.getBooQuantity()>=1)}">
                                <div class="boutonOrder col-lg-12"><a href="ControllerMain?item=${p.bookIsbn}&action=add&section=${currentSection}" class=" zocial cart">Commander</a></div>   
                        </c:when>
                    </c:choose>   
        <c:if test="${sendrev !=null}"><p><strong>Votre commentaire a bien été pris en compte. Il sera publié après validation par notre équipe de modération</strong></p></c:if>
                </c:forEach>
            </div>

        </div>
        <c:if test="${not empty displayComment}">
            <div class="guillaume">
                <form action="ControllerMain?bookGeneral" method="post">
                    <div class="col-lg-9"> 
                        <label class="labelreview" for="comment"> Donnez-nous votre avis sur ce livre (il apparaîtra sur le site après validation par notre équipe de modération)</label><br />
                        <p>
                            <textarea name="revcomment" id="comment" rows="5" cols="120">${customerReview.revComment}</textarea>
                        </p>
                    </div>
                    <div class="col-lg-3"> 
                        <div class="starRating"><!--
                            --><input name="revnote" id="e5" type="radio" value="5" <c:if test="${customerReview.revNote >= 5}">checked="true"</c:if>></a><label for="e5">☆</label><!--
                            --><input name="revnote" id="e4" type="radio" value="4" <c:if test="${customerReview.revNote >= 4 && customerReview.revNote < 5}">checked="true"</c:if>></a><label for="e4">☆</label><!--
                            --><input name="revnote" id="e3" type="radio" value="3" <c:if test="${customerReview.revNote >= 3 && customerReview.revNote < 4}">checked="true"</c:if>></a><label for="e3">☆</label><!--
                            --><input name="revnote" id="e2" type="radio" value="2" <c:if test="${customerReview.revNote >= 2 && customerReview.revNote < 3}">checked="true"</c:if>></a><label for="e2">☆</label><!--
                            --><input name="revnote" id="e1" type="radio" value="1" <c:if test="${customerReview.revNote >= 1 && customerReview.revNote < 2}">checked="true"</c:if>></a><label for="e1">☆</label>
                            </div>
                        <c:choose>
                            <c:when test="${customerReview != null}">
                                <input type="submit" name="updatereview" value="Modifier mon commentaire">
                            </c:when>
                            <c:otherwise>
                                <input type="submit" name="submitreview" value="Ajouter mon commentaire">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <input type="hidden" name="ip" value="${pageContext.request.localAddr}">
                    <input type="hidden" name="section" value="${currentSection}">
                    <input type="hidden" name="booId" value="<c:forEach items="${book}" var="p">${p.bookIsbn}</c:forEach>">
                    </form>
                </div>
        </c:if>


    </div>






    <%-- footer --%>
    <%@include file="includes/footer.jsp" %>
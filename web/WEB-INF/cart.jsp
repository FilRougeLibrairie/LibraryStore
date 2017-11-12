<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp" %>
<%@include file="includes/navigator.jsp" %>

<!DOCTYPE HTML>
<html>
    <head>
        <title>PANIER</title>
    </head>
    <body>
        <div class="container">
            <c:if test="${panierVide}">
                <table class="table" style="background-color: white">
                    <tbody>
                        <tr>
                            <td>
                                <h1>Panier Vide !</h1>
                                <p>Retourner sur</p>
                                <ul>
                                    <li><a href="ControllerMain">La page d'accueil</a></p></li>
                                    <li><a href="ControllerMain?section=monCompte">Votre compte</a></p></li>
                                </ul>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${!panierVide}">
                <table class="table" style="background-color: white">
                    <tbody>
                        <tr>
                            <td style="text-justify: right">
                                <p class=""><strong>TOTAL TTC : ${totalTTC} euros</strong></p>
                                <p class=""><strong><a href="ControllerMain?section=checkout">Commander mes articles</a></strong></p>
                                <p class=""><a href="ControllerMain">Poursuivre mes achats</a></p>
                            </td>
                        </tr>
                    </tbody>
                </table>
                
                <table class="table table-hover table-bordered" style="background-color: white">
                    <thead>
                        <tr>
                            <th>Produit</th>
                            <th>Quantité</th>
                            <th>Prix total</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${panier}" var="item">
                        <tr>
                            <c:set value="${item.stock}" var="stock" scope="page"></c:set>
                            <td<c:if test="${!item.buyable}"> style="opacity: 0.5; filter: alpha(opacity=50)"</c:if>>
                                 
                                    <img class="cover item" src="${item.imageUrl}" alt="${item.title}" style="height: 200px"/>

                                    <p class=""><strong>${item.title}</strong><br>
                                    ${item.subTitle}</p>
                                    <p class=""><c:forEach items="${item.authorsList}" var="author" varStatus="loop">
                                            ${author}
                                            <c:if test="${!loop.last}">, </c:if>
                                        </c:forEach></p>
                                <p class="text-muted text-uppercase">${item.editorName} | ${item.format} | Paru le ${item.publishDate}</p>
                             <%--   <p class=""><fmt:formatDate pattern="d-M-y" value = "${item.getPublishDate()}" /></p> --%>
                                <p class="">ISBN : ${item.ref}</p>
                                <c:choose>
                                            <c:when test="${item.discounted}">
                                                <p><span  class="" style="text-decoration: line-through">${item.unitPriceTTC} euros</span>
                                                    <span class="">${item.unitPriceWithDiscount} euros</span></p>
                                                <p><strong>Remise de ${item.vat}% dans le cadre de l'offre ${item.offerName}</strong></p>
                                            </c:when>
                                            <c:otherwise><p class="">${item.unitPriceTTC} euros</p></c:otherwise>
                                        </c:choose>
                            </td>

                            <td>
                                <c:if test="${item.buyable}">
                                <p> <span class="" style="margin:10px;"><a href="ControllerMain?item=${item.ref}&action=dec&section=${currentSection}"> - </a></span>
                                <strong>${item.quantity}</strong>
                                <span class="" style="margin:10px"><a href="ControllerMain?item=${item.ref}&action=add&section=${currentSection}"> + </a></span></p>
                                    
                            </c:if>
                                <p><a href="ControllerMain?item=${item.ref}&action=del&section=${currentSection}"> Supprimer</a><p>
                                </td>
                                    <td>
                                        <p><strong>
                                        <c:choose>
                                            <c:when test="${item.stock >= 1 && item.stock <= 10}"><strong>Plus que ${item.stock} en stock !</strong></c:when>
                                            <c:when test="${item.stock > 10}">En stock</c:when>
                                            <c:otherwise>Ce produit n'est plus disponible</c:otherwise>
                                        </c:choose>
                                            </strong></p>
                                        <c:choose>
                                            <c:when test="${item.discounted}">
                                                <p><span  class="" style="text-decoration: line-through">${item.totalPriceTTC} euros</span></p>
                                                <p><span class="">${item.totalPriceTTCWithDiscount} euros</span></p>
                                            </c:when>
                                            <c:otherwise><p class="">${item.totalPriceTTC} euros</p></c:otherwise>
                                        </c:choose>
                                        
                                        
                                    </td>
                                    
                        </tr>

                                </c:forEach> 
                   
   </tbody>
                </table>
                
             <%--   <table class="table table-hover table-bordered" style="background-color: white">
                    <tbody>
                    <tr>
                        <td>
                    <h2>Mon mode de livraison</h2>
                    <form action="ControllerMain?section=cart" method="post">
                    <c:forEach items="${shipList}" var="shipItem">
                        <div style="background-color: #cccccc">
                    <input type="radio" name="shipping" value="${shipItem.getShipId()}" id="ship${shipItem.getShipId()}" 
                           <c:choose>
                               <c:when test="${shipid != null && shipid == shipItem.getShipId()}"> checked="true"</c:when>
                               <c:when test="${shipid == null && shipItem.getShipId() == 1}"> checked="true"</c:when>
                           </c:choose>
                    >
                    <h3>${shipItem.getShipName()}</h3>
                            <p>${shipItem.getShipCost()} euros</p>
                    </div>
                    </c:forEach>
                    </form>
                        </td>
                        <td>
                            ${chosenShipCost} euros
                        </td>
                    </tr>
                         </tbody>
                </table> --%>
                
                <table class="table " style="background-color: white">
                    <tbody>
                        <tr>
                        <td style="text-justify: right">
                            <p>TOTAL TTC</p>
                            <p>Total HT</p>
                            <p>Total TVA</p>
                        </td>
                        <td style="text-justify: right">
                            <p>${totalTTC} euros</p>
                            <p>${totalHT} euros</p>
                            <p>${totalTVA} euros</p>
                        </td>
                    </tr>
                     </tbody>
                </table>
       <table class="table" style="background-color: white">
                    <tbody>
                        <tr>
                            <td style="text-justify: right">
                                <p class=""><strong><a href="ControllerMain?section=checkout">Commander mes articles</a></strong></p>
                                <p class=""><a href="ControllerMain">Poursuivre mes achats</a></p>
                            </td>
                        </tr>
                    </tbody>
                </table>
                            </c:if>
                            
                            <%@include file="includes/footer.jsp" %>
                            
                    </div>          
                    </body>
                    </html>
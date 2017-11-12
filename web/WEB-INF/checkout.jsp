<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp" %>
<%@include file="includes/navigator.jsp" %>

<c:url value="ControllerMain?section=monCompte" var="urlMonCompte" />
<c:url value="ControllerMain?section=displaycart" var="urldisplaycart" />
<!DOCTYPE HTML>
<html>
    <head>
        <title>COMMANDER</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${totalItemsCount < 1}">
                <h1>REDIRECT</h1>
                <c:redirect url="${urldisplaycart}" />
            </c:when>
            <c:when test="${empty c}">
                <h1>REDIRECT</h1>
                <c:redirect url="${urlMonCompte}" />
            </c:when>

            <c:otherwise>
                <div class="container">
                    <div class="row">
                        <!-- Conteneur principal -->
                        <div class="col-sm-7 border border-dark rounded" style="background-color: #e0e0e0">
                            <c:choose>
                                <c:when test="${step == 'shipcost'}">
                                    <form action="ControllerMain?section=checkout" method="post">
                                        <c:forEach items="${shipList}" var="shipItem">
                                            <div>
                                                <p><input type="radio" name="shippingchoice" value="${shipItem.shipId}" id="ship${shipItem.shipId}" 
                                                       <c:choose>
                                                           <c:when test="${shipid == shipItem.shipId}"> checked="true"</c:when>
                                                           <c:when test="${shipid != null && shipid == shipItem.shipId}"> checked="true"</c:when>
                                                           <c:when test="${shipid == null && shipItem.shipId == 1}"> checked="true"</c:when>
                                                       </c:choose>  
                                                       >
                                                ${shipItem.shipName} ${shipItem.shipCost} euros</p>
                                            </div>
                                        </c:forEach>
                                        <input type="submit" name="shipcostvalidate" value="Poursuivre">
                                    </form>
                                </c:when>
                                <c:when test="${step == 'address'}">
                                    <form action="ControllerMain?section=checkout" method="post">
                                        <div class="container">
                                            <c:if test="${not empty error}">
                                                <h3 style="color:red">${error}</h3>
                                            </c:if> 
                                            <h2>Choisissez vos adresses de facturation et de livraison</h2>
                                            <ul class="nav nav-pills">
                                                <li class="active"><a data-toggle="pill" href="#invoice">Facturation</a></li>
                                                <li><a data-toggle="pill" href="#shipping">Livraison</a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div id="invoice" class="tab-pane fade in active">
                                                    <c:forEach items="${addressList}" var="addr">
                                                        <div style="border:1px black solid; border-radius: 10px">
                                                            <input type="radio" name="invoiceadd" value="${addr.addId}" id="invoiceaddress${addr.addId}" 
                                                                   <c:if test="${invoiceaddid == addr.addId}"> checked="true"</c:if>>
                                                            <label for="invoiceaddress${addr.addId}">${addr.addLabel}</label>
                                                            <p>${addr.addFirstName} ${addr.getAddLastName()}</p>
                                                            <p>${addr.addComplement}</p>
                                                            <p>${addr.addNumber} ${addr.addStreetType} ${addr.addStreetName}</p>
                                                            <p>${addr.addZipCode}  ${addr.addCity} </p>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                                <div id="shipping" class="tab-pane fade">
                                                    <c:forEach items="${addressList}" var="addr">
                                                        <div style="border:1px black solid; border-radius: 10px">
                                                            <input type="radio" name="shipadd" value="${addr.addId}" id="shipaddress${addr.addId}"
                                                                   <c:if test="${shipaddid == addr.addId}"> checked="true"</c:if>>
                                                            <label for="shipaddress${addr.addId}">${addr.addLabel}</label>
                                                            <p>${addr.addFirstName} ${addr.getAddLastName()}</p>
                                                            <p>${addr.addComplement}</p>
                                                            <p>${addr.addNumber} ${addr.addStreetType} ${addr.addStreetName}</p>
                                                            <p>${addr.addZipCode}  ${addr.addCity} </p>

                                                            <p>DigiCode : <c:choose>
                                                                    <c:when test="${addr.addSecurityCode != null}">${addr.addSecurityCode}</c:when>
                                                                    <c:otherwise>Non renseigné</c:otherwise>
                                                                </c:choose>
                                                            </p>
                                                            <p>Téléphone : <c:choose>
                                                                    <c:when test="${addr.addPhone != null}">${addr.addPhone}</c:when>
                                                                    <c:otherwise>Non renseigné</c:otherwise>
                                                                </c:choose>
                                                            </p>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                        <input type="submit" name="addressvalidate" value="Poursuivre">
                                    </form>
                                    <p><input type="button" name="newadd" value="Ajouter une nouvelle adresse" onclick="showNewAddDiv()" /></p>
                                    <script>function showNewAddDiv() {
                                            document.getElementById('newadd').style.display = "block";
                                        }</script>
                                    <div id="newadd" style="display:none">
                                        <form action="ControllerMain?section=checkout" method="post">

                                            <label for="label"><h4>Donnez un nom à cette adresse</h4></label>
                                            <p><input type="text" name="label" id="label"></p>
                                            <p> <label for="firstname">Prénom</label> : <input type="text" name ="firstname" id="firstname"></p>
                                            <p> <label for="lastname">Nom</label> : <input type="text" name ="lastname" id="lastname"></p>
                                            <p> <label for="company">Société</label> : <input type="text" name ="company" id="company"></p>
                                            <p> <label for="streetnumber">Numéro</label> : <input type="text"  name="streetnumber" id="streetnumber"></p>
                                            <p> <label for="streettype">Type de voie</label> : <input type="text"  name="streettype" id="streettype"></p>
                                            <p> <label for="streetname">Nom de voie</label> : <input type="text"  name="streetname" id="streetname"></p>
                                            <p> <label for="streetcomplement">Complément d'adresse</label> : <input type="text"  name="streetcomplement" id="streetcomplement"></p>
                                            <p> <label for="zipcode">Code postal *</label> : <input type="text"  maxlength="5" name="zipcode" id="zipcode" required></p>
                                            <p> <label for="city">Ville</label> : <input type="text" name="city" id="city"></p>
                                            <p> <label for="securitycode">Code de votre résidence (pour la livraison)</label> : <input type="text" name="securitycode" id="securitycode"></p>
                                            <p> <label for="phone">Téléphone</label> : <input type="tel" maxlength="10" name="phone" id="phone"></p>
                                            <input type="submit" name="submitnewadd" value="Valider mon adresse">
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${step == 'payment'}">
                                    <h1>Payer ma commande</h1>
                                    <p>Vous serez redirigé vers le site de notre banque partenaire.</p>
                                    <form action="ControllerMain?section=checkout" method="post">
                                        <p><input type="checkbox" name="cgu" required> 
                                            En cochant cette case, j’accepte et je reconnais avoir pris connaissance des Conditions Générales de Vente.
                                            <a href="http://localhost:8080/LibraryStore/document/cgu.pdf" target="_blank">En savoir plus</a></p>
                                        <input type="hidden" name="ip" value="${pageContext.request.localAddr}">
                                        <p><input type="submit" name="gotobank" value="Payer ma commande"></p>
                                    </form>
                                </c:when>
                                <c:when test="${step == 'recap'}">
                                    <h3>Votre commande a été enregistrée sous le numéro ${orderid} </h3>
                                    <p>Vous pouvez suivre les étapes de sa préparation dans votre espace personnel.</p>
                                </c:when>
                                <c:when test="${step == 'payfail'}">
                                    <h3>Nous avons rencontré un problème lors de la validation de votre paiement. </h3>
                                   <!-- <p>Veuillez contacter votre banque ou utiliser un autre moyen de paiement.</p>-->
                                <!--    <h4>Pour vous consoler, nous vous offrons ce moment de détente avec un chaton</h4> -->
                                <h4>Sentez-vous le mépris dans le regard de cet adorable animal ?</h4>
                                    
                                        <img src="image/fripouille.jpg" width="100%" height="100%" style="display:block;">
                                    
                                </c:when>
                                <c:when test="${step == 'outofstock'}">
                                    <p>La totalité de votre commande n'a pu être satisfaite, <a href="${urldisplaycart}">vérifiez votre panier !</a></p>

                                </c:when>

                            </c:choose>
                            <p>
                        </div>






                        <!-- Colonne de droite -->
                        <c:if test="${step != 'recap'}">
                        <div class="col-sm-offset-1 col-sm-4 border border-dark rounded" style="background-color: #e0e0e0">
                            <h2>Ma commande</h2>
                            <p>${totalItemsCount} article<c:if test="${totalItemsCount > 1}">s</c:if></p>
                                <hr>
                            <c:forEach items="${panier}" var="item">
                                <c:if test="${item.isBuyable()}">
                                    <p class="">${item.getTitle()} | ${item.getQuantity()} exemplaire<c:if test="${item.getQuantity() > 1}">s</c:if><br>
                                    <p style="font-size: small; text-transform: uppercase; color: darkgray">${item.getEditorName()}</p>
                                    <p>${item.getTotalPriceTTCWithDiscount()} euros</p>
                                    <hr style="border-top: 1px dashed #8c8b8b">
                                </c:if>
                            </c:forEach>
                            <h3>Total des livres : ${totalTTC} euros TTC</h3>
                            <hr>

                            <c:if test="${shippingchoice != null}">
                                <p>Frais de livraison : ${shippingchoice.shipCost} euros</p>
                            </c:if>
                            <h3><strong>TOTAL : <span style="color: red">
                                        <c:choose>
                                            <c:when test="${not empty totalTTCWithShipCost}">${totalTTCWithShipCost}</c:when>
                                            <c:otherwise>${totalTTC}</c:otherwise>
                                        </c:choose>
                                        euros</span></strong></h3>
                        </div>
                            </c:if>
                    </div>


                    <%@include file="includes/footer.jsp" %>

                </div>   
            </c:otherwise>
        </c:choose>
    </body>
</html>
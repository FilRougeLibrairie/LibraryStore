<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Société Qui Régale</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${bank eq 'payment'}">
                <h1>Payer ma commande</h1>

                <div>
                    <p>Référence de la commande : <strong>${purchaseid}</strong></p>
                    <p>Montant : <strong>${totalTTC} euros</strong></p>
                </div>
                <hr>
                <c:if test="${inputerror != null}"><p><strong>Erreur de saisie</strong></p></c:if>
                    <form action="ControllerMain?section=checkout" method="post">
                        <p> <label for="numerocb">Numéro de carte bancaire</label> : <input type="text"  name="numerocb" id="numerocb" size="19" maxlength="19" required></p>
                        <p>Date d'expiration 
                            <select name="month" size="1" class="vads-expiry-date-input" required>
                                <option value="">mois</option>
                                <option value="01"  > 01-Janvier </option>
                                <option value="02"  > 02-Février </option>
                                <option value="03"  > 03-Mars </option>
                                <option value="04"  > 04-Avril </option>
                                <option value="05"  > 05-Mai </option>
                                <option value="06"  > 06-Juin </option>
                                <option value="07"  > 07-Juillet </option>
                                <option value="08"  > 08-Août </option>
                                <option value="09"  > 09-Septembre </option>
                                <option value="10"  > 10-Octobre </option>
                                <option value="11"  > 11-Novembre </option>
                                <option value="12"  > 12-Décembre </option>
                            </select>

                            <select name="year" size="1" required>
                                <option value=""> année</option>
                                <option value="2017"> 2017</option>
                                <option value="2018"> 2018</option>
                                <option value="2019"> 2019</option>
                                <option value="2020"> 2020</option>
                                <option value="2021"> 2021</option>
                                <option value="2022"> 2022</option>
                                <option value="2023"> 2023</option>
                                <option value="2024"> 2024</option>
                                <option value="2025"> 2025</option>
                                <option value="2026"> 2026</option>
                                <option value="2027"> 2027</option>
                            </select>
                            </span>
                        </p>
                        <label for="cvv">Cryptogramme visuel de la carte</label> : <input type="text" name="cvv" id="cvv" size="3" maxlength="3" required>
                        <input type="submit" name="paymentvalidate" value="Payer ma commande">
                    </form>
            </c:when>
            <c:when test="${bank eq 'validation'}">
                <h1>BONJOUR LA BANQUE</h1>
                <form action="ControllerMain?section=checkout" method="post">
                    <p><input type="submit" name="bankcallback" value="validate"></p>                              
                </form>
                <form action="ControllerMain?section=checkout" method="post">               
                    <p><input type="submit" name="bankcallback" value="rejected"></p>                 
                </form>
            </c:when>
        </c:choose>
    </body>
</html>

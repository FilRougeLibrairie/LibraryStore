<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="includes/header.jsp" %>


<%@include file="includes/navigator.jsp" %>

<c:url value="ControllerMain?section=bookGeneral&booId=" var="urlFicheProduit" />

<%--<%@include file="includes/menuLeftBook.jsp" %>--%>
<%--<div class="centralSection col-lg-10 col-md-10 col-sm-10 col-xs-10 ">--%>



<div class="searchTheme"> Recherche par thème :</div>
<form class="themeCombo"action="ControllerMain">
    <input type="hidden" name="section" value="book" />
    <select name="listeIdTheme">
        <c:forEach items="${listeThemes}" var="listeT">
            <option value="${listeT.theId}"> ${listeT.theName} </option>
        </c:forEach>
    </select>

    <input type='submit' name='recherche' value='Recherche'>
    <c:if test="${listeSousThemes != null}">
        <div class="searchTheme"> Recherche par sous-thème :</div>
        <select name="listeIdSousTheme">

            <c:forEach items="${listeSousThemes}" var="listeST">
                <option value="${listeST.subId}">${listeST.subName}</option>
            </c:forEach>        
            <%--   <c:forEach items="${listeBookSousTheme}" var="listeBookST">
                   <option>${listeBookST}</option>
               </c:forEach> --%>
        </select>                  
        <input type='submit' name='affichage' value='Afficher'>   
    </c:if>
</form>

<c:forEach items="${listeLivre}" var="ll">
    <a href="${urlFicheProduit}${ll.booIsbn13}"><img class="image" src="${ll.booFrontCover}"></a>    
    <div style="padding-left: 20%; color: black">
    <h4><a class="infosLivre"href="${urlFicheProduit}${ll.booIsbn13}">${ll.booTitle}</a></h4><br> 
        <div>${ll.booPublishYear}</a></div></div><br>
    <div>${ll.ediId}</div>
    <%--<div> ${ll.booPriceHT * ll.vatCode.vatRate } euros TTC</div>--%>
    <div class="livreResume"style=""><span>${ll.booResume}</span></div><br>  
    <hr />
</c:forEach>
<br>
<hr/>
<%--
<c:choose>
    <c:when test="${listeBookSousTheme != null}">      
        <c:forEach items="${listeBookSousTheme}" var="lbst">
            <a href="${url400}"><img src="${lbst.booFrontCover}" 
                                     style="float: left; padding-right: 20px; padding-left: 20px; padding-bottom: 10px; 
                                     max-height: 150px; max-width: available"></a>   
            <div style="padding-left: 20%; color: black">
                <h4><a href="${url400}" style="text-align: center; color: black; font-weight: bold">${lbst.booTitle}</a></h4><br>       
                <span>${lbst.booPublishYear}</a></span></div><br>
            <div style="width: 90%; color: black; position: relative; padding-left: 20%"><span>${lbst.booResume}</span></div> 
            <br> 
            <br>    
            <hr/>
        </c:forEach>
    </c:when>   

    <c:otherwise>
        <c:forEach items="${listeLivre}" var="ll">
           <a href=""><img src="${ll.booFrontCover}" 
                                             style="float: left; padding-right: 20px; padding-left: 20px; padding-bottom: 10px;  
                                             max-height: 150px; max-width: available"></a>    
            <div style="padding-left: 20%; color: black">
                <h4><a href="${url400}" style="text-align: center; color: black; font-weight: bold">${ll.booTitle}</a></h4><br>       
                <span>${ll.booPublishYear}</a></span></div><br>
            <div>${ll.ediId}</div>
<%--<div> ${ll.booPriceHT * ll.vatCode.vatRate } euros TTC</div>--%>
<%--      <div style="width: 90%; color: black; position: relative; padding-left: 20%"><span>${ll.booResume}</span></div> <br>  
      <hr />
  </c:forEach>
</c:otherwise>
</c:choose>--%>
<%-- footer --%>
<%@include file="includes/footer.jsp" %>
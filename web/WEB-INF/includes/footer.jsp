<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">

    <div class="bottomSection col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-12 col-xs-12"> 



        

        <c:forEach items="${listeLibrary}" var="p">

            <div class="contact col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <br>
                <div><span class="glyphicon glyphicon-envelope"></span><a href="mailto:${p.getMyLibEmail()}">${p.getMyLibEmail()}</a></div>
                <div><span class="glyphicon glyphicon-earphone"></span>    ${p.getMyLibPhone()}</div>
                <div><span class="glyphicon glyphicon-briefcase"></span>    N° de Siret: ${p.getMyLibSiret()}</div>
                <div><span class="glyphicon glyphicon-download-alt"></span><a href="${p.getMyLibCGU()}">    Nos conditions Générales</a></div>
            </div>

            <div class="adress col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <br>
                <br>
                <div>${p.getMyLibAddNumber()} ${p.getMyLibAddStreetName()} ${p.getMyLibAddComplement()}</div>
                <div>${p.getMyLibAddZipCode()} ${p.getMyLibAddCity() }</div>
                <br>
            </div>


        </c:forEach>





    </div>

</div>      

</div>
</div>

<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>
<div class="row">
  
    <c:url value="ControllerMain?section=offerbook" var="url120" />

    <div class="leftSection col-lg-2 col-md-2 col-sm-2 col-xs-2">

        <div class="leftSectionMenu">
            <c:forEach items="${listeOffer}" var="p"> 
                
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="${url120}&offid=${p.getOffId()}">${p.getOffName()}</a>
                            </h4>
                        </div>
                    </div>
                </div>
            </c:forEach> 
        </div>

    </div>

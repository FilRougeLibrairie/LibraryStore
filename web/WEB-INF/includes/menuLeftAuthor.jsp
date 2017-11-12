<div class="row">

    <c:url value="ControllerMain?section=authorAscii" var="url122" />
    <c:url value="ControllerMain?section=author" var="url126" />

    <div class="leftSection col-lg-2 col-md-2 col-sm-2 col-xs-2">

        <div class="leftSectionMenu">
            
            <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="${url126}">Tous les auteurs</a>
                            </h4>
                        </div>
                    </div>
                </div>
            <c:forEach items="${listeKeys}" var="p"> 
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="${url122}&ascii=${p}">${p}</a>
                            </h4>
                        </div>
                    </div>
                </div>
            </c:forEach> 
        </div>



    </div>


     <c:url value="ControllerMain?section=pageCatalog" var="url102" />
    <c:url value="ControllerMain?section=pageNewBook" var="url108" />
    <c:url value="ControllerMain?section=pagePriceInf" var="url109" />
    <c:url value="ControllerMain?section=pageReview5" var="url112" />
    <c:url value="ControllerMain?section=pageReview4" var="url113" />
    <c:url value="ControllerMain?section=pageReview3" var="url114" />
    <c:url value="ControllerMain?section=pageReview2" var="url115" />
    <c:url value="ControllerMain?section=pageReview1" var="url116" />
    <c:url value="ControllerMain?section=pageReview0" var="url117" />
    <c:url value="ControllerMain?section=new" var="url118" />
    <c:url value="ControllerMain?section=pagePriceInf2" var="url150" />
    <c:url value="ControllerMain?section=pagePriceInf3" var="url151" />
    
 <div class="row">   

    <div class="leftSection col-lg-2 col-md-2 col-sm-2 col-xs-2">

        <div class="leftSectionMenu">

            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="${url102}&page=1">Tout le catalogue</a>
                        </h4>
                    </div>
                </div>
            </div>


            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="${url108}&page=1">A paraître</a>
                        </h4>
                    </div>
                </div>
            </div>

            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="${url118}&page=1">Nouveauté</a>
                        </h4>
                    </div>
                </div>
            </div>


            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#collapse2">Par Prix</a>
                        </h4>
                    </div>
                    <div id="collapse2" class="panel-collapse collapse">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="${url109}&page=1">De 0 EUR à 10 EUR</a></li>
                            <li class="list-group-item"><a href="${url150}&page=1">De 10 EUR à 20 EUR</a></li>
                            <li class="list-group-item"><a href="${url151}&page=1">Plus de 20 EUR </a></li>
                        </ul>
                        <div class="panel2-footer"></div>
                    </div>
                </div>
            </div>


            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#collapse3">Par Note</a>
                        </h4>
                    </div>
                    <div id="collapse3" class="panel-collapse collapse">
                        <ul class="list-group">
                            <div class="star rating">
                                <li class="list-group-item">

                                    <a href="${url112}&page=1">
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                    </a>

                                </li>
                                
                                <li class="list-group-item">
                                    <a href="${url113}&page=1" >
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                    </a>
                                </li>
                                
                                
                                <li class="list-group-item">
                                    <a href="${url114}&page=1" >
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                    </a>
                                </li>
                                
                                <li class="list-group-item">
                                    <a href="${url115}&page=1">
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                    </a>
                                </li>
                                
                                <li class="list-group-item">
                                    <a href="${url116}&page=1">
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                    </a>
                                </li>
                                
                                <li class="list-group-item">
                                    <a href="${url117}&page=1">
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                    </a>
                                </li>
                            </div>
                        </ul>
                        <div class="panel3-footer"></div>
                    </div>
                </div>
            </div>

        </div>
    </div>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/zocial.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">



        <title>Caroline</title>
    </head>
    <body>
        <div class="contener">

            <div class ="cadre">

                <header class="container">
                    <div class= "topHeader row">


                        <div class="logo col-lg-3 col-md-3 col-sm-6 col-xs-6"><img src="image/logo.png"/> </div>
                        
                        <c:url value="ControllerMain?section=search&page=1" var="url500" />


                        <div class="search col-lg-4 col-md-4 col-sm-6 col-xs-6">
                            <form action="${url500}" method="post">
                                <div class="input-group">
                                    <div class=" searchCombo input-group-btn">

                                        <select class="input-button-size-header form-control version-selector selectSearch" name="searchType" style="float: right;"> 

                                            
                                            <option value="livre">Titre du livre</option>
                                            <option value="auteur"> Nom d'auteur</option>
                                            <option value="isbn">Isbn</option>
                                            <option value="mots">Mots Clés</option>

                                        </select>

                                    </div>
                                <%--    <input type="page" value="${currentSection}"> --%>
                                    <input type="text" class="form-control" name="searchBarre" placeholder="rechercher">
                                    <div> <input name="searchButton" type="submit" class="btn btn-default" value="OK"/>
                                    </div><!-- /input-group -->
                                </div>
                            </form>       
                        </div>   


                        <div class="socialIcon col-lg-3 col-md-3 hidden-sm hidden-xs">

                            <a href="#" class=" zocial icon facebook">Sign in with Facebook</a>
                            <a href="#" class=" zocial icon twitter">Sign in with Twitter</a>
                            <a href="#" class=" zocial icon gmail">Sign in with Gmail</a>
                            <a href="#" class=" zocial instagram icon">Sign in with Instagram</a>

                        </div>

                        <div class="head col-lg-2 col-md-2 hidden-sm hidden-xs"> <img src="image/feuille2.png"/>
                        </div>

                </header>
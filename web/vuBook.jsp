
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Page</title>
    </head>
    <body>
          <c:url value="ControleurBook?section=menu-main" var="url01" />
        <c:import url="${url01}" />
        <c:forEach items="${clefs}" var="c">
            <div>
                <h1>${c}</h1>
                <ul>
                    <c:forEach items="${listeBook.get(c)}" >
                                 <li>
                        <c:url value="#" var="url350" />
                        <a href="${url350}">${p}</a>
                    </li>
                </c:forEach>
                </ul>
            </div>
        </c:forEach>
     
    </body>
</html>

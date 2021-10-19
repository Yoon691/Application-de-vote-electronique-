<%--
  Created by IntelliJ IDEA.
  User: Lionel
  Date: 03/10/2021
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.user == null}">
<%response.sendError(HttpServletResponse.SC_FORBIDDEN, "Vous ne pouvez pas accéder a cette page ,connectez-vous pour accéder  ");%>
</c:if>
<html>
<head>
    <title>Page de Vote</title>
    <link rel="stylesheet" type="text/css" href="../static/vote.css">
</head>
<body>
<jsp:include page="header.jsp"><jsp:param name="titre-header" value="Voter pour qui vous voulez"/></jsp:include>
<main id="contenu" class="wrapper">
    <jsp:include page="menu.jsp"/>
    <article class="contenu">

    <form method="post" action="vote">
        <label>Sélectionnez un candidat :

        </label>

        <select name="candidat-choisi" >

            <option value="">--|Choisissez un candidat|--</option>
            <c:forEach items="${applicationScope.candidats.keySet()}" var="nomCandidat">
            <option ><c:out value="${nomCandidat}"/> </option>
            </c:forEach>
        </select>

            <p>
                <input type="submit" name="action" value="Envoyer votre vote ">
            </p>
        </form>
    </article>
</main>

<jsp:include page="footer.html"/>
</body>
</html>

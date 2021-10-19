<%--
  Created by IntelliJ IDEA.
  User: Lionel
  Date: 03/10/2021
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bulletins" type="java.util.List" beanName="bulletins" scope="application"/>
<jsp:useBean id="ballots" type="java.util.Map" beanName="ballots" scope="application"/>
<c:if test="${sessionScope.user == null}">
    <%response.sendError(HttpServletResponse.SC_FORBIDDEN, "Vous ne pouvez pas accéder a cette page ,connectez-vous pour accéder ");%>
</c:if>
<html>
<head>
    <title>Preuve de Vote</title>
    <link rel="stylesheet" type="text/css" href="../static/vote.css">
</head>
<body>
<jsp:include page="header.jsp"><jsp:param name="titre-header" value="Votre preuve de vote"/></jsp:include>
<main id="contenu" class="wrapper">
    <jsp:include page="menu.jsp"/>
    <article class="contenu">
        <form method="post" action="ballot">
            <label>Votre Vote :
                <c:choose>
                    <c:when test="${applicationScope.ballots.containsKey(sessionScope.user.login)}">
                        <c:out value="${sessionScope.candidatVoter.nom}"/>  <c:out value="${sessionScope.candidatVoter.prenom}"/>
                    </c:when>

                    <c:otherwise><c:out value="Vous n’avez pas encore voté"/></c:otherwise>
                </c:choose><br>

                <label>Nombre de vote actuelle :
                    <c:out value="${requestScope.nombreVote}"/>
                </label>
            </label>
            <p>
                <input type="submit" name="action" value="supprimer">
            </p>
        </form>
    </article>
</main>

<jsp:include page="footer.html"/>
</body>
</html>

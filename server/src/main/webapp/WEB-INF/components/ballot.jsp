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
    <%response.sendError(HttpServletResponse.SC_FORBIDDEN, "Vous ne pouvez pas acceder a cette page connctez-vous pour acceder ");%>
</c:if>
<html>
<head>
    <title>Preuve de Vote</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/vote.css">
</head>
<body>
<jsp:include page="header.jsp"><jsp:param name="titre-header" value="Votre pruve de vote"/></jsp:include>
<main id="contenu" class="wrapper">
    <jsp:include page="menu.jsp"/>
    <article class="contenu">

        <form method="post" action="${pageContext.request.contextPath}/election/deleteVote">
            <label>Votre Vote :
                <c:choose>
                    <c:when test="${applicationScope.ballots.containsKey(sessionScope.user.login)}">
                        <c:out value="${sessionScope.candidatVoter.nom}"/>  <c:out value="${sessionScope.candidatVoter.prenom}"/>
                    </c:when>

                    <c:otherwise><c:out value="Vous avez pas encore voter"/></c:otherwise>
                </c:choose><br>
<%--                <c:if test="${applicationScope.ballots.containsKey(sessionScope.user.login)}">--%>
<%--                   <c:out value="${sessionScope.candidatVoter.nom}"/>  <c:out value="${sessionScope.candidatVoter.prenom}"/> </label>--%>
<%--                </c:if>--%>

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

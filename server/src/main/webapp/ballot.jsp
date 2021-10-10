<%--
  Created by IntelliJ IDEA.
  User: Lionel
  Date: 03/10/2021
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="fr.univlyon1.m1if.m1if03.cherbal.classes.Bulletin" %>
<%@ page import="fr.univlyon1.m1if.m1if03.cherbal.classes.Candidat" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="bulletins" type="java.util.List" beanName="bulletins" scope="application"/>
<jsp:useBean id="ballots" type="java.util.Map" beanName="ballots" scope="application"/>
<c:if test="${sessionScope.user == null}">
    <%response.sendError(HttpServletResponse.SC_FORBIDDEN, "Vous ne pouvez pas acceder a cette page connctez-vous pour acceder ");%>
</c:if>
<html>
<head>
    <title>Preuve de Vote</title>
    <link rel="stylesheet" type="text/css" href="static/vote.css">
</head>
<body>
<jsp:include page="WEB-INF/components/header.jsp"><jsp:param name="titre-header" value="Votre pruve de vote"/></jsp:include>
<main id="contenu" class="wrapper">
    <jsp:include page="WEB-INF/components/menu.jsp"/>
    <article class="contenu">

        <form method="post" action="DeleteVote">
            <label>Votre Vote :
                <c:choose>
                    <c:when test="${applicationScope.ballots.containsKey(sessionScope.user.login)}">
                        <c:out value="${sessionScope.candidatVoter.nom}"/>  <c:out value="${sessionScope.candidatVoter.prenom}"/>
                    </c:when>

                    <c:otherwise><c:out value="Vous avez pas encore voter"/></c:otherwise>
                </c:choose>
<%--                <c:if test="${applicationScope.ballots.containsKey(sessionScope.user.login)}">--%>
<%--                   <c:out value="${sessionScope.candidatVoter.nom}"/>  <c:out value="${sessionScope.candidatVoter.prenom}"/> </label>--%>
<%--                </c:if>--%>
            </label>
            <p>
                <input type="submit" name="action" value="supprimer">
            </p>
        </form>
    </article>
</main>

<jsp:include page="WEB-INF/components/footer.html"/>
</body>
</html>
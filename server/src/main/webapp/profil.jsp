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
<%@ page import="java.util.Set" %>
<%--<jsp:useBean id="bulletins" type="java.util.List" beanName="bulletins" scope="application"/>--%>
<%--<jsp:useBean id="bulletins" type="java.util.List" beanName="bulletins" scope="application"/>--%>
<c:if test="${sessionScope.user == null}">
    <%response.sendError(HttpServletResponse.SC_FORBIDDEN, "Vous ne pouvez pas acceder a cette page connctez-vous pour acceder ");%>
</c:if>
<html>
<head>
    <title>Page de Vote</title>
    <link rel="stylesheet" type="text/css" href="static/vote.css">
</head>
<body>
<jsp:include page="WEB-INF/components/header.jsp"><jsp:param name="titre-header" value="Voter pour qui vous voulez"/></jsp:include>
<main id="contenu" class="wrapper">
    <jsp:include page="WEB-INF/components/menu.jsp"/>
    <article class="contenu">
            <form method="post" action="profil">
                <h2>Mettrez à jour votre profil</h2>
                <p>
                    <label>
                        Entrez votre nouveau nom :
                        <input type="text" name="new-nom" autofocus>
                    </label>
                </p>
                <p>
                    <input type="submit" name="action" value="Modifiez">
                </p>
            </form>
    </article>
</main>

<jsp:include page="WEB-INF/components/footer.html"/>
</body>
</html>

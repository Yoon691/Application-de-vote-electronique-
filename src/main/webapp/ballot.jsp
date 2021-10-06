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
<html>
<head>
    <title>Preuve de Vote</title>
    <link rel="stylesheet" type="text/css" href="static/vote.css">
</head>
<body>
<header>
    <c:if test="${sessionScope.user != null}">
        <p class="header-user"> Bonjour ${sessionScope.user.nom}</p>
    </c:if>
    <h1 class="header-titre">Votre preuve de vote</h1>
</header>
<main id="contenu" class="wrapper">
    <aside class="menu">
        <h2>Menu</h2>
        <ul>
            <li><a href="vote.jsp">Voter</a></li>
            <li><a href="ballot.jsp">Votre vote</a></li>
            <li><a href="resultats.jsp">Résultats</a></li>
            <li><a href="Deco">Déconnexion</a></li>
        </ul>
    </aside>
    <article class="contenu">
        <%--        <h2>Sélectionnez un candidat : </h2>--%>
        <%-- jsp:useBean id="votes" scope="request" class="java.util.HashMap" /--%>
<%--        <%--%>
<%--            Map<String, Integer> votes = new HashMap<>();--%>
<%--            for (String nomCandidat : ((Map<String, Candidat>) application.getAttribute("candidats")).keySet()) {--%>
<%--                votes.put(nomCandidat, 0);--%>
<%--            }--%>
<%--//            for (Bulletin bulletin : (List<Bulletin>) bulletins) {--%>
<%--//                int score = ((Map<String, Integer>) votes).get(bulletin.getCandidat().getNom());--%>
<%--//                votes.put(bulletin.getCandidat().getNom(), ++score);--%>
<%--//            }--%>
<%--        %>--%>
        <form method="post" action="vote">
            <label>Sélectionnez un candidat : <c:out value="${nomCandidat}"/> </label>

            <p>
                <input type="submit" name="action" value="supprimer">
            </p>
        </form>
    </article>
</main>
</body>
</html>

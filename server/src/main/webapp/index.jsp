<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Vote - accueil</title>
    <link rel="stylesheet" type="text/css" href="static/vote.css">
</head>
<body>
<jsp:include page="WEB-INF/components/header.jsp"><jsp:param name="titre-header" value="Bienvenue sur notre application de vote revolutionnaire !"/></jsp:include>

<main id="contenu" class="wrapper">
    <jsp:include page="WEB-INF/components/menu.jsp"/>
    <article class="contenu">
        <form method="post" action="init">
            <h2>Connectez-vous pour pouvoir voter</h2>
            <p>
                <label>
                    Entrez votre login :
                    <input type="text" name="login" autofocus>
                </label>
            </p>
            <p>
                <label>
                    Entrez votre nom :
                    <input type="text" name="nom">
                </label>
            </p>
            <p>
                <input type="submit" name="action" value="Connexion">
            </p>
        </form>
    </article>
</main>
<jsp:include page="WEB-INF/components/footer.html"/>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="menu">
   <h2>Menu</h2>
   <ul>
      <li><a href="resultats">Résultats</a></li>
      <c:if test="${sessionScope.user != null}">
         <li><a href="vote.jsp">Voter</a></li>
         <li><a href="ballot.jsp">Votre vote</a></li>
         <li><a href="profil.jsp">mettre à jour votre profil</a></li>
         <li><a href="listBallots.jsp">list de Ballots</a></li>
         <li><a href="deco">Déconnexion</a></li>
      </c:if>
   </ul>
</aside>



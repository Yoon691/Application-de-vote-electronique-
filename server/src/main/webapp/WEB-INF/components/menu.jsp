<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="menu">
   <h2>Menu</h2>
   <ul>
      <li><a href="resultats">Résultats</a></li>
      <c:if test="${sessionScope.user != null}">
         <li><a href="vote">Voter</a></li>
         <c:if test="${!sessionScope.user.admin}">
         <li><a href="ballot">Votre vote</a></li>
         </c:if>
         <li><a href="user">mettre à jour votre profil</a></li>
         <c:if test="${sessionScope.user.admin}">
         <li><a href="listBallots">list de Ballots</a></li>
         </c:if>
         <li><a href="${pageContext.request.contextPath}/deco">Déconnexion</a></li>
      </c:if>
   </ul>
</aside>



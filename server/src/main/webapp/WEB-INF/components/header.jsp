<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
  <c:if test="${sessionScope.user != null}">
  <p class="header-user"> Bonjour ${sessionScope.user.nom}</p>
  </c:if>
  <h1 class="header-titre">${param['titre-header']}</h1>


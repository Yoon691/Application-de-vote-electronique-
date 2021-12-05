<%--
  Created by IntelliJ IDEA.
  User: derba
  Date: 14/11/2021
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<% System.out.println("jsp2");%>

<section>
    <table>
        <tr>
            <th>Noms candidats</th>

        </tr>
        <c:forEach items="${applicationScope.candidats.keySet()}" var="nomCandidat">
            <tr>
                <td>${nomCandidat}</td>
            </tr>
        </c:forEach>
    </table>
</section>


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
<% System.out.println("jsp4");%>
<jsp:useBean id="DTO" type="java.util.ArrayList" scope="request" beanName="resultats"/>

<section>

    <table>
        <tr>
            <th>Resultats</th>

        </tr>
        <c:forEach items="${DTO}" var="resultats">
            <% System.out.println();%>
            <tr>
                <th>"Nom : "</th>
                <td>${resultats.nomCandidat}</td>
                <th>"Vote : "</th>
                <td>${resultats.votes}</td>
            </tr>
        </c:forEach>
    </table>
</section>
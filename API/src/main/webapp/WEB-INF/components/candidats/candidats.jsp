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
        <% System.out.println("jsp1");%>

<%--<jsp:useBean id="candidat" type="fr.univlyon1.m1if.m1if03.classes.Candidat" scope="request"/>--%>

<section>
<%--&lt;%&ndash;    <p>Salut</p>&ndash;%&gt;--%>
<%--    <tr>--%>
<%--        <% System.out.println("jsp1");%>--%>
<%--        <c:forEach items="${applicationScope.candidats.keySet()}" var="nomCandidat">--%>
<%--            <% System.out.println("jsp2");%>--%>
<%--        <a>${nomCandidat}</a>--%>
<%--            <% System.out.println("jsp3");%>--%>
<%--        </c:forEach>--%>
<%--    </tr>--%>

<table>
<tr>
    <th>Candidats</th>

</tr>
<c:forEach items="${applicationScope.candidats.keySet()}" var="nomCandidat">
    <tr>
    <td><a href="http://localhost:8080/election/candidats/${nomCandidat}">${nomCandidat}</a></td>
    </tr>
    </c:forEach>
</table>
    </section>


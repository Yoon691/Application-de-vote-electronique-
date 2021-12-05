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
<jsp:useBean id="DTO" type="java.util.ArrayList" scope="request" beanName="users"/>

<section>

    <table>
        <tr>
            <th>Users</th>

        </tr>
        <c:forEach items="${DTO}" var="userLogin">
            <tr>
                <td><a href="http://localhost:8080/election/candidats/${userLogin}">${userLogin}</a></td>
            </tr>
        </c:forEach>
    </table>
</section>



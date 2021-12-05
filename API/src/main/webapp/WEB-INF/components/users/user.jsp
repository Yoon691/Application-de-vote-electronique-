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
<jsp:useBean id="DTO" type="fr.univlyon1.m1if.m1if03.dto.UserDTO" scope="request" beanName="users"/>

<section>

    <table>
        <tr>
            <th>User</th>

        </tr>
            <tr>
                <td>"login ":${DTO.login}</td>
            </tr>
        <tr>
            <td>"nom ":${DTO.nom}</td>
        </tr>
        <tr>
            <td>"admin ":${DTO.admin}</td>
        </tr>
    </table>
</section>



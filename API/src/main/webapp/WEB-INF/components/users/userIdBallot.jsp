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
<jsp:useBean id="DTO" type="fr.univlyon1.m1if.m1if03.dto.BallotDTO" scope="request" beanName="ballot"/>

<section>

    <table>
        <tr>
            <th>Ballot user</th>

        </tr>
        <tr>
            <td>"User ":${DTO.login}</td>
        </tr>
        <tr>
            <td>"Ballot ":${DTO.nomCandidat}</td>
        </tr>
    </table>
</section>



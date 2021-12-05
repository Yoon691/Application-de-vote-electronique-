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
<jsp:useBean id="DTO" type="fr.univlyon1.m1if.m1if03.dto.CandidatDTO" scope="request" beanName="candidat"/>

<section>

    <table>
        <tr>
            <th>Candidat</th>

        </tr>
        <tr>
            <td>"prenom ":${DTO.prenom}</td>
        </tr>

        <tr>
            <td>"nom ":${DTO.nom}</td>
        </tr>
    </table>
</section>



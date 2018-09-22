<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tp" tagdir="/WEB-INF/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>

<tp:table_dark>
    <jsp:attribute name="header">
        <th scope="col">#</th>
        <th scope="col">Descrição</th>
        <th scope="col"></th>
    </jsp:attribute>

    <jsp:attribute name="content">
        <c:choose>
            <c:when test="${func:length(produtos) gt 0}">
                <c:forEach var="produto" items="${produtos}">
                    <tr id="produto-${produto.id}">
                        <th scope="row">${produto.id}</th>
                        <td><c:if test="${func:length(produto.descricao) < 1}"> - </c:if>${produto.descricao}</td>
                        <td class="text-center">
                            <a class="js-btn-delete-produto text-danger"
                               href="#"
                               data-produto-id="${produto.id}"
                               data-produto-descricao="${produto.descricao}"
                               ><span data-feather="trash-2"></span></a>
                        </td>
                        <td class="text-center">
                            <a class="js-btn-edit-produto text-info"
                               href="/produto/${produto.id}"><span data-feather="edit"></span></a>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <th scope="row"> -</th>
                <td> - </td>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

</tp:table_dark>
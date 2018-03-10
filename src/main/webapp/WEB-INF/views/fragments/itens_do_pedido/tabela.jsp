<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tp" tagdir="/WEB-INF/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
    <h2 class="col text-center">Itens do Pedido</h2>
</div>

<tp:table_dark>
    <jsp:attribute name="header">
        <th scope="col">#</th>
        <th scope="col">Produto</th>
        <th scope="col">Quantidade</th>
        <th scope="col"></th>
    </jsp:attribute>

    <jsp:attribute name="content">
        <c:choose>
            <c:when test="${func:length(pedido.itemDoPedidoCollection) gt 0}">
                <c:forEach var="index" begin="0" end="${func:length(pedido.itemDoPedidoCollection) - 1 }">
                    <c:set var="idPedido" value="${pedido.itemDoPedidoCollection[index].itemDoPedidoPK.idPedido}"/>
                    <c:set var="idProduto" value="${pedido.itemDoPedidoCollection[index].itemDoPedidoPK.idProduto}"/>

                    <tr>
                        <th scope="row" class="text-center">
                            PD${idPedido}PT${idProduto}
                        </th>

                        <td >${pedido.itemDoPedidoCollection[index].produto.descricao}</td>

                        <td class="text-center">${pedido.itemDoPedidoCollection[index].qtdade}</td>

                        <td class="text-center">
                            <a href="/pedido/${idPedido}/produto/${idProduto}/item"><span data-feather="trash-2"></span></a>
                            <a href="/pedido/${idPedido}/produto/${idProduto}/item"><span data-feather="edit"></span></a>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <th scope="row"> -</th>
                <td> -</td>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

</tp:table_dark>
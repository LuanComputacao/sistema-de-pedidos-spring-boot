<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <div class="col">
        <form id="item-do-pedido-form"
              action="<c:url value="/api/v1/clientes/${pedido.cliente.id}/pedidos/${pedido.id}/itens"/>"
              method="post">

            <input type="hidden" name="id" id="pedido-id" data-value="${pedido.id}">
            <input type="hidden" name="id" id="cliente-id" data-value="${pedido.cliente.id} ">

            <div class="form-group">
                <label for="produto-id" class="sr-only">Cliente</label>

                <c:choose>
                    <c:when test="${criar}">
                        <select id="produto-id" class="form-control" name="pedido-cliente">
                            <option> Selecione um produto</option>
                            <c:forEach var="produto" items="${produtos}">
                                <option value="${produto.id}" <c:if test="${true}">selected="true"</c:if>>
                                        ${produto.descricao}
                                </option>

                            </c:forEach>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <input id="produto-id" type="hidden" data-value="${itemDoPedido.produto.id}">
                        <b>Produto:</b> ${itemDoPedido.produto.descricao}
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="form-group">
                <label for="produto-qtdade" class="sr-only">Quantidade:</label>
                <input id="produto-qtdade"
                       class="form-control"
                       type="number"
                       min="1"
                       name="quantidade"
                <c:choose>
                       <c:when test="${editar}">data-value="${itemDoPedido.qtdade}"</c:when>
                       <c:otherwise>value="1"</c:otherwise>
                </c:choose>
                >
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">
                    <c:choose>
                        <c:when test="${criar}">
                            Adicionar
                        </c:when>
                        <c:otherwise>
                            Salvar
                        </c:otherwise>
                    </c:choose>
                </button>
            </div>
        </form>
    </div>
</div>
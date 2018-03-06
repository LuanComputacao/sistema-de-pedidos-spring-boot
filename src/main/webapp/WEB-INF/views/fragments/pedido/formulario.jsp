<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <div class="col">

        <form id="pedido-form" action="/api/v1/pedidos/" method="post">
            <input type="hidden" name="id" id="pedido-id"
                   <c:if test="${editar}">data-value="${pedido.id}"</c:if>>

            <div class="form-group">
                <label for="pedido-cliente" class="sr-only">Cliente</label>
                <select id="pedido-cliente" class="form-control" name="pedido-cliente" >
                    <option> Selecione um Cliente</option>
                    <c:forEach var="cliente" items="${clientes}">
                        <option
                                value="${cliente.id}"
                                <c:if test="${pedido.cliente.id == cliente.id}">selected="true"</c:if>
                        >
                                ${cliente.cpf}: ${cliente.nome} ${cliente.sobrenome}
                        </option>

                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="pedido-data" class="sr-only">Data do Pedido:</label>
                <input id="pedido-data" class="form-control" type="date" name="dataPedido" placeholder="Descrição"
                       <c:if test="${editar}">data-value="${pedido.dataPedido}"</c:if>>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </form>
    </div>
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <div class="col">

        <form id="pedido-form" action="<c:url value="/api/v1/pedidos/"/>" method="post">
            <input type="hidden" name="id" id="pedido-id"
                   <c:if test="${editar}">data-value="${pedido.id}"</c:if>>

            <div class="form-group">
                <label for="pedido-descricao" class="sr-only">Descrição:</label>
                <input class="form-control" type="text" name="descricao" id="pedido-descricao" placeholder="Descrição"
                       <c:if test="${editar}">data-value="${pedido.descricao}"</c:if>>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </form>
    </div>
</div>



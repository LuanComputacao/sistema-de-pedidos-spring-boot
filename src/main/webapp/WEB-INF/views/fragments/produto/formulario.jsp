<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tp" tagdir="/WEB-INF/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
    <div class="col">

        <form id="produto-form" action="/api/v1/produtos/" method="post">
            <input type="hidden" name="id" id="produto-id"
                   <c:if test="${editar}">data-value="${produto.id}"</c:if>>

            <div class="form-group">
                <label for="produto-descricao" class="sr-only">Descrição:</label>
                <input class="form-control"
                       type="text"
                       name="descricao"
                       id="produto-descricao"
                       placeholder="Descrição"
                       <c:if test="${editar}">data-value="${produto.descricao}"</c:if>>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </form>
    </div>
</div>



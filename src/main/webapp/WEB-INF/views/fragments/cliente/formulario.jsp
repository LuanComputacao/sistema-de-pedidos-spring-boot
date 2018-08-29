<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <div class="col">

        <form id="cliente-form" action="/api/v1/clientes/" method="post">
            <input type="hidden" name="id" id="cliente-id"
                   <c:if test="${editar}">data-value="${cliente.id}"</c:if>>

            <div class="form-group">
                <label for="cliente-cpf" class="sr-only">CPF:</label>
                <input class="form-control" type="text" name="cpf" id="cliente-cpf" placeholder="CPF"
                       <c:if test="${editar}">data-value="${cliente.cpf}"</c:if>>
            </div>

            <div class="form-group">
                <label for="cliente-nome" class="sr-only">Nome:</label>
                <input class="form-control" type="text" name="nome" id="cliente-nome" placeholder="Nome"
                       <c:if test="${editar}">data-value="${cliente.nome}"</c:if>>
            </div>

            <div class="form-group">
                <label for="cliente-sobrenome" class="sr-only">Sobrenome</label>
                <input class="form-control" type="text" name="sobrenome" id="cliente-sobrenome" placeholder="Sobrenome"
                       <c:if test="${editar}">data-value="${cliente.sobrenome}"</c:if>>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </form>
    </div>
</div>



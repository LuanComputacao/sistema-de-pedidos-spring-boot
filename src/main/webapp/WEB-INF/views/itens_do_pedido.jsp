<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tp" tagdir="/WEB-INF/tags" %>

<tp:base>
    <jsp:attribute name="navbar">pedidos</jsp:attribute>

    <jsp:attribute name="title">
        Itens do Pedido
    </jsp:attribute>
    <jsp:attribute name="subtitle">
        Lista de Itens do Pedido
    </jsp:attribute>


    <jsp:attribute name="btnToolbar">
        <div class="btn-group mr-2">
            <c:if test="${listar}">
                <a href="<c:url value="/pedido/${pedido.id}/item"/>" class="btn btn-sm btn-outline-primary">Adicionar Item</a>
                <a href="<c:url value="/pedidos"/>" class="btn btn-sm btn-outline-info">Lista de Pedidos</a>
            </c:if>
            <c:if test="${editar || criar}">
                <a href="<c:url value="/pedido/${pedido.id}/itens"/>" class="btn btn-sm btn-outline-primary">Listar Itens do Pedido</a>
            </c:if>
        </div>
    </jsp:attribute>

    <jsp:attribute name="body">
        <c:if test="${editar || criar}">
            <jsp:include page="fragments/itens_do_pedido/formulario.jsp"/>
        </c:if>

        <c:if test="${listar}">
            <jsp:include page="fragments/pedido/dados_do_pedido.jsp"/>
            <jsp:include page="fragments/itens_do_pedido/tabela.jsp"/>
        </c:if>

        <jsp:include page="fragments/itens_do_pedido/item_modal.jsp"/>
    </jsp:attribute>

    <jsp:attribute name="jsFooter">
        <script src="<c:url value="/static/js/item_do_pedido.js"/>"></script>
    </jsp:attribute>

</tp:base>
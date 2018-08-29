<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tp" tagdir="/WEB-INF/tags" %>

<tp:base>
    <jsp:attribute name="navbar">pedidos</jsp:attribute>

    <jsp:attribute name="title">
        Sistema de Pedidos
    </jsp:attribute>
    <jsp:attribute name="subtitle">
        Consulta pedidos
    </jsp:attribute>


    <jsp:attribute name="btnToolbar">
        <div class="btn-group mr-2">
            <c:if test="${listar}">
                <a href="<c:url value="/pedido"/>" class="btn btn-sm btn-outline-primary">Criar Pedido</a>
            </c:if>
            <c:if test="${editar || criar}">
                <a href="<c:url value="/pedidos"/>" class="btn btn-sm btn-outline-primary">Listar</a>
            </c:if>
        </div>
    </jsp:attribute>

    <jsp:attribute name="body">
        <c:if test="${editar || criar}">
            <jsp:include page="fragments/pedido/formulario.jsp"/>
        </c:if>

        <c:if test="${listar}">
            <jsp:include page="fragments/pedido/tabela.jsp"/>
        </c:if>

        <jsp:include page="fragments/pedido/pedido_modal.jsp"/>
    </jsp:attribute>

    <jsp:attribute name="jsFooter">
        <script src="<c:url value="/static/js/pedidos.js"/>"></script>
    </jsp:attribute>

</tp:base>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tp" tagdir="/WEB-INF/tags" %>

<tp:base>

    <jsp:attribute name="navbar">clientes</jsp:attribute>

    <jsp:attribute name="title">
        Sistema de Pedidos
    </jsp:attribute>
    <jsp:attribute name="subtitle">
        Consulta Clientes
    </jsp:attribute>


    <jsp:attribute name="btnToolbar">
        <div class="btn-group mr-2">
            <c:if test="${clientes != null}">
                <a href="/cliente" class="btn btn-sm btn-outline-primary">Adicionar Cliente</a>
            </c:if>
            <c:if test="${clientes == null}">
                <a href="/clientes" class="btn btn-sm btn-outline-primary">Listar</a>
            </c:if>
        </div>
    </jsp:attribute>

    <jsp:attribute name="body">
        <c:if test="${editar || criar}">
            <jsp:include page="fragments/cliente/formulario.jsp"/>
        </c:if>


        <c:if test="${listar}">
            <jsp:include page="fragments/cliente/tabela.jsp"/>
        </c:if>

        <jsp:include page="fragments/cliente/cliente_modal.jsp"/>
    </jsp:attribute>

    <jsp:attribute name="jsFooter">
        <script src="/static/js/clientes.js"></script>
    </jsp:attribute>

</tp:base>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tp" tagdir="/WEB-INF/tags" %>

<tp:base>
    <jsp:attribute name="navbar">produtos</jsp:attribute>

    <jsp:attribute name="title">
        Sistema de Pedidos
    </jsp:attribute>
    <jsp:attribute name="subtitle">
        Consulta produtos
    </jsp:attribute>


    <jsp:attribute name="btnToolbar">
        <div class="btn-group mr-2">
            <c:if test="${listar}">
                <a href="/produto" class="btn btn-sm btn-outline-primary">Adicionar Produto</a>
            </c:if>
            <c:if test="${editar || criar}">
                <a href="/produtos" class="btn btn-sm btn-outline-primary">Listar</a>
            </c:if>
        </div>
    </jsp:attribute>

    <jsp:attribute name="body">
        <c:if test="${editar || criar}">
            <jsp:include page="fragments/produto/formulario.jsp"/>
        </c:if>


        <c:if test="${listar != null}">
            <jsp:include page="fragments/produto/tabela.jsp"/>
        </c:if>

        <jsp:include page="fragments/produto/produto_modal.jsp"/>
    </jsp:attribute>

    <jsp:attribute name="jsFooter">
        <script src="<c:url value="/static/js/produtos.js"/>"></script>
    </jsp:attribute>

</tp:base>
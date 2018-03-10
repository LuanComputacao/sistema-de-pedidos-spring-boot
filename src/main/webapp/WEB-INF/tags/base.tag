<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Página Generica" pageEncoding="utf-8" %>

<%--Assets--%>
<%@attribute name="cssLinks" fragment="true" %>
<%@attribute name="jsFooter" fragment="true" %>

<%--Contents--%>
<%@attribute name="title" fragment="true" %>
<%@attribute name="subtitle" fragment="true" %>
<%@attribute name="navbar" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="body" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="btnToolbar" fragment="true" %>

<html lang="pt-br">
<head>

    <title>
        <jsp:invoke fragment="title"/>
    </title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="UTF-8">
    <link href="<c:url value="/webjars/bootstrap/4.0.0/css/bootstrap.min.css"/>" rel="stylesheet" media="screen"/>
    <link href="<c:url value="/static/css/dashboard.css"/>" rel="stylesheet" media="screen">

    <jsp:invoke fragment="cssLinks"/>

</head>


<body>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 navbar-top">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="<c:url value="/"/>">
        <jsp:invoke fragment="title"/>
    </a>
    <div class="col-md-10 navbar-top__title">
        <h1 class="h2"><jsp:invoke fragment="subtitle"/></h1>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav id="js-sidebar" class="col-md-2 d-none d-md-block bg-light sidebar" data-navbar="<jsp:invoke fragment="navbar"/>">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link js-clientes" href="<c:url value="/clientes"/>"><span data-feather="users"></span>Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-produtos" href="<c:url value="/produtos"/>"><span data-feather="shopping-bag"></span>Produtos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link js-pedidos" href="<c:url value="/pedidos"/>"><span data-feather="list"></span>Pedidos</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4" id="js-main">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
            <div class="btn-toolbar mb-2 mb-md-0">
                <jsp:invoke fragment="btnToolbar"/>
            </div>
        </div>

        <div class="row">
            <div class="col" id="">
                <jsp:invoke fragment="body"/>
            </div>
        </div>

        <div class="row">
            <jsp:invoke fragment="footer"/>
        </div>
    </main>
</div>





<script src="<c:url value="/webjars/jquery/3.3.1/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/base.js"/>"></script>
<script src="<c:url value="/static/js/sidebar.js"/>"></script>

<script src="<c:url value="/webjars/popper.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/node_modules/feather-icons/dist/feather.min.js"/>"></script>

<jsp:invoke fragment="jsFooter"/>
</body>
</html>
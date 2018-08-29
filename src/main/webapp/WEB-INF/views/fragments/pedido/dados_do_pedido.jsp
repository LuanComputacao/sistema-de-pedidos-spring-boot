<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <h2 class="col text-center">Pedido</h2>
</div>
<div class="row">
    <div class="col-3">ID:</div><div class="col-9">${pedido.id}</div>
</div>

<div class="row">
    <h2 class="col text-center">Cliente</h2>
</div>
<div class="row">
    <div class="col-3">CPF:</div><div class="col-9">${pedido.cliente.cpf}</div>
</div>
<div class="row">
    <div class="col-3">Nome:</div><div class="col-9">${pedido.cliente.nome}</div>
</div>
<div class="row">
    <div class="col-3">Sobrenome:</div><div class="col-9">${pedido.cliente.sobrenome}</div>
</div>
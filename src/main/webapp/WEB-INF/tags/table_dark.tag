<%@tag description="Tabela escura" pageEncoding="utf-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="content" fragment="true" %>

<div class="row">
    <div class="col">
        <table class="table table-dark table-bordered">
            <thead class="theaad-dark text-center table-striped">
            <tr>
                <jsp:invoke fragment="header"/>
            </tr>
            </thead>
            <tbody>
            <tr>
                <jsp:invoke fragment="content"/>
            </tr>
            </tbody>
        </table>
    </div>
</div>
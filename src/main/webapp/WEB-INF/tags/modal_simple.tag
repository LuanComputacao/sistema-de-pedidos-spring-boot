<%@tag description="Modal simples" pageEncoding="utf-8" %>

<%@attribute name="id" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="body" fragment="true" %>
<%@attribute name="footer" fragment="true" %>


<div class="modal fade"
     id="<jsp:invoke fragment="id"/>"
     tabindex="-1"
     role="dialog"
     aria-labelledby="exampleModalLabel"
     aria-hidden="true">

    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <div class="js-modal-title">
                    <jsp:invoke fragment="title"/>
                </div>

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body js-modal-body"><jsp:invoke fragment="body"/></div>
            <div class="modal-footer"><jsp:invoke fragment="footer"/></div>
        </div>
    </div>
</div>



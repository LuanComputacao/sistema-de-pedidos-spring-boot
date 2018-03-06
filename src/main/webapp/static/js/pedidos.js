/**
 * Created by luancomputacao on 25/02/18.
 */
$(function () {
    Produto.run();
});


Produto = {

    component: $("<div class='js-produto'></div>").html('\
    \<div>\
    \   <b>ID</b>: <span class="js-produto-id"></span><br> \
    \   <b>Descrição</b>: <span class="js-produto-descricao"></span><br> \
    \</div> \
    '),

    elements: {
        form: {
            _: $("#produto-form"),
            id: $("#produto-id"),
            descricao: $("#produto-descricao"),
        },
        modal: {
            _: $('#produto-modal'),
            title: $('#produto-modal').find('.js-modal-title'),
            body: $('#produto-modal').find('.js-modal-body')

        }
    },

    models: {
        id: null,
        descricao: null,
    },

    run: function () {
        console.log("Produto started");
        this.init();

        console.log("Calling watchers");
        this.watchers.form();

        console.log(this.models);
    },

    init: function () {
        this.models.id = this.elements.form.id.val() || this.elements.form.id.data('value') || null;
        this.models.descricao = this.elements.form.descricao.val() || this.elements.form.descricao.data('value');


        this.elements.form.id.val(this.models.id);
        this.elements.form.descricao.val(this.models.descricao);
    },

    watchers: {
        form: function () {
            var form = Produto.elements.form;
            form._.submit(function (event) {
                event.preventDefault();
                Produto.methods.save();
            });

            form.descricao.keyup(function (e) {
                Produto.models.descricao = form.descricao.val();
            });
        }
    },

    methods: {
        save: function () {
            $.ajax({
                url: Produto.elements.form._.attr('action'),
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify({
                    id: Produto.models.id,
                    descricao: Produto.models.descricao,
                }),
                statusCode: {
                    200: function (data) {
                        Produto.methods.fill(data);
                        Produto.methods.updated();
                    },
                    201: function (data) {
                        Produto.methods.fill(data);
                        Produto.methods.created();
                    },
                    404: function (data) {
                        Produto.methods.web404(data);
                    },
                    409: function (data) {
                        Produto.methods.conflict(data);
                    }
                }
            });
        },

        fill: function (dataProduto) {
            Produto.models.id = dataProduto.id;
            Produto.models.descricao = dataProduto.descricao;
        },

        fillComponent: function () {
            var component = Produto.component;
            component.find('.js-produto-id').text(Produto.models.id);
            component.find('.js-produto-descricao').text(Produto.models.descricao);
        },

        updated: function () {
            Produto.methods.fillComponent();
            Produto.elements.form.descricao.removeClass('is-invalid');

            Produto.elements.modal.title.text("ATUALIZADO COM SUCESSO");
            Produto.elements.modal.body.html(Produto.component);
            Produto.elements.modal._.modal();
        },

        created: function (data) {
            Produto.methods.fillComponent();
            Produto.methods.clearForm();
            Produto.elements.form.descricao.removeClass('is-invalid');
            Produto.elements.modal.title.text("CRIADO COM SUCESSO");
            Produto.elements.modal.body.html(Produto.component);
            Produto.elements.modal._.modal();

        },

        conflict: function () {
            Produto.elements.modal.title.text("CONFLITO");
            Produto.elements.modal.body.text("O produto já existe");
            Produto.elements.form.descricao.addClass('is-invalid');
            Produto.elements.modal._.modal();
        },

        clearForm: function () {
            Produto.elements.form.id.val('');
            Produto.elements.form.descricao.val('');
        },

        web404: function (data) {
            console.log(data);
            Produto.elements.modal.title.text("404: Not Found");
            Produto.elements.modal.body.html("<p>O End Point " + data.responseJSON.path + " não foi encontrado</p>")
            Produto.elements.modal._.modal();

        }
    }
};
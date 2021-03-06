/**
 * Created by luancomputacao on 25/02/18.
 */
$(function () {
    Cliente.run();
});


Cliente = {

    component: $("<div class='js-cliente'></div>").html(''
        .concat('<div>')
        .concat('   <b>ID</b>: <span class="js-cliente-id"></span><br> ')
        .concat('   <b>CPF</b>: <span class="js-cliente-cpf"></span><br> ')
        .concat('   <b>Nome</b>: <span class="js-cliente-nome"></span><br> ')
        .concat('   <b>Sobrenome</b>: <span class="js-cliente-sobrenome"></span>')
        .concat('</div> ')
    ),

    elements: {
        form: {
            _: $("#cliente-form"),
            id: $("#cliente-id"),
            cpf: $("#cliente-cpf"),
            nome: $("#cliente-nome"),
            sobrenome: $("#cliente-sobrenome")
        },
        modal: {
            _: $('#cliente-modal'),
            title: $('#cliente-modal').find('.js-modal-title'),
            body: $('#cliente-modal').find('.js-modal-body')

        },
        buttons: {
            deleteCliente: $(".js-btn-delete-cliente"),
            editCliente: $(".js-btn-edit-cliente"),
        },
    },

    models: {
        id: null,
        cpf: null,
        nome: null,
        sobrenome: null,
        urls: {
            deleteOne: '/api/v1/clientes/'
        }
    },

    run: function () {
        console.log("Cliente started");
        this.init();

        console.log("Calling watchers");
        console.log("-- form");
        this.watchers.form();
        console.log("-- table");
        this.watchers.table();

        console.log(this.models);
    },

    init: function () {
        // Load the values if the form was filled by the system, or browser on load
        this.models.id = this.elements.form.id.val() || this.elements.form.id.data('value') || null;
        this.models.cpf = this.elements.form.cpf.val() || this.elements.form.cpf.data('value');
        this.models.nome = this.elements.form.nome.val() || this.elements.form.nome.data('value');
        this.models.sobrenome = this.elements.form.sobrenome.val() || this.elements.form.sobrenome.data('value');

        // Refill the form
        this.elements.form.id.val(this.models.id);
        this.elements.form.cpf.val(this.models.cpf);
        this.elements.form.nome.val(this.models.nome);
        this.elements.form.sobrenome.val(this.models.sobrenome);
    },

    watchers: {
        form: function () {
            var form = Cliente.elements.form;
            form._.submit(function (event) {
                event.preventDefault();
                Cliente.methods.fill({
                    "id": form.id.val(),
                    "cpf": form.cpf.val(),
                    "nome": form.nome.val(),
                    "sobrenome": form.sobrenome.val()
                });
                Cliente.methods.save();
                return false;
            });

            form.cpf.keyup(function (e) {
                Cliente.models.cpf = form.cpf.val();
            });
            form.nome.keyup(function (e) {
                Cliente.models.nome = form.nome.val();
            });
            form.sobrenome.keyup(function (e) {
                Cliente.models.sobrenome = form.sobrenome.val();
            });
        },
        table: function () {
            $(Cliente.elements.buttons.deleteCliente).on('click', function (e) {
                e.preventDefault();
                Cliente.methods.deleteOne(this);
                return false;
            });
        }
    },

    methods: {
        save: function () {
            var estaEditando = $.isNumeric(Cliente.models.id);
            var method = (estaEditando) ? "PUT" : "POST";
            var ajaxUrl = Cliente.elements.form._.attr('action');
            $.ajax({
                url: estaEditando ? ajaxUrl.concat(Cliente.models.id) : ajaxUrl,
                method: method,
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify({
                    id: Cliente.models.id,
                    cpf: Cliente.models.cpf,
                    nome: Cliente.models.nome,
                    sobrenome: Cliente.models.sobrenome
                }),
                statusCode: {
                    200: function (data) {
                        Cliente.methods.fill(data);
                        Cliente.methods.updated();
                    },
                    201: function (data) {
                        Cliente.methods.fill(data);
                        Cliente.methods.created();
                    },
                    304: function () {
                        Cliente.methods.notModified()
                    },
                    409: function () {
                        Cliente.methods.conflict()
                    }
                }
            });
        },

        fill: function (dataCliente) {
            Cliente.models.id = dataCliente.id;
            Cliente.models.cpf = dataCliente.cpf;
            Cliente.models.nome = dataCliente.nome;
            Cliente.models.sobrenome = dataCliente.sobrenome;
        },

        fillComponent: function () {
            var component = Cliente.component;
            component.find('.js-cliente-id').text(Cliente.models.id);
            component.find('.js-cliente-cpf').text(Cliente.models.cpf);
            component.find('.js-cliente-nome').text(Cliente.models.nome);
            component.find('.js-cliente-sobrenome').text(Cliente.models.sobrenome);
        },

        updated: function () {
            Cliente.methods.fillComponent();
            Cliente.elements.form.cpf.removeClass('is-invalid');

            Cliente.elements.modal.title.text("ATUALIZADO COM SUCESSO");
            Cliente.elements.modal.body.html(Cliente.component);
            Cliente.elements.modal._.modal();
        },

        created: function (data) {
            Cliente.methods.fillComponent();

            Cliente.methods.clearForm();
            Cliente.elements.form.cpf.removeClass('is-invalid');

            Cliente.elements.modal.title.text("CRIADO COM SUCESSO");
            Cliente.elements.modal.body.html(Cliente.component);
            Cliente.elements.modal._.modal();

        },

        conflict: function () {
            Cliente.elements.modal.title.text("CONFLITO");
            Cliente.elements.modal.body.text("O cliente já existe");
            Cliente.elements.form.cpf.addClass('is-invalid');
            Cliente.elements.modal._.modal();
        },

        notModified: function () {
            Cliente.elements.modal.title.text("NÃO MODIFICADO");
            Cliente.elements.modal.body.text("Os dados estão errados ou o CPF já existe");
            Cliente.elements.form.cpf.addClass('is-invalid');
            Cliente.elements.modal._.modal();
        },

        clearForm: function () {
            Cliente.elements.form.id.val('');
            Cliente.elements.form.cpf.val('');
            Cliente.elements.form.nome.val('');
            Cliente.elements.form.sobrenome.val('');
        },

        deleteOne: function (clickedBtn) {
            console.log(clickedBtn);
            Cliente.methods.fill({
                "id": $(clickedBtn).data("cliente-id"),
                "cpf": $(clickedBtn).data("cliente-cpf"),
                "nome": $(clickedBtn).data("cliente-nome"),
                "sobrenome": $(clickedBtn).data("cliente-sobrenome")
            });


            $.ajax({
                url: Cliente.models.urls.deleteOne.concat(Cliente.models.id),
                method: "DELETE",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify({
                    id: Cliente.models.id,
                    cpf: Cliente.models.cpf,
                    nome: Cliente.models.nome,
                    sobrenome: Cliente.models.sobrenome
                }),
                statusCode:{
                    202: function () {
                        Cliente.methods.deteleted();
                    }
                }
            })
        },

        deteleted: function () {
            Cliente.methods.fillComponent();
            Cliente.elements.modal.title.text("DELETADO COM SUCESSO");
            Cliente.elements.modal.body.html(Cliente.component);
            Cliente.elements.modal._.modal();
            $('#cliente-' + Cliente.models.id).hide()
        }
    }
};
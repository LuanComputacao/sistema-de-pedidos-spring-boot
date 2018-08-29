/**
 * Created by luancomputacao on 25/02/18.
 */
$(function () {
    ItemDoPedido.run();
});


ItemDoPedido = {

    component: $("<div class='js-pedido'></div>").html('\
    \<div>\
    \   <b>ID</b>:      <span class="js-item-do-pedido-id"></span><br> \
    \   <b>Cliente</b>: <span class="js-item-do-pedido-cliente"></span><br> \
    \   <b>Pedido</b>:    <span class="js-item-do-pedido-pedido"></span><br> \
    \   <b>Quantidade</b>:    <span class="js-item-do-pedido-quantidade"></span><br> \
    \</div> \
    '),

    elements: {
        form: {
            _: $("#item-do-pedido-form"),
            clienteID: $("#cliente-id"),
            pedidoID: $("#pedido-id"),
            produtoID: $("#produto-id"),
            produtoQtdade: $("#produto-qtdade"),
        },
        modal: {
            _: $('#item-do-pedido-modal'),
            title: $('#item-do-pedido-modal').find('.js-modal-title'),
            body: $('#item-do-pedido-modal').find('.js-modal-body')

        }
    },

    models: {
        id: {produtoId: null, pedidoId: null},
        cliente: {id: null, cfp: null, nome: null, sobrenome: null},
        produto: {id: null, descricao: null},
        pedido: {id: null, dataPedido: null},
        qtdade: null
    },

    run: function () {
        console.log("IteM do Pedido started");
        this.init();

        console.log("Calling watchers");
        this.watchers.form();

        console.log(this.models);
    },

    init: function () {
        this.models.id.pedidoId = this.elements.form.pedidoID.val() || this.elements.form.pedidoID.data('value');
        this.models.id.produtoId = this.elements.form.produtoID.val() || this.elements.form.produtoID.data('value');
        this.models.qtdade = this.elements.form.produtoQtdade.val() || this.elements.form.produtoQtdade.data('value');

        this.elements.form.pedidoID.val(this.models.id.pedidoId);
        this.elements.form.produtoID.val(this.models.id.produtoId);
        this.elements.form.produtoQtdade.val(this.models.qtdade);
    },

    watchers: {
        form: function () {
            var form = ItemDoPedido.elements.form;
            form._.submit(function (event) {
                event.preventDefault();
                ItemDoPedido.methods.save();
            });

            form.produtoID.on("change", function (e) {
                ItemDoPedido.models.id.produtoId = form.produtoID.val();
            });
            form.produtoQtdade.on("change", function (e) {
                ItemDoPedido.models.qtdade = form.produtoQtdade.val();
            });
        }
    },

    methods: {
        save: function () {
            $.ajax({
                url: ItemDoPedido.elements.form._.attr('action'),
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify({
                    produtoId: ItemDoPedido.models.id.produtoId,
                    qtdade: ItemDoPedido.models.qtdade
                }),
                statusCode: {
                    200: function (data) {
                        ItemDoPedido.methods.fill(data);
                        ItemDoPedido.methods.updated();
                    },
                    201: function (data) {
                        ItemDoPedido.methods.fill(data);
                        ItemDoPedido.methods.created();
                    },
                    404: function (data) {
                        ItemDoPedido.methods.web404(data);
                    },
                    409: function (data) {
                        ItemDoPedido.methods.conflict(data);
                    }
                }
            });
        },

        fill: function (dataItemDoPedido) {
            ItemDoPedido.models.cliente = dataItemDoPedido.pedido.cliente;
            ItemDoPedido.models.produto = dataItemDoPedido.produto;
            ItemDoPedido.models.pedido = dataItemDoPedido.pedido;
            ItemDoPedido.models.qtdade = dataItemDoPedido.qtdade;

            ItemDoPedido.models.id.produtoId = ItemDoPedido.models.produto.id;
            ItemDoPedido.models.id.pedidoId = ItemDoPedido.models.pedido.id;
        },

        fillComponent: function () {
            var component = ItemDoPedido.component;
            component.find('.js-item-do-pedido-id').text(ItemDoPedido.methods.getCode());
            component.find('.js-item-do-pedido-cliente').text(ItemDoPedido.methods.getCliente());
            component.find('.js-item-do-pedido-pedido').text(ItemDoPedido.methods.getPedido());
            component.find('.js-item-do-pedido-quantidade').text(ItemDoPedido.models.qtdade);
        },

        updated: function () {
            ItemDoPedido.methods.fillComponent();
            ItemDoPedido.elements.modal.title.text("ATUALIZADO COM SUCESSO");
            ItemDoPedido.elements.modal.body.html(ItemDoPedido.component);
            ItemDoPedido.elements.modal._.modal();
        },

        created: function (data) {
            ItemDoPedido.methods.fillComponent();
            ItemDoPedido.methods.clearForm();

            ItemDoPedido.elements.modal.title.text("CRIADO COM SUCESSO");
            ItemDoPedido.elements.modal.body.html(ItemDoPedido.component);
            ItemDoPedido.elements.modal._.modal();

        },

        conflict: function () {
            ItemDoPedido.elements.modal.title.text("CONFLITO");
            ItemDoPedido.elements.modal.body.text("O item do pedido não foi encontrado");
            ItemDoPedido.elements.modal._.modal();
        },

        clearForm: function () {
            ItemDoPedido.elements.form.produtoID.val('');
            ItemDoPedido.elements.form.produtoQtdade.val('');
        },

        web404: function (data) {
            console.log(data);
            ItemDoPedido.elements.modal.title.text("404: Not Found");
            ItemDoPedido.elements.modal.body.html("<p>O End Point " + data.responseJSON.path + " não foi encontrado</p>")
            ItemDoPedido.elements.modal._.modal();

        },

        getCode: function () {
            return "PD" + ItemDoPedido.models.id.pedidoId + "PT" + ItemDoPedido.models.id.produtoId;
        },

        getCliente: function () {
            return "[" + ItemDoPedido.models.cliente.cpf + "] - " + ItemDoPedido.models.cliente.nome + " " + ItemDoPedido.models.cliente.sobrenome;
        },

        getPedido: function () {
            var dataPedido = new Date(ItemDoPedido.models.pedido.dataPedido).toLocaleDateString();
            return "[" + ItemDoPedido.models.pedido.id + "] - " + dataPedido;
        }
    }
};
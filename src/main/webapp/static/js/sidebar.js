var Sidebar = {
    component:{},
    elements: {
        nav:{
            _: $('#js-sidebar'),
            item: $('.nav-item')
        }
    },

    models:{
        activePage: null
    },

    /**
     * Call init function and the watchers
     */
    run: function () {
        console.log("Sidebar started");
        this.init();
    },

    /**
     * Initialize the component
     */
    init: function () {
        this.models.activePage = this.elements.nav._.data('navbar');
        this.methods.setActive();

        console.log(this.models);
    },

    /**
     * Run watchers to perform modification as response for user events
     */
    watchers: {

    },
    methods:{
        /**
         * Get the data-navbar and set the active nav-item that have the regex `js-[a-z]+`
         */
        setActive: function () {
            Sidebar.elements.nav.item.find('.js-' + Sidebar.models.activePage).addClass('active')
        }
    }
};
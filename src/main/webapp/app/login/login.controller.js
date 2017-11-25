//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'consultar' do modulo 'login'
    angular.module('login').controller(
            'LoginController',
            LoginController);

    // Definindo atributos e operacoes do Controlador da tela 'consultar' do modulo 'login'
    /* @ngInject */
    function LoginController($controller, $scope, $state,
    		LoginData, MsgCenter) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.msgs = "";

        vm.filtros = {};
        //vm.clientes = [];
        //vm.cliente = {};

        // Paginação

        vm.pagesize = 0;
        vm.currentpage = 0;


        // Operacoes acessiveis no html

        vm.irParaTelaInclusao = irParaTelaInclusao;
        vm.irParaTelaDetalhamento = irParaTelaDetalhamento;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
            //vm.deveRestaurar = FiltroService.deveRestaurar();
            //restaurarEstadoTela();
        	irParaTelaLogin()
        }

        function irParaTelaLogin() {
            //salvarEstadoTela();			
            $state.go('login', {
   
            });
        }
        function irParaTelaDetalhamento(codCliente) {
            //salvarEstadoTela();			
            $state.go('clienteDetalhar', {
                'codCliente': codCliente
            });
        }

        function irParaTelaInclusao() {
            //salvarEstadoTela();
            $state.go('clienteEditar');
        }

        function salvarEstadoTela() {
            var devePesquisar = vm.clientes.length > 0;
            //FiltroService.salvarFiltros(vm.filtros, devePesquisar);
        }

        function restaurarEstadoTela() {
            /*if (FiltroService.deveRestaurar()) {
             vm.filtros = FiltroService.obterFiltros();
             
             if (FiltroService.devePesquisar()) {
             pesquisar();
             }
             FiltroService.marcarRestaurado();
             }*/
        }

        function pesquisar() {
        	MsgCenter.clear();
            var filtros = vm.filtros;

            ClienteData.listar(filtros).then(function (data) {
                vm.clientes = data.entidades;

                if (data.pagina) {
                    var page = data.pagina;
                    vm.currentpage = page.currentPage + 1;
                    vm.pagesize = page.pageSize;
                    vm.totalresults = page.totalResults;
                }
                if (data.mensagens) {
                	MsgCenter.addMessages(data.mensagens);  
                }
                
            });
        }
    }

})();


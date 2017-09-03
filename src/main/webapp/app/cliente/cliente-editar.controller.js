//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'editar' do modulo 'cliente'
    angular.module('cliente').controller(
            'EditarClienteController',
            EditarClienteController);


    // Definindo atributos e operacoes do Controlador da tela 'editar' do modulo 'cliente'
    /* @ngInject */
    function EditarClienteController($controller, $scope, $state, $stateParams,
            ClienteData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.cliente = {};
        vm.cliente.id = $stateParams.id;

        vm.msgs = "";

        vm.limparFormulario = limparFormulario;
        vm.irParaTelaConsultar = irParaTelaConsultar;
        vm.incluir = incluir;
        vm.alterar = alterar;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
            if (vm.cliente.id !== undefined) {
                obter();
            }
        }

        function recarregarTela() {
            $state.reload();
        }

        function irParaTelaConsultar() {
            $state.go('clienteConsultar');
        }
        
        function incluir() {

            vm.cliente.id = undefined;

            ClienteData.salvar(vm.cliente).then(function (data) {
                vm.msgs = "Cliente incluído com sucesso!";
                limparFormulario();
            });
        }
        
        function limparFormulario() {
        	vm.cliente = {};
        }

        function alterar() {

            ClienteData.salvar(vm.cliente).then(function (data) {
                vm.msgs = "Cliente salvo com sucesso!";
                $state.go('clienteDetalhar', {
                    'id': vm.cliente.id
                });
            });
        }

        function obter() {
            ClienteData.obter(vm.cliente.id).then(function (data) {
                vm.cliente = data.plain();
            });
        }
    }

})();
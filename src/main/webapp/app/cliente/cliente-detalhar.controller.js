//-----------------------------------------------------
// CONTROLADOR DETALHAMENTO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'detalhar' do modulo 'cliente'
    angular.module('cliente').controller(
            'DetalharClienteController',
            DetalharClienteController);

    // Definindo atributos e operacoes do Controlador da tela 'detalhar' do modulo 'cliente'
    /* @ngInject */
    function DetalharClienteController($controller, $scope, $state, $stateParams,
            ClienteData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.cliente = {};
        vm.cliente.codCliente = $stateParams.codCliente;

        vm.msgs = "";

        vm.voltarParaTelaAnterior = voltarParaTelaAnterior;
        vm.irParaTelaEdicao = irParaTelaEdicao;
        vm.excluir = excluir;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
            obter();
        }

        function limpar() {
            $state.reload();
        }

        function voltarParaTelaAnterior() {
            $state.go('clienteConsultar');
        }

        function irParaTelaEdicao(codCliente) {
            $state.go('clienteEditar', {
                'codCliente': codCliente
            });
        }

        function obter() {
            ClienteData.obter(vm.cliente.codCliente).then(function (data) {
                vm.cliente = data.plain();
            });
        }
        
        function excluir() {
            ClienteData.excluir(vm.cliente.codCliente).then(function (data) {                
                $state.go('clienteConsultar');
            });
        }

    }

})();
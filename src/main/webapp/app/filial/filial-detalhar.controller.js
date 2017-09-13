//-----------------------------------------------------
// CONTROLADOR DETALHAMENTO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'detalhar' do modulo 'filial'
    angular.module('filial').controller(
            'DetalhaFilialController',
            DetalharFilialController);

    // Definindo atributos e operacoes do Controlador da tela 'detalhar' do modulo 'filial'
    /* @ngInject */
    function DetalharFilialController($controller, $scope, $state, $stateParams,
            FilialData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.filial = {};
        vm.filial.codFilial = $stateParams.codFilial;

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
            $state.go('filialConsultar');
        }

        function irParaTelaEdicao(codFilial) {
            $state.go('filialEditar', {
                'codFilial': codFilial
            });
        }

        function obter() {
            FilialData.obter(vm.filial.codFilial).then(function (data) {
                vm.filial = data.plain();
            });
        }
        
        function excluir() {
            FilialData.excluir(vm.filial.codFilial).then(function (data) {                
                $state.go('filialConsultar');
            });
        }

    }

})();
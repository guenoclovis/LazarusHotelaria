//-----------------------------------------------------
// CONTROLADOR DETALHAMENTO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'detalhar' do modulo 'atributo'
    angular.module('atributo').controller(
            'DetalharAtributoController',
            DetalharAtributoController);

    // Definindo atributos e operacoes do Controlador da tela 'detalhar' do modulo 'atributo'
    /* @ngInject */
    function DetalharAtributoController($controller, $scope, $state, $stateParams,
            AtributoData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.atributo = {};
        vm.atributo.codAtributo = $stateParams.codAtributo;

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
            $state.go('atributoConsultar');
        }

        function irParaTelaEdicao(codAtributo) {
            $state.go('atributoEditar', {
                'codAtributo': codAtributo
            });
        }

        function obter() {
            AtributoData.obter(vm.atributo.codAtributo).then(function (data) {
                vm.atributo = data.plain();
            });
        }
        
        function excluir() {
            AtributoData.excluir(vm.atributo.codAtributo).then(function (data) {                
                $state.go('atributoConsultar');
            });
        }

    }

})();
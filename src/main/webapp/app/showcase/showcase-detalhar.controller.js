//-----------------------------------------------------
// CONTROLADOR DETALHAMENTO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'detalhar' do modulo 'showcase'
    angular.module('showcase').controller(
            'DetalharShowcaseController',
            DetalharShowcaseController);

    // Definindo atributos e operacoes do Controlador da tela 'detalhar' do modulo 'showcase'
    /* @ngInject */
    function DetalharShowcaseController($controller, $scope, $state, $stateParams,
            ShowcaseData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.showcase = {};
        vm.showcase.codShowcase = $stateParams.codShowcase;

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
            $state.go('showcaseConsultar');
        }

        function irParaTelaEdicao(codShowcase) {
            $state.go('showcaseEditar', {
                'codShowcase': codShowcase
            });
        }

        function obter() {
            ShowcaseData.obter(vm.showcase.codShowcase).then(function (data) {
                vm.showcase = data.plain();
            });
        }
        
        function excluir() {
            ShowcaseData.excluir(vm.showcase.codShowcase).then(function (data) {                
                $state.go('showcaseConsultar');
            });
        }

    }

})();
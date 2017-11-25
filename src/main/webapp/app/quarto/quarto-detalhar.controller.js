//-----------------------------------------------------
// CONTROLADOR DETALHAMENTO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'detalhar' do modulo 'quarto'
    angular.module('quarto').controller(
            'DetalharQuartoController',
            DetalharQuartoController);

    // Definindo atributos e operacoes do Controlador da tela 'detalhar' do modulo 'quarto'
    /* @ngInject */
    function DetalharQuartoController($controller, $scope, $state, $stateParams, 
    		QuartoData, FilialData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.quarto = {};
        vm.quarto.codQuarto = $stateParams.codQuarto;

        vm.msgs = "";

        vm.voltarParaTelaAnterior = voltarParaTelaAnterior;
        vm.irParaTelaEdicao = irParaTelaEdicao;
        vm.excluir = excluir;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
        	obterQuarto();
        }

        function limpar() {
            $state.reload();
        }

        function voltarParaTelaAnterior() {
            $state.go('quartoConsultar');
        }

        function irParaTelaEdicao(codQuarto) {
            $state.go('quartoEditar', {
                'codQuarto': codQuarto
            });
        }

        function obterQuarto() {
        	QuartoData.obter(vm.quarto.codQuarto).then(function (data) {
                vm.quarto = data.plain();
                
            	FilialData.obter(vm.quarto.codFilial).then(function (data) {
                    vm.filial = data.plain();
                });                
             
            });
        	
        }
        
        function excluir() {
        	QuartoData.excluir(vm.quarto.codQuarto).then(function (data) {                
                $state.go('quartoConsultar');
            });
        }

    }

})();
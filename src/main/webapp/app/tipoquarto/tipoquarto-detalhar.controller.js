//-----------------------------------------------------
// CONTROLADOR DETALHAMENTO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'detalhar' do modulo 'tipoquarto'
    angular.module('tipoquarto').controller(
            'DetalharTipoQuartoController',
            DetalharTipoQuartoController);

    // Definindo tipos de quarto e operacoes do Controlador da tela 'detalhar' do modulo 'tipoquarto'
    /* @ngInject */
    function DetalharTipoQuartoController($controller, $scope, $state, $stateParams,
            TipoQuartoData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.tipoquarto = {};
        vm.tipoquarto.codTipoQuarto = $stateParams.codTipoQuarto;

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
            $state.go('tipoquartoConsultar');
        }

        function irParaTelaEdicao(codTipoQuarto) {
            $state.go('tipoquartoEditar', {
                'codTipoQuarto': codTipoQuarto
            });
        }

        function obter() {
            TipoQuartoData.obter(vm.tipoquarto.codTipoQuarto).then(function (data) {
                vm.tipoquarto = data.plain();
            });
        }
        
        function excluir() {
            TipoQuartoData.excluir(vm.tipoquarto.codTipoQuarto).then(function (data) {                
                $state.go('tipoquartoConsultar');
            });
        }

    }

})();
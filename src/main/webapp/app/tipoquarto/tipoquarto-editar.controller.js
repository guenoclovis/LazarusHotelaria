//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'editar' do modulo 'tipoquarto'
    angular.module('tipoquarto').controller(
            'EditarTipoQuartoController',
            EditarTipoQuartoController);


    // Definindo tipoquartos e operacoes do Controlador da tela 'editar' do modulo 'TipoQuarto'
    /* @ngInject */
    function EditarTipoQuartoController($controller, $scope, $state, $stateParams,
    		TipoQuartoData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.tipoquarto = {};
        vm.tipoquarto.codTipoQuarto = $stateParams.codTipoQuarto;

        vm.msgs = "";

        vm.limparFormulario = limparFormulario;
        vm.irParaTelaConsultar = irParaTelaConsultar;
        vm.incluir = incluir;
        vm.alterar = alterar;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
            if (vm.tipoquarto.codTipoQuarto !== undefined) {
                obter();
            }
        }

        function recarregarTela() {
            $state.reload();
        }

        function irParaTelaConsultar() {
            $state.go('tipoquartoConsultar');
        }
        
        function incluir() {

            vm.tipoquarto.codTipoQuarto = undefined;

            TipoQuartoData.salvar(vm.tipoquarto).then(function (data) {
                vm.msgs = "TipoQuarto incluído com sucesso!";
                limparFormulario();
            });
        }
        
        function limparFormulario() {
        	vm.tipoquarto = {};
        }

        function alterar() {

        	TipoQuartoData.salvar(vm.tipoquarto).then(function (data) {
                vm.msgs = "TipoQuarto salvo com sucesso!";
                $state.go('tipoquartoDetalhar', {
                    'codTipoQuarto': vm.tipoquarto.codTipoQuarto
                });
            });
        }

        function obter() {
        	TipoQuartoData.obter(vm.tipoquarto.codTipoQuarto).then(function (data) {
                vm.tipoquarto = data.plain();
            });
        }
    }

})();
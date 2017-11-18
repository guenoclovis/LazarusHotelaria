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
    		TipoQuartoData, MsgCenter) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.tipoQuarto = {};
        vm.tipoQuarto.codTipoQuarto = $stateParams.codTipoQuarto;

        vm.msgs = "";

        vm.limparFormulario = limparFormulario;
        vm.irParaTelaConsultar = irParaTelaConsultar;
        vm.incluir = incluir;
        vm.alterar = alterar;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
            if (vm.tipoQuarto.codTipoQuarto !== undefined) {
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

            vm.tipoQuarto.codTipoQuarto = undefined;

            TipoQuartoData.salvar(vm.tipoQuarto).then(function (data) {
            	MsgCenter.add("INFO", "Tipo de Quarto incluído(a) com sucesso!", undefined, undefined);    
                limparFormulario();
            });
        }
        
        function limparFormulario() {
        	vm.tipoQuarto = {};
        }

        function alterar() {

        	TipoQuartoData.salvar(vm.tipoQuarto).then(function (data) {
        		MsgCenter.add("INFO", "Tipo de Quarto alterado(a) com sucesso!", undefined, undefined);
                $state.go('tipoquartoDetalhar', {
                    'codTipoQuarto': vm.tipoQuarto.codTipoQuarto
                });
            });
        }

        function obter() {
        	TipoQuartoData.obter(vm.tipoQuarto.codTipoQuarto).then(function (data) {
                vm.tipoQuarto = data.plain();
            });
        }
    }

})();
//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'editar' do modulo 'filial'
    angular.module('filial').controller(
            'EditarFilialController',
            EditarFilialController);


    // Definindo atributos e operacoes do Controlador da tela 'editar' do modulo 'Filial'
    /* @ngInject */
    function EditarFilialController($controller, $scope, $state, $stateParams,
    		FilialData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.filial = {};
        vm.filial.codFilial = $stateParams.codFilial;

        vm.msgs = "";

        vm.limparFormulario = limparFormulario;
        vm.irParaTelaConsultar = irParaTelaConsultar;
        vm.incluir = incluir;
        vm.alterar = alterar;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
            if (vm.filial.codFilial !== undefined) {
                obter();
            }
        }

        function recarregarTela() {
            $state.reload();
        }

        function irParaTelaConsultar() {
            $state.go('filialConsultar');
        }
        
        function incluir() {

            vm.filial.codFilial = undefined;

            FilialData.salvar(vm.filial).then(function (data) {
                vm.msgs = "Filial incluído com sucesso!";
                limparFormulario();
            });
        }
        
        function limparFormulario() {
        	vm.filial = {};
        }

        function alterar() {

        	FilialData.salvar(vm.filial).then(function (data) {
                vm.msgs = "Filial salvo com sucesso!";
                $state.go('filialDetalhar', {
                    'codFilial': vm.filial.codFilial
                });
            });
        }

        function obter() {
        	FilialData.obter(vm.filial.codFilial).then(function (data) {
                vm.filial = data.plain();
            });
        }
    }

})();
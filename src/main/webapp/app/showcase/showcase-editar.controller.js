//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'editar' do modulo 'showcase'
    angular.module('showcase').controller(
            'EditarShowcaseController',
            EditarShowcaseController);


    // Definindo atributos e operacoes do Controlador da tela 'editar' do modulo 'Showcase'
    /* @ngInject */
    function EditarShowcaseController($controller, $scope, $state, $stateParams,
    		ShowcaseData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.showcase = {};
        vm.showcase.codShowcase = $stateParams.codShowcase;

        vm.msgs = "";

        vm.limparFormulario = limparFormulario;
        vm.irParaTelaConsultar = irParaTelaConsultar;
        vm.incluir = incluir;
        vm.alterar = alterar;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
            if (vm.showcase.codShowcase !== undefined) {
                obter();
            }
        }

        function recarregarTela() {
            $state.reload();
        }

        function irParaTelaConsultar() {
            $state.go('showcaseConsultar');
        }
        
        function incluir() {

            vm.showcase.codShowcase = undefined;

            ShowcaseData.salvar(vm.showcase).then(function (data) {
                vm.msgs = "Showcase incluído com sucesso!";
                limparFormulario();
            });
        }
        
        function limparFormulario() {
        	vm.showcase = {};
        }

        function alterar() {

        	ShowcaseData.salvar(vm.showcase).then(function (data) {
                vm.msgs = "Showcase salvo com sucesso!";
                $state.go('showcaseDetalhar', {
                    'codShowcase': vm.showcase.codShowcase
                });
            });
        }

        function obter() {
        	ShowcaseData.obter(vm.showcase.codShowcase).then(function (data) {
                vm.showcase = data.plain();
            });
        }
    }

})();
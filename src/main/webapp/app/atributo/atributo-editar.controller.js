//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'editar' do modulo 'atributo'
    angular.module('atributo').controller(
            'EditarAtributoController',
            EditarAtributoController);


    // Definindo atributos e operacoes do Controlador da tela 'editar' do modulo 'Atributo'
    /* @ngInject */
    function EditarAtributoController($controller, $scope, $state, $stateParams,
    		AtributoData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.tipos = [];        
        
        vm.atributo = {};
        vm.atributo.codAtributo = $stateParams.codAtributo;

        vm.msgs = "";

        vm.limparFormulario = limparFormulario;
        vm.irParaTelaConsultar = irParaTelaConsultar;
        vm.incluir = incluir;
        vm.alterar = alterar;

        activate();

        function activate() {
            if (vm.atributo.codAtributo !== undefined) {
                obter();
            }            
        	// CARREGA LISTA DE TIPOS
			carregarTipos();            
        }        
        
		// ============================= OPERACOES DO CONTROLADOR  ============================= //
		function carregarTipos(){
			vm.tipos = [{
				codigo : 1,
				descricao : 'Hotel'
			}, {
				codigo : 2,
				descricao : 'Quarto'
			}];
		}        

        function recarregarTela() {
            $state.reload();
        }

        function irParaTelaConsultar() {
            $state.go('atributoConsultar');
        }
        
        function incluir() {

            vm.atributo.codAtributo = undefined;

            AtributoData.salvar(vm.atributo).then(function (data) {
                vm.msgs = "Atributo incluído com sucesso!";
                limparFormulario();
            });
        }
        
        function limparFormulario() {
        	vm.atributo = {};
        }

        function alterar() {

        	AtributoData.salvar(vm.atributo).then(function (data) {
                vm.msgs = "Atributo salvo com sucesso!";
                $state.go('atributoDetalhar', {
                    'codAtributo': vm.atributo.codAtributo
                });
            });
        }

        function obter() {
        	AtributoData.obter(vm.atributo.codAtributo).then(function (data) {
                vm.atributo = data.plain();
            });
        }
    }

})();
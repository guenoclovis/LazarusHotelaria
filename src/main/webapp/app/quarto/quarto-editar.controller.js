//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'editar' do modulo 'quarto'
    angular.module('quarto').controller(
            'EditarQuartoController',
            EditarQuartoController);


    // Definindo atributos e operacoes do Controlador da tela 'editar' do modulo 'quarto'
    /* @ngInject */
    function EditarQuartoController($controller, $scope, $state, $stateParams,
    		QuartoData, MsgCenter) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.tipos = [];        
        
        vm.quarto = {};
        vm.quarto.codQuarto = $stateParams.codQuarto;

        vm.msgs = "";

        vm.limparFormulario = limparFormulario;
        vm.irParaTelaConsultar = irParaTelaConsultar;
        vm.incluir = incluir;
        vm.alterar = alterar;

        activate();

        function activate() {
            if (vm.quarto.codQuarto !== undefined) {
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
            $state.go('quartoConsultar');
        }
        
        function incluir() {

            vm.quarto.codQuarto = undefined;

            QuartoData.salvar(vm.quarto).then(function (data) {
            	MsgCenter.add("INFO", "Quarto incluído(a) com sucesso!", undefined, undefined);    
                limparFormulario();
            });
        }
        
        function limparFormulario() {
        	vm.quarto = {};
        }

        function alterar() {

        	QuartoData.salvar(vm.quarto).then(function (data) {
        		MsgCenter.add("INFO", "Quarto alterado(a) com sucesso!", undefined, undefined);
                $state.go('quartoDetalhar', {
                    'codQuarto': vm.quarto.codQuarto
                });
            });
        }

        function obter() {
        	QuartoData.obter(vm.quarto.codQuarto).then(function (data) {
                vm.quarto = data.plain();
            });
        }
    }

})();
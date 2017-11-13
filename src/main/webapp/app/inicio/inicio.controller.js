//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'consultar' do modulo 'reserva'
	angular.module('inicio').controller('InicioController',
			InicioController);

	// Definindo atributos e operacoes do Controlador da tela 'consultar' do
	// modulo 'Reserva'
	/* @ngInject */
	function InicioController($controller, $scope, $state,
			ReservaData, MsgCenter, FilialData) {

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		var vm = this;
		
		vm.filiais = {};

		vm.msgs = "";

		vm.filtros = {};

		// Paginação
		vm.totalresults = 0;
		vm.pagesize = 0;
		vm.currentpage = 0;
		vm.pageoptions = [ 10, 25, 50, 100 ];

		// Operacoes acessiveis no html
		vm.pesquisarLimpar = pesquisarLimpar;
		vm.pageSizeAlterado = pageSizeAlterado;
		vm.paginaAlterada = paginaAlterada;
		vm.limpar = limpar;
		vm.irParaTelaPesquisaReserva = irParaTelaPesquisaReserva;

		activate();

		// ////// OPERACOES DO CONTROLADOR ////////////////////
		

		function activate() {
			// vm.deveRestaurar = FiltroService.deveRestaurar();
			// restaurarEstadoTela();
			carregarFiliais();
		}

		function pesquisarLimpar() {
			vm.filtros.currentpage = 0;
			MsgCenter.clear();
			pesquisar();
		}

		function paginaAlterada() {
			vm.filtros.pagesize = vm.pagesize;
			vm.filtros.currentpage = vm.currentpage - 1;
			pesquisar();
		}

		function pageSizeAlterado() {
			vm.currentpage = 1;
			paginaAlterada();
		}

		function limpar() {
			$state.reload();
		}

		function irParaTelaPesquisaReserva(codFilial) {
			// salvarEstadoTela();
			$state.go('reservaConsultar', {
				'codFilial' : codFilial
			});
		}


		function carregarFiliais() {
			MsgCenter.clear();
			var filtros = vm.filtros;

			FilialData.listar(filtros).then(function(data) {
				vm.filiais = data.entidades;
			});
		}

	}

})();

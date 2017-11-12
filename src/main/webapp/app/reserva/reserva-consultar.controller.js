//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'consultar' do modulo 'reserva'
	angular.module('reserva').controller('ConsultarReservaController',
			ConsultarReservaController);

	// Definindo atributos e operacoes do Controlador da tela 'consultar' do
	// modulo 'Reserva'
	/* @ngInject */
	function ConsultarReservaController($controller, $scope, $state,
			ReservaData, MsgCenter, FilialData) {

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		var vm = this;

		vm.popupDataEntrada = {
			opened : false,
		};

		vm.popupDataSaida = {
			opened : false
		};
		
		vm.openDataEntrada = openDataEntrada;
		vm.openDataSaida = openDataSaida;

		vm.msgs = "";

		vm.filtros = {};
		vm.reservas = [];
		vm.reserva = {};

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
		vm.irParaTelaInclusao = irParaTelaInclusao;
		vm.irParaTelaDetalhamento = irParaTelaDetalhamento;

		activate();

		// ////// OPERACOES DO CONTROLADOR ////////////////////
		
		function openDataEntrada() {
			vm.popupDataEntrada.opened = true;
		}

		function openDataSaida() {
			vm.popupDataSaida.opened = true;
		}

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

		function irParaTelaDetalhamento(codReserva) {
			// salvarEstadoTela();
			$state.go('reservaDetalhar', {
				'codReserva' : codReserva
			});
		}

		function irParaTelaInclusao() {
			// salvarEstadoTela();
			$state.go('reservaEditar');
		}

		function salvarEstadoTela() {
			var devePesquisar = vm.reservas.length > 0;
			// FiltroService.salvarFiltros(vm.filtros, devePesquisar);
		}

		function restaurarEstadoTela() {
			/*
			 * if (FiltroService.deveRestaurar()) { vm.filtros =
			 * FiltroService.obterFiltros();
			 * 
			 * if (FiltroService.devePesquisar()) { pesquisar(); }
			 * FiltroService.marcarRestaurado(); }
			 */
		}

		function carregarFiliais() {
			MsgCenter.clear();
			var filtros = vm.filtros;

			FilialData.listar(filtros).then(function(data) {
				vm.filiais = data.entidades;
			});
		}

		function pesquisar() {
			MsgCenter.clear();
			var filtros = {};

			ReservaData.listar(filtros).then(function(data) {
				vm.filiais = data.entidades;
			});
		}
	}

})();

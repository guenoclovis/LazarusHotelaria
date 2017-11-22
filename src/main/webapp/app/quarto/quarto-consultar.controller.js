//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'consultar' do modulo 'quarto'
	angular.module('quarto').controller('ConsultarQuartoController',
			ConsultarQuartoController);

	// Definindo atributos e operacoes do Controlador da tela 'consultar' do
	// modulo 'quarto'
	/* @ngInject */
	function ConsultarQuartoController($controller, $scope, $state, QuartoData,
			MsgCenter, FiltroService, FilialData) {

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		var vm = this;

		vm.msgs = "";

		vm.filtros = {};
		vm.quartos = [];
		vm.quarto = {};
		
		vm.filiais = [];

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

		function activate() {
			vm.deveRestaurar = FiltroService.deveRestaurar();
			restaurarEstadoTela();
			carregarFiliais();
			pesquisar();
		}
		
		function carregarFiliais() {
			MsgCenter.clear();
			var filtros = vm.filtros;

			FilialData.listar(filtros).then(function(data) {
				vm.filiais = data.entidades;
			});
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

		function irParaTelaDetalhamento(codQuarto) {
			salvarEstadoTela();
			$state.go('quartoDetalhar', {
				'codQuarto' : codQuarto
			});
		}

		function irParaTelaInclusao() {
			salvarEstadoTela();
			$state.go('quartoEditar');
		}

		function salvarEstadoTela() {
			var devePesquisar = vm.quartos.length > 0;
			FiltroService.salvarFiltros(vm.filtros, devePesquisar);
		}

		function restaurarEstadoTela() {
			if (FiltroService.deveRestaurar()) {
				vm.filtros = FiltroService.obterFiltros();

				if (FiltroService.devePesquisar()) {
					pesquisar();
				}
				FiltroService.marcarRestaurado();
			}
		}

		function pesquisar() {
			MsgCenter.clear();
			var filtros = vm.filtros;

			QuartoData.listar(filtros).then(function(data) {
				vm.quartos = data.entidades;

				if (data.pagina) {
					var page = data.pagina;
					vm.currentpage = page.currentPage + 1;
					vm.pagesize = page.pageSize;
					vm.totalresults = page.totalResults;
				}
				if (data.mensagens) {
					MsgCenter.addMessages(data.mensagens);
				}
			});
		}
	}

})();

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
			$stateParams, ReservaData, MsgCenter, FilialData, FiltroService,
			QuartoData, FotoData) {

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		var vm = this;

		vm.msgs = "";

		vm.popupDataEntrada = {
			opened : false,
		};

		vm.popupDataSaida = {
			opened : false
		};

		vm.openDataEntrada = openDataEntrada;
		vm.openDataSaida = openDataSaida;
		vm.pesquisarQuartosSemReserva = pesquisarQuartosSemReserva;
		
		vm.filtros = {};
		vm.quartos = [];
		vm.quarto = {};
		
		//vm.filtros.codFilial = $stateParams.codFilial;
		//vm.filtros.dataEntrada = $stateParams.dataEntrada;
		//vm.filtros.dataSaida = $stateParams.dataSaida;
		
		vm.filtros = { 
		};
		
		vm.quartosDisponiveis = 0;
		
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
		vm.irParaTelaDetalhamento = irParaTelaDetalhamento;
		vm.carregarFiliais = carregarFiliais;
		vm.carregarFilial = carregarFilial;
		vm.pesquisarQuartosSemReserva = pesquisarQuartosSemReserva;
			
		

		activate();

		// ////// OPERACOES DO CONTROLADOR ////////////////////
		
		function activate() {
			vm.deveRestaurar = FiltroService.deveRestaurar();
			
			vm.filtros.codFilial = 1;
			vm.filtros.dataEntrada =  '2018-06-02T14:00:00.000Z';    
			vm.filtros.dataSaida = '2018-06-03T12:00:00.000Z';
			
			/*
			var tomorrow = new Date();
			var numberOfDaysToAdd = 1;
			tomorrow.setDate(new Date().getDate() + numberOfDaysToAdd);
			
			vm.filtros.dataEntrada =  $filter('date')(new Date(), 'yyyy-MM-ddTHH:mm.ss.SSSZ');    
			vm.filtros.dataSaida = $filter('date')(tomorrow, 'yyyy-MM-ddTHH:mm.ss.SSSZ');
			*/
			
			restaurarEstadoTela();
			carregarFiliais();
			carregarFilial();
			
//			if(preencheuFiltrosObrigatorioParaPesquisa()){
				pesquisar();
//			}
				pesquisarQuartosSemReserva();
		}
		
		
		function openDataEntrada() {
			vm.popupDataEntrada.opened = true;
		}

		function openDataSaida() {
			vm.popupDataSaida.opened = true;
		}

		function preencheuFiltrosObrigatorioParaPesquisa(){
			if(vm.filtros.codFilial == undefined || vm.filtros.codFilial == ''){
				return false;
			}
			if(vm.filtros.dataEntrada == undefined || vm.filtros.dataEntrada == ''){
				return false;
			}
			if(vm.filtros.dataSaida == undefined || vm.filtros.dataSaida == ''){
				return false;
			}
			return true;
		}

		function carregarFiliais() {
			MsgCenter.clear();
			var filtros = vm.filtros;

			FilialData.listar(filtros).then(function(data) {
				vm.filiais = data.entidades;
			});
		}
		
		function carregarFilial(){
			MsgCenter.clear();
			var filtros = vm.filtros;

			FilialData.obter(vm.filtros.codFilial, filtros).then(function(data) {
				vm.filial = data;
				var filtros = { carregarImagemOriginal : true, carregarImagemMiniatura : true };
				
				if(vm.filial.foto != undefined && vm.filial.foto.codFoto != undefined && vm.filial.foto.codFoto != null){
					FotoData.obter(vm.filial.foto.codFoto, filtros).then(function(data) {
						vm.filial.foto = data;
					});	
				}
				vm.quartos = [];
			});
		}		
		
		function pesquisarLimpar() {
			vm.filtros.currentpage = 0;
			MsgCenter.clear();
			pesquisar();
			pesquisarQuartosSemReserva();
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
			salvarEstadoTela();
			$state.go('reservaDetalhar', {
				'codReserva' : codReserva
			});
		}

		function validarFiltrosObrigatorios() {
			
			var filtrosValidos = true;
			
			if(vm.filtros.codFilial == undefined){
				MsgCenter.add("WARN",
						"Selecione um Hotel", undefined,
						undefined);
				filtrosValidos = false;
			}
			
			if(vm.filtros.dataEntrada == undefined){
				MsgCenter.add("WARN",
						"Selecione uma Data de Entrada", undefined,
						undefined);
				filtrosValidos = false;
			}
			
			if(vm.filtros.dataSaida == undefined){
				MsgCenter.add("WARN",
						"Selecione uma Data de Saida", undefined,
						undefined);
				filtrosValidos = false;
			}
			
			return filtrosValidos;
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

			ReservaData.listar(filtros).then(function(data) {
				vm.reservas = data.entidades;

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
		
		function pesquisarQuartosSemReserva(){

			var filtros = vm.filtros;
			
			QuartoData.pesquisarSemReserva(filtros).then(function(data) {
				vm.quartosDisponiveis = page.totalResults;
			});
			
		}
	}

})();

//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'consultar' do modulo 'reserva'
	angular.module('inicio').controller('InicioController', InicioController);

	// Definindo atributos e operacoes do Controlador da tela 'consultar' do
	// modulo 'Reserva'
	/* @ngInject */
	function InicioController($controller, $scope, $state, ReservaData,
			MsgCenter, FilialData) {

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
		vm.pesquisarReservasFilial = pesquisarReservasFilial;

		activate();

		// ////// OPERACOES DO CONTROLADOR ////////////////////

		function activate() {
			// vm.deveRestaurar = FiltroService.deveRestaurar();
			// restaurarEstadoTela();
			
			var tomorrow = new Date();
			var numberOfDaysToAdd = 1;
			tomorrow.setDate(new Date().getDate() + numberOfDaysToAdd);
			
			vm.filtros.codFilial = 1;
			vm.filtros.dataEntrada =  new Date();   
			vm.filtros.dataSaida = tomorrow;
			
			carregarFiliais();
		}

		function openDataEntrada() {
			vm.popupDataEntrada.opened = true;
		}

		function openDataSaida() {
			vm.popupDataSaida.opened = true;
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
		
		
		function pesquisarReservasFilial(codFilial){
			vm.filtros.codFilial = codFilial;
			
			irParaTelaPesquisaReserva();
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

		function irParaTelaPesquisaReserva() {
			
			$state.go('quartoConsultarSiteAberto', {
				'codFilial' : vm.filtros.codFilial,
				'dataEntrada' : vm.filtros.dataEntrada,
				'dataSaida': vm.filtros.dataSaida
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

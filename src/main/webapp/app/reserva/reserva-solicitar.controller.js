//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'consultar' do modulo 'reserva'
	angular.module('reserva').controller('SolicitarReservaController',
			SolicitarReservaController);

	// Definindo atributos e operacoes do Controlador da tela 'consultar' do
	// modulo 'Reserva'
	/* @ngInject */
	function SolicitarReservaController($controller, $scope, $state,
			$stateParams, ReservaData, MsgCenter, FilialData, FiltroService,
			QuartoData, FotoData) {

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		var vm = this;

		vm.msgs = "";

		vm.filtros = {};

		vm.filiais = [];
		vm.filial = {};
		
		vm.reservas = [];
		vm.reserva = {};
		
		vm.codQuarto = $stateParams.codQuarto;
		
		vm.reserva.dataEntrada = $stateParams.dataEntrada;
		vm.reserva.dataSaida = $stateParams.dataSaida;
		
		vm.solicitarReserva = solicitarReserva;

		activate();

		// ////// OPERACOES DO CONTROLADOR ////////////////////

		function activate() {
			carregarFiliais();
			carregarFilial();
			carregarQuarto();
		}

		function solicitarReserva() {
			MsgCenter.clear();
			ReservaData.solicitarReserva(vm.reserva).then(function(data) {
				
				
				MsgCenter.add("INFO",
						"Solicitação de Reserva efetuada com sucesso", undefined,
						undefined);
				
				
				if (data.mensagens) {
					MsgCenter.addMessages(data.mensagens);
				}
			});
			
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

			FilialData.obter(vm.codFilial, filtros).then(function(data) {
				vm.filial = data.plain();
				var filtros = { carregarImagemOriginal : true, carregarImagemMiniatura : true };
				
				
				
				vm.quartos = [];
			});
		}
		
		
		function carregarQuarto(){
			MsgCenter.clear();
			var filtros = vm.filtros;

			QuartoData.obter(vm.codQuarto, filtros).then(function(data) {
				vm.quarto = data.plain();
				var filtros = { carregarImagemOriginal : false, carregarImagemMiniatura : true };				
				
				if(vm.quarto.foto.codFoto != undefined && vm.quarto.foto.codFoto != null){
					FotoData.obter(vm.quarto.foto.codFoto, filtros).then(function(data) {
						vm.quarto.foto = data.plain();
					});	
				}
				
				
				vm.reserva.quarto = vm.quarto;
				
				vm.quartos = [];
			});
		}
		
	}

})();

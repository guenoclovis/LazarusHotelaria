//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'consultar' do modulo 'reserva'
	angular.module('reserva').controller('DetalharReservaController',
			DetalharReservaController);

	// Definindo atributos e operacoes do Controlador da tela 'detalhar' do
	// modulo 'Reserva'
	/* @ngInject */
	function DetalharReservaController($controller, $scope, $state,
			$stateParams, ReservaData, MsgCenter, FilialData, FiltroService,
			QuartoData, FotoData, TipoQuartoData) {

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		var vm = this;

		vm.msgs = "";

		vm.reserva = {};
		vm.reserva.codReserva = $stateParams.codReserva;

		vm.voltarParaTelaAnterior = voltarParaTelaAnterior;
		vm.confirmar = confirmar;
		vm.cancelar = cancelar;
		vm.checkin = checkin;
		vm.checkout = checkout;

		activate();

		// ////// OPERACOES DO CONTROLADOR ////////////////////

		function activate() {
			carregarReserva();
		}

		function voltarParaTelaAnterior() {
			$state.go('reservaConsultar');
		}

		function irParaTelaEdicao(codReserva) {
			$state.go('reservaEditar', {
				'codReserva' : codReserva
			});
		}

		function excluir() {
			QuartoData.excluir(vm.quarto.codQuarto).then(function(data) {
				$state.go('quartoConsultar');
			});
		}

		function carregarFilial() {
			MsgCenter.clear();
			var filtros = vm.filtros;

			FilialData.obter(vm.codFilial, filtros).then(function(data) {
				vm.filial = data.plain();
				var filtros = {
					carregarImagemOriginal : true,
					carregarImagemMiniatura : true
				};

				vm.quartos = [];
			});
		}

		function carregarReserva() {
			MsgCenter.clear();
			var filtros = vm.filtros;

			ReservaData.obter(vm.reserva.codReserva, filtros).then(
					function(data) {
						vm.reserva = data.plain();

						carregarQuarto()
					});
		}

		function carregarQuarto() {
			
			var filtros = vm.filtros;

			QuartoData.obter(vm.reserva.quarto.codQuarto, filtros).then(
					function(data) {
						vm.quarto = data.plain();
						var filtros = {
							carregarImagemOriginal : false,
							carregarImagemMiniatura : true
						};

						if (vm.quarto.foto != undefined && vm.quarto.foto.codFoto != undefined
								&& vm.quarto.foto.codFoto != null) {
							FotoData.obter(vm.quarto.foto.codFoto, filtros)
									.then(function(data) {
										vm.quarto.foto = data.plain();
									});
						}

						vm.reserva.quarto = vm.quarto;

						carregarTipoQuarto();

						vm.quartos = [];
					});
		}

		function carregarTipoQuarto() {

			var filtros = vm.filtros;

			TipoQuartoData.obter(vm.reserva.quarto.codTipoQuarto, filtros)
					.then(function(data) {
						vm.tipoQuarto = data.plain();
					});
		}
		
		function confirmar() {																																																																																																						
			vm.reserva.status = 2;
			alterar("Reserva confirmada com sucesso!");
		}
		
		function checkin() {
			vm.reserva.status = 3;
			alterar("Check-in realizado com sucesso!");
		}
		
		function checkout() {
			vm.reserva.status = 4;
			alterar("Check-out realizado com sucesso!");
		}
		
		function cancelar() {
			vm.reserva.status = 0;
			alterar("Cancelamento realizado com sucesso!");
		}
		
		function alterar(msg) {
			MsgCenter.clear();

			ReservaData.salvar(vm.reserva).then(
				function(data) {
					
					if(!msg){
						msg = "Reserva alterado(a) com sucesso!";
					}
					
					//--NAO Foi possivel usar o carregarReserva, pque ele apaga a msg!!!
					MsgCenter.clear();
					var filtros = vm.filtros;

					ReservaData.obter(vm.reserva.codReserva, filtros).then(
							function(data) {
								vm.reserva = data.plain();

								carregarQuarto()
								
								MsgCenter.add("INFO",
										msg, undefined,
										undefined);
							});
					//--NAO Foi possivel usar o carregarReserva, pque ele apaga a msg!!!
					

				});
		}

	}

})();

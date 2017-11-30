//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'consultar' do modulo 'reserva'
	angular.module('reserva').controller('EditarReservaController',
			EditarReservaController);

	// Definindo atributos e operacoes do Controlador da tela 'detalhar' do
	// modulo 'Reserva'
	/* @ngInject */
	function EditarReservaController($controller, $scope, $state,
			$stateParams, ReservaData, MsgCenter, FilialData, FiltroService,
			QuartoData, FotoData, TipoQuartoData) {

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		var vm = this;

		vm.msgs = "";

		vm.reserva = {};
		vm.reserva.codReserva = $stateParams.codReserva;

		vm.voltarParaTelaAnterior = voltarParaTelaAnterior;

		activate();

		// ////// OPERACOES DO CONTROLADOR ////////////////////

		function activate() {
			carregarReserva();
		}

		function voltarParaTelaAnterior() {
			$state.go('reservaConsultar');
		}

		function irParaTelaDetalhamento(codDetalhar) {
			$state.go('reservaDetalhar', {
				'codDetalhar' : codDetalhar
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
			MsgCenter.clear();
			var filtros = vm.filtros;

			QuartoData.obter(vm.reserva.quarto.codQuarto, filtros).then(
					function(data) {
						vm.quarto = data.plain();
						var filtros = {
							carregarImagemOriginal : false,
							carregarImagemMiniatura : true
						};

						if (vm.quarto.foto.codFoto != undefined
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
			MsgCenter.clear();
			var filtros = vm.filtros;

			TipoQuartoData.obter(vm.reserva.quarto.codTipoQuarto, filtros)
					.then(function(data) {
						vm.tipoQuarto = data.plain();
					});
		}
		
		function recarregarTela() {
			$state.reload();
		}

		function irParaTelaConsultar() {
			MsgCenter.clear();

			$state.go('reservaConsultar');
		}		

		function limparFormulario() {
			vm.quarto = {};
		}

		function alterar() {
			MsgCenter.clear();

			ReservaData.salvar(vm.reserva).then(
					function(data) {
						MsgCenter.add("INFO",
								"Reserva alterado(a) com sucesso!", undefined,
								undefined);

						$state.go('reservaDetalhar', {
							'codReserva' : vm.quarto.codReserva
						});
					});
		}

	}

})();

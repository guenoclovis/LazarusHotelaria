//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'editar' do modulo 'quarto'
	angular.module('quarto').controller('EditarQuartoController',
			EditarQuartoController);

	
	
	// Definindo atributos e operacoes do Controlador da tela 'editar' do modulo
	// 'Quarto'
	/* @ngInject */
	function EditarQuartoController($controller, $scope,  $http, $state, $stateParams,
			QuartoData, MsgCenter, FotoData, FilialData, TipoQuartoData) {

		
		var vm = this;
		
		// ////////
		// the image
		vm.imagem = undefined;
		vm.nomeImagem = $scope.nomeArquivo;
		

		vm.uploadImage = function() {
			
			var imgBlob = dataURItoBlob(vm.imagem);
			
			FotoData.incluir(imgBlob, "quarto.jpg", 'fjsadffsasadfas').then(function(response) {
				vm.quarto.foto = response.data;				
			});
		}

		// you need this function to convert the dataURI
		function dataURItoBlob(dataURI) {
			var binary = atob(dataURI.split(',')[1]);
			var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
			var array = [];
			for (var i = 0; i < binary.length; i++) {
				array.push(binary.charCodeAt(i));
			}
			return new Blob([ new Uint8Array(array) ], {
				type : mimeString
			});
		}
		// ///////

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		

		vm.quarto = {};
		vm.quarto.codQuarto = $stateParams.codQuarto;

		vm.msgs = "";

		vm.limparFormulario = limparFormulario;
		vm.irParaTelaConsultar = irParaTelaConsultar;
		vm.incluir = incluir;
		vm.alterar = alterar;
		vm.carregarFiliais = carregarFiliais;

		activate();

		// ////// OPERACOES DO CONTROLADOR ////////////////////
		
		function carregarTipoQuarto() {
			MsgCenter.clear();
			var filtros = vm.filtros;

			TipoQuartoData.listar(filtros).then(function(data) {
				vm.tiposQuartos = data.entidades;
			});
		}

		function activate() {
			if (vm.quarto.codQuarto !== undefined) {
				obter();
			}
			carregarFiliais();
			carregarTipoQuarto();
		}
		
		function carregarFiliais() {
			MsgCenter.clear();
			var filtros = vm.filtros;

			FilialData.listar(filtros).then(function(data) {
				vm.filiais = data.entidades;
			});
		}

		function recarregarTela() {
			$state.reload();
		}

		function irParaTelaConsultar() {
			MsgCenter.clear();

			$state.go('quartoConsultar');
		}

		function incluir() {
			MsgCenter.clear();

			vm.quarto.codQuarto = undefined;

			QuartoData.salvar(vm.quarto).then(
					function(data) {
						MsgCenter.add("INFO",
								"Quarto incluído(a) com sucesso!", undefined,
								undefined);
						limparFormulario();
					});
		}

		function limparFormulario() {
			vm.quarto = {};
		}

		function alterar() {
			MsgCenter.clear();

			QuartoData.salvar(vm.quarto).then(
					function(data) {
						MsgCenter.add("INFO",
								"Quarto alterado(a) com sucesso!", undefined,
								undefined);

						$state.go('quartoDetalhar', {
							'codQuarto' : vm.quarto.codQuarto
						});
					});
		}

		function obter() {
			QuartoData.obter(vm.quarto.codQuarto).then(function(data) {
				vm.quarto = data.plain();
			});
		}
	}
})();
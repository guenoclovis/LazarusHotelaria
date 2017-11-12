//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'editar' do modulo 'filial'
	angular.module('filial').controller('EditarFilialController',
			EditarFilialController);

	//your directive
	angular.module('filial').directive("fileread", [
	  function() {
	    return {
	      scope: {
	        fileread: "="
	      },
	      link: function(scope, element, attributes) {
	        element.bind("change", function(changeEvent) {
	          var reader = new FileReader();
	          reader.onload = function(loadEvent) {
	            scope.$apply(function() {
	              scope.fileread = loadEvent.target.result;
	            });
	          }
	          reader.readAsDataURL(changeEvent.target.files[0]);
	        });
	      }
	    }
	  }
	]);
	
	// Definindo atributos e operacoes do Controlador da tela 'editar' do modulo
	// 'Filial'
	/* @ngInject */
	function EditarFilialController($controller, $scope,  $http, $state, $stateParams,
			FilialData, MsgCenter) {

		
		var vm = this;
		
		// ////////
		// the image
		vm.uploadme;

		vm.uploadImage = function() {
			
			var imgBlob = dataURItoBlob(vm.uploadme);
			
			FilialData.uploadImage(imgBlob, 'teste.png', 'fjsadffsasadfas').then(function(data) {
					//???
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
		

		vm.filial = {};
		vm.filial.codFilial = $stateParams.codFilial;

		vm.msgs = "";

		vm.limparFormulario = limparFormulario;
		vm.irParaTelaConsultar = irParaTelaConsultar;
		vm.incluir = incluir;
		vm.alterar = alterar;

		activate();

		// ////// OPERACOES DO CONTROLADOR ////////////////////

		function activate() {
			if (vm.filial.codFilial !== undefined) {
				obter();
			}
		}

		function recarregarTela() {
			$state.reload();
		}

		function irParaTelaConsultar() {
			MsgCenter.clear();

			$state.go('filialConsultar');
		}

		function incluir() {
			MsgCenter.clear();

			vm.filial.codFilial = undefined;

			FilialData.salvar(vm.filial).then(
					function(data) {
						MsgCenter.add("INFO",
								"Filial incluído(a) com sucesso!", undefined,
								undefined);
						limparFormulario();
					});
		}

		function limparFormulario() {
			vm.filial = {};
		}

		function alterar() {
			MsgCenter.clear();

			FilialData.salvar(vm.filial).then(
					function(data) {
						MsgCenter.add("INFO",
								"Filial alterado(a) com sucesso!", undefined,
								undefined);

						$state.go('filialDetalhar', {
							'codFilial' : vm.filial.codFilial
						});
					});
		}

		function obter() {
			FilialData.obter(vm.filial.codFilial).then(function(data) {
				vm.filial = data.plain();
			});
		}
	}

})();
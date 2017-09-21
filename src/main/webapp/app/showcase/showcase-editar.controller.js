//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'editar' do modulo 'showcase'
	angular.module('showcase').controller('EditarShowcaseController',
			EditarShowcaseController);

	// Definindo atributos e operacoes do Controlador da tela 'editar' do modulo
	// 'Showcase'
	/* @ngInject */
	function EditarShowcaseController($controller, $scope, $state,
			$stateParams, ShowcaseData, $http) {

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		var vm = this;

		vm.showcase = {};
		vm.showcase.codShowcase = $stateParams.codShowcase;
		vm.showcase.conhecimentos = [];

		vm.msgs = "";

		vm.limparFormulario = limparFormulario;
		vm.irParaTelaConsultar = irParaTelaConsultar;
		vm.incluir = incluir;
		vm.alterar = alterar;
		vm.open1 = open1;
		vm.open2 = open2;
		vm.getLocation = getLocation;
		
		vm.states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Dakota', 'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];

		activate();

		// ////// OPERACOES DO CONTROLADOR ////////////////////
		
		function getLocation(textoDigitadoNaTela) {
		    return $http.get('//maps.googleapis.com/maps/api/geocode/json', {
		      params: {
		        address: textoDigitadoNaTela,
		        sensor: false
		      }
		    }).then(function(response){
		      return response.data.results.map(function(item){
		        return item.formatted_address;
		      });
		    });
		  };

		function activate() {
			if (vm.showcase.codShowcase !== undefined) {
				obter();
			}

			// Carregamento de listas
			carregarUFs();
			carregarConhecimentos();
		}

		vm.popup1 = {
			opened : false
		};

		vm.popup2 = {
			opened : false
		};

		function open1() {
			vm.popup1.opened = true;
		};

		function open2() {
			vm.popup2.opened = true;
		};

		function carregarUFs() {
			vm.ufs = [ {
				codigo : 1,
				descricao : 'PR'
			}, {
				codigo : 2,
				descricao : 'SC'
			}, {
				codigo : 3,
				descricao : 'RS'
			}, ]
		}

		function carregarConhecimentos() {
			vm.conhecimentos = [ {
				codigo : 1,
				descricao : 'Java'
			}, {
				codigo : 2,
				descricao : 'AngularJS'
			}, {
				codigo : 3,
				descricao : 'Javascript'
			}, {
				codigo : 4,
				descricao : 'SQL'
			} ]
		}

		function recarregarTela() {
			$state.reload();
		}

		function irParaTelaConsultar() {
			$state.go('showcaseConsultar');
		}

		function incluir() {

			vm.showcase.codShowcase = undefined;

			ShowcaseData.salvar(vm.showcase).then(function(data) {
				vm.msgs = "Showcase incluído com sucesso!";
				limparFormulario();
			});
		}

		function limparFormulario() {
			vm.showcase = {};
		}

		function alterar() {

			ShowcaseData.salvar(vm.showcase).then(function(data) {
				vm.msgs = "Showcase salvo com sucesso!";
				$state.go('showcaseDetalhar', {
					'codShowcase' : vm.showcase.codShowcase
				});
			});
		}

		function obter() {
			ShowcaseData.obter(vm.showcase.codShowcase).then(function(data) {
				vm.showcase = data.plain();
			});
		}
	}

})();
//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'editar' do modulo 'cliente'
	angular.module('cliente').controller('EditarClienteController',
			EditarClienteController);

	// Definindo atributos e operacoes do Controlador da tela 'editar' do modulo
	// 'cliente'
	/* @ngInject */
	function EditarClienteController($controller, $scope, $state, $stateParams,
			ClienteData) {

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		var vm = this;

		vm.ufs = [];
		
		vm.popup1 = {
			opened : false,			
		};

		vm.popup2 = {
			opened : false
		};
		
		vm.cliente = {};
		vm.cliente.codCliente = $stateParams.codCliente;

		vm.msgs = "";

		vm.limparFormulario = limparFormulario;
		vm.irParaTelaConsultar = irParaTelaConsultar;
		vm.incluir = incluir;
		vm.alterar = alterar;
		vm.open1 = open1;
		vm.open2 = open2;
		activates();
		
		function activates() {
			if (vm.cliente.codCliente !== undefined) {
				obter();
			}

			// Carregamento de listas
			carregarUFs();
			//carregarConhecimentos();
		}

		// ////// OPERACOES DO CONTROLADOR ////////////////////

		function carregarUFs() {
			vm.ufs = [ {
				codigo : 1,
				descricao : 'AC'
			}, {
				codigo : 2,
				descricao : 'AL'
			}, {
				codigo : 3,
				descricao : 'AP'
			}, {
				codigo : 4,
				descricao : 'AM'
			}, {
				codigo : 5,
				descricao : 'BA'
			}, {
				codigo : 6,
				descricao : 'CE'
			}, {
				codigo : 7,
				descricao : 'DF'
			}, {
				codigo : 8,
				descricao : 'ES'
			}, {
				codigo : 9,
				descricao : 'GO'
			}, {
				codigo : 10,
				descricao : 'MA'
			}, {
				codigo : 11,
				descricao : 'MT'
			}, {
				codigo : 12,
				descricao : 'MS'
			}, {
				codigo : 13,
				descricao : 'MG'
			}, {
				codigo : 14,
				descricao : 'PA'
			}, {
				codigo : 15,
				descricao : 'PB'
			}, {
				codigo : 16,
				descricao : 'PR'
			}, {
				codigo : 17,
				descricao : 'PE'
			}, {
				codigo : 18,
				descricao : 'PI'
			}, {
				codigo : 19,
				descricao : 'RJ'
			}, {
				codigo : 20,
				descricao : 'RN'
			}, {
				codigo : 21,
				descricao : 'RS'
			}, {
				codigo : 22,
				descricao : 'RO'
			}, {
				codigo : 23,
				descricao : 'RR'
			},{
				codigo : 24,
				descricao : 'SC'
			},{
				codigo : 25,
				descricao : 'SP'
			},{
				codigo : 26,
				descricao : 'SE'
			},{
				codigo : 27,
				descricao : 'TO'
			}];
		}
		
		function open1() {
			vm.popup1.opened = true;
		}

		function open2() {
			vm.popup2.opened = true;
		}
		

		function activate() {
			if (vm.cliente.codCliente !== undefined) {
				obter();
			}
		}

		function recarregarTela() {
			$state.reload();
		}

		function irParaTelaConsultar() {
			$state.go('clienteConsultar');
		}

		function incluir() {

			vm.cliente.codCliente = undefined;

			ClienteData.salvar(vm.cliente).then(function(data) {
				vm.msgs = "Cliente incluído com sucesso!";
				limparFormulario();
			});
		}

		function limparFormulario() {
			vm.cliente = {};
		}

		function alterar() {

			ClienteData.salvar(vm.cliente).then(function(data) {
				vm.msgs = "Cliente salvo com sucesso!";
				$state.go('clienteDetalhar', {
					'codCliente' : vm.cliente.codCliente
				});
			});
		}

		function obter() {
			ClienteData.obter(vm.cliente.codCliente).then(function(data) {
				vm.cliente = data.plain();
			});
		}
	}

})();
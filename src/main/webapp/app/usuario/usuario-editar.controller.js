//-----------------------------------------------------
// CONTROLADOR EDICAO
//-----------------------------------------------------
(function() {
	'use strict';

	// Adicionando um Controlador para a tela 'editar' do modulo 'usuario'
	angular.module('usuario').controller('EditarUsuarioController',
			EditarUsuarioController);

	// Definindo atributos e operacoes do Controlador da tela 'editar' do modulo
	// 'usuario'
	/* @ngInject */
	function EditarUsuarioController($controller, $scope, $state, $stateParams,
			UsuarioData, MsgCenter) {

		// ////// ATRIBUTOS DO CONTROLADOR ////////////////////
		var vm = this;

		vm.ufs = [];
		vm.perfis = [];
		
		vm.popup1 = {
			opened : false,			
		};

		vm.popup2 = {
			opened : false
		};
		
		vm.usuario = {};
		vm.usuario.codUsuario = $stateParams.codUsuario;

		vm.msgs = "";

		vm.limparFormulario = limparFormulario;
		vm.irParaTelaConsultar = irParaTelaConsultar;
		vm.incluir = incluir;
		vm.alterar = alterar;
		vm.open1 = open1;
		vm.open2 = open2;
		activates();
		
		function activates() {
			if (vm.usuario.codUsuario !== undefined) {
				obter();
			}

			// Carregamento de listas
			carregarUFs();
			carregarPerfis();
			//carregarConhecimentos();
		}

		// ////// OPERACOES DO CONTROLADOR ////////////////////

		function carregarPerfis() {
			vm.perfis = [ {
				codigo : 1,
				descricao : 'Atendente'
			}, {
				codigo : 2,
				descricao : 'Gerente'
			}];
		}
		
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
			if (vm.usuario.codUsuario !== undefined) {
				obter();
			}
		}

		function recarregarTela() {
			$state.reload();
		}

		function irParaTelaConsultar() {
			$state.go('usuarioConsultar');
		}

		function incluir() {

			vm.usuario.codUsuario = undefined;

			UsuarioData.salvar(vm.usuario).then(function(data) {
				MsgCenter.add("INFO", "Usuário incluído(a) com sucesso!", undefined, undefined);    
				limparFormulario();
			});
		}

		function limparFormulario() {
			vm.usuario = {};
		}

		function alterar() {

			UsuarioData.salvar(vm.usuario).then(function(data) {
				MsgCenter.add("INFO", "Usuário alterado(a) com sucesso!", undefined, undefined);
				$state.go('usuarioDetalhar', {
					'codUsuario' : vm.usuario.codUsuario
				});
			});
		}

		function obter() {
			UsuarioData.obter(vm.usuario.codUsuario).then(function(data) {
				vm.usuario = data.plain();
			});
		}
	}

})();
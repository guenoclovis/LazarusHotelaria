(function() {
	'use strict';

	angular.module('lazarusApp').config(function($stateProvider) {

		var root = {
			name : "root",
		    url: "/",
		    abstract : true,
		    templateUrl: 'carrossel.html'
		}
		
		var inicioState = {
			name : 'inicio',
			url : '/inicio',
			templateUrl : 'carrossel.html'
			// controller : 'InicioController'
		}
		
		var reservaConsultarState = {
			name : 'reserva',
			url : '/reserva',
			templateUrl : './reserva/reserva-consultar.html',			
			controller : 'ConsultarReservaController'
		}

		var reservaEditarState = {
			name : 'reservaEditar',
			url : '/reservaEditar',
			controller : 'EditarReservaController',
			templateUrl : './reserva/reserva-editar.html',
			params : {
				codReserva : undefined
			}
		}

		var reservaDetalharState = {
			name : 'reservaDetalhar',
			url : '/reservaDetalhar',
			controller : 'DetalharReservaController',
			templateUrl : './reserva/reserva-detalhar.html',
			params : {
				codReserva : undefined
			}
		}
		
		var tipoquartositeState = {
				name : 'tipoquartosite',
				url : '/tipoquarto',
				templateUrl : './quartos/tipoquartosite.html'
			}				
		
		var clienteConsultarState = {
				name : 'clienteConsultar',
				url : '/clienteConsultar',
				controller : 'ConsultarClienteController',
				templateUrl : './cliente/cliente-consultar.html'
			}

			var clienteEditarState = {
				name : 'clienteEditar',
				url : '/clienteEditar',
				controller : 'EditarClienteController',
				templateUrl : './cliente/cliente-editar.html',
				params : {
					codCliente : undefined
				}
			}

			var clienteDetalharState = {
				name : 'clienteDetalhar',
				url : '/clienteDetalhar',
				controller : 'DetalharClienteController',
				templateUrl : './cliente/cliente-detalhar.html',
				params : {
					codCliente : undefined
				}
			}

		/*
			var quartoConsultarState = {
				name : 'quartoConsultar',
				url : '/quartoConsultar',
				controller : 'ConsultarQuartoController',
				templateUrl : './quarto/quarto-consultar.html'
			}

			var quartoEditarState = {
				name : 'quartoEditar',
				url : '/quartoEditar',
				controller : 'EditarQuartoController',
				templateUrl : './quarto/quarto-editar.html',
				params : {
					codQuarto : undefined
				}
			}

			var quartoDetalharState = {
				name : 'quartoDetalhar',
				url : '/quartoDetalhar',
				controller : 'DetalharQuartoController',
				templateUrl : './quarto/quarto-detalhar.html',
				params : {
					codQuarto : undefined
				}
			}
*/

		var loginState = {
			name : 'login',
			url : '/login',
			templateUrl : './login/login.html'
		}
		
		var contatoState = {
				name : 'contato',
				url : '/contato',
				templateUrl : './contato/contato.html'
			}

		var clienteConsultarState = {
			name : 'clienteConsultar',
			url : '/clienteConsultar',
			controller : 'ConsultarClienteController',
			templateUrl : './cliente/cliente-consultar.html'
		}

		var clienteEditarState = {
			name : 'clienteEditar',
			url : '/clienteEditar',
			controller : 'EditarClienteController',
			templateUrl : './cliente/cliente-editar.html',
			params : {
				codCliente : undefined
			}
		}

		var clienteDetalharState = {
			name : 'clienteDetalhar',
			url : '/clienteDetalhar',
			controller : 'DetalharClienteController',
			templateUrl : './cliente/cliente-detalhar.html',
			params : {
				codCliente : undefined
			}
		}

		var filialConsultarState = {
			name : 'filialConsultar',
			url : '/filialConsultar',
			controller : 'ConsultarFilialController',
			templateUrl : './filial/filial-consultar.html',
			params : {
				codFilial : undefined
			}
		}

		var filialEditarState = {
			name : 'filialEditar',
			url : '/filialEditar',
			controller : 'EditarFilialController',
			templateUrl : './filial/filial-editar.html',
			params : {
				codFilial : undefined
			}
		}

		var filialDetalharState = {
			name : 'filialDetalhar',
			url : '/filialDetalhar',
			controller : 'DetalharFilialController',
			templateUrl : './filial/filial-detalhar.html',
			params : {
				codFilial : undefined
			}
		}

		var atributoConsultarState = {
			name : 'atributoConsultar',
			url : '/atributoConsultar',
			controller : 'ConsultarAtributoController',
			templateUrl : './atributo/atributo-consultar.html',
			params : {
				codAtributo : undefined
			}
		}

		var atributoEditarState = {
			name : 'atributoEditar',
			url : '/atributoEditar',
			controller : 'EditarAtributoController',
			templateUrl : './atributo/atributo-editar.html',
			params : {
				codAtributo : undefined
			}
		}

		var atributoDetalharState = {
			name : 'atributoDetalhar',
			url : '/atributoDetalhar',
			controller : 'DetalharAtributoController',
			templateUrl : './atributo/atributo-detalhar.html',
			params : {
				codAtributo : undefined
			}
		}
		
		var tipoquartoConsultarState = {
				name : 'tipoquartoConsultar',
				url : '/tipoquartoConsultar',
				controller : 'ConsultarTipoQuartoController',
				templateUrl : './tipoquarto/tipoquarto-consultar.html',
				params : {
					codTipoQuarto : undefined
				}
			}

		var tipoquartoEditarState = {
			name : 'tipoquartoEditar',
			url : '/tipoquartoEditar',
			controller : 'EditarTipoQuartoController',
			templateUrl : './tipoquarto/tipoquarto-editar.html',
			params : {
				codTipoQuarto : undefined
			}
		}

		var tipoquartoDetalharState = {
			name : 'tipoquartoDetalhar',
			url : '/tipoquartoDetalhar',
			controller : 'DetalharTipoQuartoController',
			templateUrl : './tipoquarto/tipoquarto-detalhar.html',
			params : {
				codTipoQuarto : undefined
			}
		}		
		
		var usuarioConsultarState = {
				name : 'usuarioConsultar',
				url : '/usuarioConsultar',
				controller : 'ConsultarUsuarioController',
				templateUrl : './usuario/usuario-consultar.html'
			}

			var usuarioEditarState = {
				name : 'usuarioEditar',
				url : '/usuarioEditar',
				controller : 'EditarUsuarioController',
				templateUrl : './usuario/usuario-editar.html',
				params : {
					codUsuario : undefined
				}
			}

			var usuarioDetalharState = {
				name : 'usuarioDetalhar',
				url : '/usuarioDetalhar',
				controller : 'DetalharUsuarioController',
				templateUrl : './usuario/usuario-detalhar.html',
				params : {
					codUsuario : undefined
				}
			}


		var showcaseConsultarState = {
				name : 'showcaseConsultar',
				url : '/showcaseConsultar',
				controller : 'ConsultarShowcaseController',
				templateUrl : './showcase/showcase-consultar.html',
				params : {
					codShowcase : undefined
				}
			}

		var showcaseEditarState = {
			name : 'showcaseEditar',
			url : '/showcaseEditar',
			controller : 'EditarShowcaseController',
			templateUrl : './showcase/showcase-editar.html',
			params : {
				codShowcase : undefined
			}
		}

		var showcaseDetalharState = {
			name : 'showcaseDetalhar',
			url : '/showcaseDetalhar',
			controller : 'DetalharShowcaseController',
			templateUrl : './showcase/showcase-detalhar.html',
			params : {
				codShowcase : undefined
			}
		}		

		$stateProvider.state(root);
		$stateProvider.state(inicioState);
		$stateProvider.state(reservaConsultarState);
		$stateProvider.state(reservaEditarState);
		$stateProvider.state(reservaDetalharState);
		$stateProvider.state(tipoquartositeState);
		/*
		$stateProvider.state(quartosState);
		$stateProvider.state(quartoConsultarState);
		$stateProvider.state(quartoEditarState);
		$stateProvider.state(quartoDetalharState);
		*/
		$stateProvider.state(clienteConsultarState);
		$stateProvider.state(clienteEditarState);
		$stateProvider.state(clienteDetalharState);
		$stateProvider.state(loginState);
		$stateProvider.state(contatoState);
		$stateProvider.state(filialConsultarState);
		$stateProvider.state(filialEditarState);
		$stateProvider.state(filialDetalharState);
		$stateProvider.state(atributoConsultarState);
		$stateProvider.state(atributoEditarState);
		$stateProvider.state(atributoDetalharState);
		$stateProvider.state(tipoquartoConsultarState);
		$stateProvider.state(tipoquartoEditarState);
		$stateProvider.state(tipoquartoDetalharState);
		$stateProvider.state(usuarioConsultarState);
		$stateProvider.state(usuarioEditarState);
		$stateProvider.state(usuarioDetalharState);
		$stateProvider.state(showcaseConsultarState);
		$stateProvider.state(showcaseEditarState);
		$stateProvider.state(showcaseDetalharState);
	});

})();
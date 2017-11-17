(function() {
	'use strict';

	angular.module('lazarusApp').config(function($stateProvider) {

		var root = {
			name : "root",
		    url: "/",
		    abstract : true
		}		
		$stateProvider.state(root);
		
		var inicioState = {
			name : 'inicio',
			url : '/inicio',
			templateUrl : './inicio/inicio.html',
			controller : 'InicioController'
		}
		$stateProvider.state(inicioState);
		
		var reservaConsultarState = {
			name : 'reserva',
			url : '/reserva',
			templateUrl : './reserva/reserva-consultar.html',			
			controller : 'ConsultarReservaController',
			params : {
				codFilial : undefined,
				dataEntrada : undefined,
				dataSaida : undefined
			}
		}
		$stateProvider.state(reservaConsultarState);
		
		
		var reservaEditarState = {
			name : 'reservaEditar',
			url : '/reservaEditar',
			controller : 'EditarReservaController',
			templateUrl : './reserva/reserva-editar.html',
			params : {
				codReserva : undefined
			}
		}
		$stateProvider.state(reservaEditarState);

		var reservaDetalharState = {
			name : 'reservaDetalhar',
			url : '/reservaDetalhar',
			controller : 'DetalharReservaController',
			templateUrl : './reserva/reserva-detalhar.html',
			params : {
				codReserva : undefined
			}
		}
		$stateProvider.state(reservaDetalharState);
		
		var tipoquartositeState = {
			name : 'tipoquartosite',
			url : '/tipoquarto',
			templateUrl : './quartos/tipoquartosite.html'
		}
		$stateProvider.state(tipoquartositeState);
		
		var quartoConsultarState = {
			name : 'quartoConsultar',
			url : '/quartoConsultar',
			controller : 'ConsultarQuartoController',
			templateUrl : './quarto/quarto-consultar.html'
		}
		$stateProvider.state(quartoConsultarState);
		
		var quartoConsultarSiteAbertoState = {
				name : 'quartoConsultarSiteAberto',
				url : '/quartoConsultarSiteAberto',
				controller : 'ConsultarQuartoSiteAbertoController',
				templateUrl : './quarto/quarto-consultar-siteaberto.html'
		}
		$stateProvider.state(quartoConsultarSiteAbertoState);

		var quartoEditarState = {
			name : 'quartoEditar',
			url : '/quartoEditar',
			controller : 'EditarQuartoController',
			templateUrl : './quarto/quarto-editar.html',
			params : {
				codQuarto : undefined
			}
		}
		$stateProvider.state(quartoEditarState);

		var quartoDetalharState = {
			name : 'quartoDetalhar',
			url : '/quartoDetalhar',
			controller : 'DetalharQuartoController',
			templateUrl : './quarto/quarto-detalhar.html',
			params : {
				codQuarto : undefined
			}
		}
		$stateProvider.state(quartoDetalharState);

		var loginState = {
			name : 'login',
			url : '/login',
			templateUrl : './login/login.html'
		}
		$stateProvider.state(loginState);
		
		var contatoState = {
			name : 'contato',
			url : '/contato',
			templateUrl : './contato/contato.html'
		}
		$stateProvider.state(contatoState);

		var clienteConsultarState = {
			name : 'clienteConsultar',
			url : '/clienteConsultar',
			controller : 'ConsultarClienteController',
			templateUrl : './cliente/cliente-consultar.html'
		}
		$stateProvider.state(clienteConsultarState);

		var clienteEditarState = {
			name : 'clienteEditar',
			url : '/clienteEditar',
			controller : 'EditarClienteController',
			templateUrl : './cliente/cliente-editar.html',
			params : {
				codCliente : undefined
			}
		}
		$stateProvider.state(clienteEditarState);

		var clienteDetalharState = {
			name : 'clienteDetalhar',
			url : '/clienteDetalhar',
			controller : 'DetalharClienteController',
			templateUrl : './cliente/cliente-detalhar.html',
			params : {
				codCliente : undefined
			}
		}
		$stateProvider.state(clienteDetalharState);

		var filialConsultarState = {
			name : 'filialConsultar',
			url : '/filialConsultar',
			controller : 'ConsultarFilialController',
			templateUrl : './filial/filial-consultar.html',
			params : {
				codFilial : undefined
			}
		}
		$stateProvider.state(filialConsultarState);

		var filialEditarState = {
			name : 'filialEditar',
			url : '/filialEditar',
			controller : 'EditarFilialController',
			templateUrl : './filial/filial-editar.html',
			params : {
				codFilial : undefined
			}
		}
		$stateProvider.state(filialEditarState);

		var filialDetalharState = {
			name : 'filialDetalhar',
			url : '/filialDetalhar',
			controller : 'DetalharFilialController',
			templateUrl : './filial/filial-detalhar.html',
			params : {
				codFilial : undefined
			}
		}
		$stateProvider.state(filialDetalharState);

		var atributoConsultarState = {
			name : 'atributoConsultar',
			url : '/atributoConsultar',
			controller : 'ConsultarAtributoController',
			templateUrl : './atributo/atributo-consultar.html',
			params : {
				codAtributo : undefined
			}
		}
		$stateProvider.state(atributoConsultarState);

		var atributoEditarState = {
			name : 'atributoEditar',
			url : '/atributoEditar',
			controller : 'EditarAtributoController',
			templateUrl : './atributo/atributo-editar.html',
			params : {
				codAtributo : undefined
			}
		}
		$stateProvider.state(atributoEditarState);

		var atributoDetalharState = {
			name : 'atributoDetalhar',
			url : '/atributoDetalhar',
			controller : 'DetalharAtributoController',
			templateUrl : './atributo/atributo-detalhar.html',
			params : {
				codAtributo : undefined
			}
		}
		$stateProvider.state(atributoDetalharState);
		
		var tipoquartoConsultarState = {
			name : 'tipoquartoConsultar',
			url : '/tipoquartoConsultar',
			controller : 'ConsultarTipoQuartoController',
			templateUrl : './tipoquarto/tipoquarto-consultar.html',
			params : {
				codTipoQuarto : undefined
			}
		}
		$stateProvider.state(tipoquartoConsultarState);

		var tipoquartoEditarState = {
			name : 'tipoquartoEditar',
			url : '/tipoquartoEditar',
			controller : 'EditarTipoQuartoController',
			templateUrl : './tipoquarto/tipoquarto-editar.html',
			params : {
				codTipoQuarto : undefined
			}
		}
		$stateProvider.state(tipoquartoEditarState);
		
		var tipoquartoDetalharState = {
			name : 'tipoquartoDetalhar',
			url : '/tipoquartoDetalhar',
			controller : 'DetalharTipoQuartoController',
			templateUrl : './tipoquarto/tipoquarto-detalhar.html',
			params : {
				codTipoQuarto : undefined
			}
		}
		$stateProvider.state(tipoquartoDetalharState);
		
		var usuarioConsultarState = {
			name : 'usuarioConsultar',
			url : '/usuarioConsultar',
			controller : 'ConsultarUsuarioController',
			templateUrl : './usuario/usuario-consultar.html'
		}
		$stateProvider.state(usuarioConsultarState);

		var usuarioEditarState = {
			name : 'usuarioEditar',
			url : '/usuarioEditar',
			controller : 'EditarUsuarioController',
			templateUrl : './usuario/usuario-editar.html',
			params : {
				codUsuario : undefined
			}
		}
		$stateProvider.state(usuarioEditarState);

		var usuarioDetalharState = {
			name : 'usuarioDetalhar',
			url : '/usuarioDetalhar',
			controller : 'DetalharUsuarioController',
			templateUrl : './usuario/usuario-detalhar.html',
			params : {
				codUsuario : undefined
			}
		}
		$stateProvider.state(usuarioDetalharState);

		var showcaseConsultarState = {
			name : 'showcaseConsultar',
			url : '/showcaseConsultar',
			controller : 'ConsultarShowcaseController',
			templateUrl : './showcase/showcase-consultar.html',
			params : {
				codShowcase : undefined
			}
		}
		$stateProvider.state(showcaseConsultarState);

		var showcaseEditarState = {
			name : 'showcaseEditar',
			url : '/showcaseEditar',
			controller : 'EditarShowcaseController',
			templateUrl : './showcase/showcase-editar.html',
			params : {
				codShowcase : undefined
			}
		}
		$stateProvider.state(showcaseEditarState);

		var showcaseDetalharState = {
			name : 'showcaseDetalhar',
			url : '/showcaseDetalhar',
			controller : 'DetalharShowcaseController',
			templateUrl : './showcase/showcase-detalhar.html',
			params : {
				codShowcase : undefined
			}
		}
		$stateProvider.state(showcaseDetalharState);

	});

})();
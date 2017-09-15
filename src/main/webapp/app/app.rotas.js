(function () {
    'use strict';

    angular.module('lazarusApp')
    .config(function ($stateProvider) {
    	
        var inicioState = {
            name: 'inicio',
            url: '/inicio',
            templateUrl: 'carrossel.html'
        }

        var reservaState = {
            name: 'reserva',
            url: '/reserva',
            templateUrl: './reserva/reserva.html'
        }

        var quartosState = {
            name: 'quartos',
            url: '/quartos',
            templateUrl: './quartos/quartos.html'
        }

        var quartosProfileState = {
            name: 'quartos.profile',
            url: '/quartos/profile',
            templateUrl: './quartos/profile.html'
        }

        var quartosAccountState = {
            name: 'quartos.account',
            url: '/quartos/account',
            templateUrl: './quartos/account.html'
        }

        var quartosInfosState = {
            name: 'quartos.infos',
            url: '/quartos/infos',
            templateUrl: './quartos/infos.html'
        }

        var loginState = {
            name: 'login',
            url: '/login',
            templateUrl: './login/login.html'
        }

        var clienteConsultarState = {
            name: 'clienteConsultar',
            url: '/clienteConsultar',
            controller: 'ConsultarClienteController',
            templateUrl: './cliente/cliente-consultar.html'
        }

        var clienteEditarState = {
            name: 'clienteEditar',
            url: '/clienteEditar',
            controller: 'EditarClienteController',
            templateUrl: './cliente/cliente-editar.html',
            params: {
                codCliente: undefined
            }
        }

        var clienteDetalharState = {
            name: 'clienteDetalhar',
            url: '/clienteDetalhar',
            controller: 'DetalharClienteController',
            templateUrl: './cliente/cliente-detalhar.html',
            params: {
            	codCliente: undefined
            }
        }

        var filialConsultarState = {
            name: 'filialConsultar',
            url: '/filialConsultar',
            controller: 'ConsultarFilialController',
            templateUrl: './filial/filial-consultar.html',
            params: {
                id: undefined
            }
        }
        
        var filialEditarState = {
                name: 'filialEditar',
                url: '/filialEditar',
                controller: 'EditarFilialController',
                templateUrl: './filial/filial-editar.html',
                params: {
                    codFilial: undefined
                }
            }

            var filialDetalharState = {
                name: 'filialDetalhar',
                url: '/filialDetalhar',
                controller: 'DetalharFilialController',
                templateUrl: './filial/filial-detalhar.html',
                params: {
                	codFilial: undefined
                }
            }

        $stateProvider.state(inicioState);
        $stateProvider.state(reservaState);
        $stateProvider.state(quartosState);
        $stateProvider.state(quartosProfileState);
        $stateProvider.state(quartosAccountState);
        $stateProvider.state(quartosInfosState);
        $stateProvider.state(clienteConsultarState);
        $stateProvider.state(clienteEditarState);
        $stateProvider.state(clienteDetalharState);
        $stateProvider.state(loginState);
        $stateProvider.state(filialConsultarState);
        $stateProvider.state(filialEditarState);
        $stateProvider.state(filialDetalharState);
    });

})();
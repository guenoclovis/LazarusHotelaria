//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'consultar' do modulo 'login'
    angular.module('login').controller(
            'LoginController',
            LoginController);

    // Definindo atributos e operacoes do Controlador da tela 'consultar' do modulo 'login'
    /* @ngInject */
    function LoginController($controller, $scope, $state, $rootScope,
    		LoginData, MsgCenter) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.dadosLogin = {};


        // Operacoes acessiveis no html

        vm.login = login;
        vm.logout = logout;
        

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
        	MsgCenter.clear();
        }

        function irParaTelaLogin() {
        	MsgCenter.clear();
            $state.go('login');
        }
        
        function login() {
        	MsgCenter.clear();
        	LoginData.login(vm.dadosLogin).then(function (data) {
                $rootScope.nomeUsuario = data.nomeUsuario;
                $rootScope.perfil = data.perfil;
                
                $state.go('reservaConsultar');
            });
        }
        
        function logout() {
        	MsgCenter.clear();
        	
        	vm.dadosLogin = {};        	
        	
        	$rootScope.nomeUsuario = undefined;
        	
        	LoginData.logout().then(function (data) {                
                $state.go('login');
            });
        }

    }

})();


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
    function LoginController($controller, $scope, $state,
    		LoginData, MsgCenter) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.dadosLogin = {};


        // Operacoes acessiveis no html

        vm.login = login;
        

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
        	MsgCenter.clear();
        	irParaTelaLogin()
        }

        function irParaTelaLogin() {			
            $state.go('login', {
            });
        }
        function login() {
        	LoginData.login(vm.dadosLogin).then(function (data) {
                vm.clientes = data.entidades;

                if (data.pagina) {
                    var page = data.pagina;
                    vm.currentpage = page.currentPage + 1;
                    vm.pagesize = page.pageSize;
                    vm.totalresults = page.totalResults;
                }
                if (data.mensagens) {
                	MsgCenter.addMessages(data.mensagens);  
                }
                
            });
        }

    }

})();


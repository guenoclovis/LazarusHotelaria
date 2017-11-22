//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'consultar' do modulo 'contato'
    angular.module('contato').controller(
            'ContatoController',
            ContatoController);

    // Definindo atributos e operacoes do Controlador da tela 'consultar' do modulo 'Usuario'
    /* @ngInject */
    function ContatoController($controller, $scope, $state,
    		ContatoData, MsgCenter) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;
        
        vm.dados = {};

	    vm.enviarEmail = enviarEmail;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
        }

        function enviarEmail() {
        	
        	MsgCenter.add("WARN", "Enviando E-mail ...", undefined, undefined);
        	
            ContatoData.enviarEmail(vm.dados).then(function(data){
            	MsgCenter.clear();
            	
            	MsgCenter.add("INFO", "E-mail enviado com sucesso!", undefined, undefined);
            	
            	vm.dados = {};
            });
        }
    }

})();


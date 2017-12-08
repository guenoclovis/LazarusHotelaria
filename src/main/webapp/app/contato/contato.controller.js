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
        	MsgCenter.clear();
        }

        function enviarEmail() {
        	
        	if(!validarFiltrosObrigatorios()){
        		return;
        	}
        	
        	MsgCenter.add("WARN", "Enviando E-mail ...", undefined, undefined);
        	
            ContatoData.enviarEmail(vm.dados).then(function(data){
            	MsgCenter.clear();
            	
            	MsgCenter.add("INFO", "E-mail enviado com sucesso!", undefined, undefined);
            	
            	vm.dados = {};
            });
        }
        
        function validarFiltrosObrigatorios() {
			
			var filtrosValidos = true;
			
			if(vm.dados.nome == undefined){
				MsgCenter.add("WARN",
						"Nome obrigatorio", undefined,
						undefined);
				filtrosValidos = false;
			}
			
			if(vm.dados.telefone == undefined){
				MsgCenter.add("WARN",
						"Telefone obrigatorio", undefined,
						undefined);
				filtrosValidos = false;
			}
			
			if(vm.dados.email == undefined){
				MsgCenter.add("WARN",
						"E-mail obrigatorio", undefined,
						undefined);
				filtrosValidos = false;
			}
			if(vm.dados.mensagem == undefined){
				MsgCenter.add("WARN",
						"Mensagem obrigatoria", undefined,
						undefined);
				filtrosValidos = false;
			}
			
			return filtrosValidos;
		}
    }

})();


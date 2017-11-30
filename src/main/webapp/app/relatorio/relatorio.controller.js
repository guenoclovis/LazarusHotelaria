//-----------------------------------------------------
// CONTROLADOR CONSULTA
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'consultar' do modulo 'relatorio'
    angular.module('relatorio').controller(
            'RelatorioController',
            RelatorioController);

    // Definindo atributos e operacoes do Controlador da tela 'consultar' do modulo 'Usuario'
    /* @ngInject */
    function RelatorioController($controller, $scope, $state,
    		RelatorioData, MsgCenter) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;
        
        vm.dados = {};

	    vm.gerarRelatorio = gerarRelatorio;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
        }

        function gerarRelatorio() {
        	
        	MsgCenter.add("WARN", "Gerando Relatorio ...", undefined, undefined);
        	
            RelatorioData.gerarRelatorio(vm.dados).then(function(data){
            	MsgCenter.clear();
            	
            	MsgCenter.add("INFO", "Relatorio gerado com sucesso!", undefined, undefined);
            	
            	vm.dados = {};
            });
        }
    }

})();


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
    		MsgCenter, $rootScope, $window) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;
        
        vm.dados = {};
        
        var apiURLRelCliente = '/cliente';
        var apiURLCompletaRelCliente = 'LazarusHotelaria/rest-clovis' + apiURLRelCliente;        
        vm.urlRelatorio = $rootScope.baseURL + apiURLCompletaRelCliente + "/pdf";
        
        vm.gerarRelatorioClientesPDF = gerarRelatorioClientesPDF;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
        }

        function gerarRelatorioClientesPDF() {
        	
        	MsgCenter.add("WARN", "Gerando Relatorio ...", undefined, undefined);
        	
            $window.open(vm.urlRelatorio, '_blank');
        	
        	MsgCenter.clear();
        	MsgCenter.add("INFO", "Relatorio gerado com sucesso!", undefined, undefined);
            	
        }
    }

})();

